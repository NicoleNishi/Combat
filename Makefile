MAIN = Combat

all:
	javac src/*.java

run: all
	java -cp src $(MAIN)