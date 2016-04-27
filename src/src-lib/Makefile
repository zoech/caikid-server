src_path=./src
build_path=./build

modle_archieve=com/imzoee/model
conv_archieve=com/imzoee/convention

lib=caikid-lib.jar

all: module

deploy: module
	mkdir -p ../WEB-INF/lib
	cp ./$(lib) ../WEB-INF/lib

module: 
	cd $(src_path)/$(modle_archieve) && make
	cd $(src_path)/$(conv_archieve) && make
	cd $(build_path) && jar cvf $(lib) ./com && mv $(lib) ../

clean:
	cd $(src_path)/$(modle_archieve) && make clean
	cd $(src_path)/$(conv_archieve) && make clean
	rm -rf $(lib)
	rm -rf $(build_path)
