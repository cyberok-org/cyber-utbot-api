import os
import re

path = '/home/andrew/BenchmarkJava/src/main/java/org/owasp/benchmark/check'
pattern = 'javax.servlet[.\w*]+'
# pattern = 'import \w[.\w]*'
# pattern = '[\w]+\('
# pattern = 'new \w[.\w]*'

classes = set()

for name in os.listdir(path):
    with open(f"{path}/{name}", 'r') as f:
        text = f.read()
        res = re.findall(pattern, text)
        classes.update(res)

print(classes)
print(len(classes))
