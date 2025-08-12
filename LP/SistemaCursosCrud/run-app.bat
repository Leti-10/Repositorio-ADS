@echo off
echo ========================================
echo Sistema de Cursos CRUD - JavaFX
echo ========================================

echo Compilando o projeto...
javac -cp ".;lib/*" -d bin src/main/java/*.java src/main/java/gui/*.java src/main/java/DAO/*.java src/main/java/factory/*.java src/main/java/modelo/*.java src/main/java/org/lingprog/sistemacursoscrud/*.java

if %ERRORLEVEL% NEQ 0 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo Executando a aplicacao...
java -cp "bin;lib/*" --module-path "lib" --add-modules javafx.controls,javafx.fxml Main

pause 