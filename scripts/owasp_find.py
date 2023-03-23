from xml.dom.minidom import parse
import shutil
import os

# settings
want = "pathtraver"
path_to = "/home/andrew/check"
copy = False

last = 2741
path = "/home/andrew/BenchmarkJava/src/main/java/org/owasp/benchmark/testcode"

if copy and not os.path.exists(path_to):
    os.makedirs(path_to)


with open("want.csv", 'w') as f:
    f.write(f"target,source\n")
    for i in range(1, last):
        name = f"BenchmarkTest{'{:05d}'.format(i)}"
        fullpath = f"{path}/{name}"
        document = parse(f"{fullpath}.xml")
        fullpath = f"{fullpath}.java"
        fullpath_to = f"{path_to}/{name}.java"
        if document.getElementsByTagName("category")[0].childNodes[0].nodeValue == want:
            f.write(f"org.owasp.benchmark.testcode.{name},{fullpath}\n")
            if copy:
                shutil.copy(fullpath, fullpath_to)
