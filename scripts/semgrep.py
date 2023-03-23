import json
from typing import List
import os


result_file = "result.json"
source = "/home/andrew/BenchmarkJava/check"
classnames = ['java.net.URL', 
            'java.io.FileInputStream', 
            'org.owasp.benchmark.helpers.SeparateClassRequest', 
            'java.util.HashMap',
            'byte', 
            'String', 
            'java.net.URI', 
            'Test', 
            'ServletException', 
            'StringBuilder', 
            'java.io.FileOutputStream', 
            'java.io.File', 
            'java.util.ArrayList',
            'javax.servlet.http.HttpServlet',
            'javax.servlet.http.HttpServletRequest',
            'javax.servlet.http.Cookie',
            'javax.servlet.RequestDispatcher',
            'javax.servlet.annotation.WebServlet',
            'javax.servlet.http.HttpServletResponse',
            'javax.servlet.ServletException']
config = lambda classname: f"""rules:
  - id: class-calls
    message: calls
    languages:
      - java
    severity: INFO
    patterns:
      - patterns:
        - pattern: ({classname} $C).$X(...)
        - focus-metavariable: $X"""

# temp
config_file = "class-calls.yaml"
out_file = "res.json"


def find_calls_by_class(classname: str) -> List[str]:
    funcs = set()
    classnames = [classname]
    reduction = classname.rsplit('.', 1)
    if len(reduction) > 1:
        classnames.append(reduction[-1])
    for classname_ in classnames:
        with open(config_file, 'w') as f:
            f.write(config(classname_))
        os.system(f"semgrep --config {config_file} {source} -q -o {out_file} --json")
        res = json.load(open(out_file, 'r'))
        for path in res["results"]:
            funcs.add(path["extra"]["metavars"]["$X"]["abstract_content"])
    return list(funcs)


def find_calls():
    answer = {}
    for classname in classnames:
        answer[classname] = find_calls_by_class(classname)
    json.dump(answer, open(result_file, 'w'))


if __name__ == "__main__":
    find_calls()
