# Compilar os arquivos .java
build:
	(cd src && javac *.java)

# Executar a classe principal
run: build
	(cd src && java Combat)