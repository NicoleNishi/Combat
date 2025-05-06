# Diretório com os arquivos-fonte
SRC_DIR = src

# Nome da classe principal
MAIN_CLASS = Combat

# Todos os arquivos .java dentro de src/
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Compilar os arquivos .java
build:
	javac -d $(SRC_DIR) $(SOURCES)

# Executar a classe principal
run: build
	java -cp $(SRC_DIR) $(MAIN_CLASS)