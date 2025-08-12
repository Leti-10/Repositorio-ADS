package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private StackPane areaConteudo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void abrirTelaAlunos() {
        carregarConteudo("/org/lingprog/sistemacursoscrud/alunos-view.fxml");
    }

    @FXML
    private void abrirTelaCursos() {
        carregarConteudo("/org/lingprog/sistemacursoscrud/cursos-view.fxml");
    }

    private void carregarConteudo(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Node novoConteudo = loader.load();
            areaConteudo.getChildren().setAll(novoConteudo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
