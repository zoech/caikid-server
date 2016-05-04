CAIKID_LIB=../lib/caikid-lib.jar
EXCLASS=/home/pi/WEB-LIB/fastjson-1.2.8.jar:$(CAIKID_LIB)

all: VerifyServlet.class SignupServlet.class LoginServlet.class LogoutServlet.class

test: TestCookieServlet.class

clean:
	rm -rf *.class

%.class: %.java
	javac -classpath $(TOMCATLIBPATH):$(EXCLASS) $<
