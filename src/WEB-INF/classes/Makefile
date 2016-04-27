
EXCLASSPATH=/home/pi/WEB-LIB/fastjson-1.2.8.jar:../lib/caikid-lib.jar

all: VerifyServlet.class

test: TestCookieServlet.class

clean:
	rm -rf *.class

%.class: %.java
	javac -classpath $(TOMCATLIBPATH):$(EXCLASSPATH) $^
