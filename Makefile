srcdir=src
buildir=build

# set your java class path here
TOMCATLIBPATH=/usr/share/tomcat7/lib/servlet-api.jar
EXCLASSPATH=/home/pi/WEB-LIB/fastjson.jar

all:
	cd $(srcdir) && export TOMCATLIBPATH=$(TOMCATLIBPATH) &&\
			export EXCLASSPATH=$(EXCLASSPATH) && make
	mkdir -p $(buildir)
	cp -r $(srcdir)/WEB-INF $(buildir)
	cd $(buildir)/WEB-INF/classes && rm *.java

clean:
	rm -rf $(buildir)
	cd $(srcdir) && make clean
