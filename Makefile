
all:
	cd src && make
	mkdir ./build
	cp -r src/WEB-INF ./build
	cd ./build/WEB-INF/classes && rm *.java

clean:
	rm -rf ./build
	cd src && make clean
