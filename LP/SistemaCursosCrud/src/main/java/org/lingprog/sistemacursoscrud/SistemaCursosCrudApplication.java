package org.lingprog.sistemacursoscrud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SistemaCursosCrudApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {

            URL fxmlUrl = getClass().getResource("main-view.fxml");
            if (fxmlUrl == null) {
                throw new IOException("Não foi possível encontrar main-view.fxml");
            }
            
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            stage.setTitle("☆");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Erro ao iniciar a aplicação: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.err.println("Erro fatal na aplicação: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }
} 