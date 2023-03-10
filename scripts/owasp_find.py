from xml.dom.minidom import parse

want = "pathtraver"
path = "/home/andrew/BenchmarkJava/src/main/java/org/owasp/benchmark/testcode"
last = 2741

with open("want.csv", 'w') as f:
    f.write(f"target,source\n")
    for i in range(1, last):
        name = f"BenchmarkTest{'{:05d}'.format(i)}"
        fullpath = f"{path}/{name}.xml"
        document = parse(fullpath)
        if document.getElementsByTagName("category")[0].childNodes[0].nodeValue == want:
            f.write(f"org.owasp.benchmark.testcode.{name},{fullpath}\n")
