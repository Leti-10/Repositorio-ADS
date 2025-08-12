#!/bin/bash
echo "Compilando o projeto..."
mvn clean compile

echo "Executando a aplicacao JavaFX..."
mvn javafx:run 