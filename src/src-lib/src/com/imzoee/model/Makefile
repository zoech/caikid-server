
EXCLASSPATH=/home/pi/WEB-LIB/fastjson-1.2.8.jar

SRCROOT=../../..
BUILDROOT=../../../../build
DESPATH=../../../../../WEB-INF/lib/

ARCHIEVE=com/imzoee/model
JARLIB=model.jar

all: copy

deploy: module
	cp ./model.jar $(DESPATH)

module: copy
	cd $(BUILDROOT) && jar cvf $(JARLIB) com && mv $(JARLIB) ../src/$(ARCHIEVE)

clean:
	rm -rf *.class
	rm -rf *.jar

copy: User.class
	mkdir -p $(BUILDROOT)/$(ARCHIEVE)
	cp $^ $(BUILDROOT)/$(ARCHIEVE)
	

%.class:%.java
	javac -classpath $(CLASSPATH):$(EXCLASSPATH):$(TOMCATLIBPATH) $^
