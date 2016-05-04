
#EXCLASSPATH=/home/pi/WEB-LIB/fastjson-1.2.8.jar

SRCROOT=../../..
BUILDROOT=../../../../build
DESPATH=../../../../../WEB-INF/lib/

ARCHIEVE=com/imzoee/convention
JARLIB=convention.jar

all: copy

deploy: module
	cp ./$(JARLIB) $(DESPATH)

module: clean-buildir copy
	cd $(BUILDROOT) && jar cvf $(JARLIB) com && mv $(JARLIB) ../src/$(ARCHIEVE)

clean:
	rm -rf *.class
	rm -rf *.jar

#copy: ConstConv.class VerifyConv.class SignupConv.class LoginConv.class LogoutConv.class \
		UserInfoConv.class PostUserConv.class

copy: ConstConv.class
	mkdir -p $(BUILDROOT)/$(ARCHIEVE)
	cp $^ $(BUILDROOT)/$(ARCHIEVE)

clean-buildir:
	rm -rf $(BUILDROOT)
	

%.class:%.java
	javac -classpath $(CLASSPATH):$(EXCLASSPATH):$(TOMCATLIBPATH) $^
