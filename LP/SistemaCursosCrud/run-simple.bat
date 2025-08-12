@echo off
echo Limpando e compilando...
mvn clean compile

echo Executando a aplicacao...
java -cp "target/classes" --module-path "C:\Program Files\Java\javafx-sdk-17.0.6\lib" --add-modules javafx.controls,javafx.fxml org.lingprog.sistemacursoscrud.SistemaCursosCrudApplication

pause 