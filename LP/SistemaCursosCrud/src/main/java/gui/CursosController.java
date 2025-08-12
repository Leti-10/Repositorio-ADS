package gui;

import DAO.CursoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import modelo.Curso;

import java.io.BufferedWriter;
import  java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CursosController implements Initializable {

    @FXML private TextField txtNome;
    @FXML private TextField txtCargaHoraria;
    @FXML private TextField txtLimiteAlunos;
    @FXML private TextField txtBusca;
    @FXML private ComboBox<String> comboStatus;
    @FXML private ComboBox<String> comboFiltro;

    @FXML private Button btnAdicionar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
    @FXML private Button btnBuscar;
    @FXML private Button btnLimparBusca;
    @FXML private Button btnExportarCursos;
    
    @FXML private TableView<Curso> tableCursos;
    @FXML private TableColumn<Curso, Integer> colId;
    @FXML private TableColumn<Curso, String> colNome;
    @FXML private TableColumn<Curso, String> colCargaHoraria;
    @FXML private TableColumn<Curso, String> colLimiteAlunos;
    @FXML private TableColumn<Curso, String> colAtivo;
    @FXML private TableColumn<Curso, String> colQtdAlunos;

    private CursoDAO cursoDAO;
    private Curso cursoSelecionado;
    private ObservableList<Curso> cursosList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cursoDAO = new CursoDAO();
        
        setupTable();
        setupComboBoxes();
        setupEvents();
        loadData();
        for (TableColumn<?, ?> col : tableCursos.getColumns()) {
            col.setReorderable(false);
        }
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCargaHoraria.setCellValueFactory(cellData -> {
            int cargaHoraria = cellData.getValue().getCargaHoraria();
            return new javafx.beans.property.SimpleStringProperty(cargaHoraria + "h");
        });
        colLimiteAlunos.setCellValueFactory(new PropertyValueFactory<>("limiteAlunos"));
        colAtivo.setCellValueFactory(cellData -> {
            int ativo = cellData.getValue().getAtivo();
            return new javafx.beans.property.SimpleStringProperty(
                ativo == 1 ? "Ativo" : "Inativo"
            );
        });
        colAtivo.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    getStyleClass().removeAll("ativo", "inativo");
                } else {
                    setText(status);
                    getStyleClass().removeAll("ativo", "inativo");
                    getStyleClass().add(status.equals("Ativo") ? "ativo" : "inativo");
                }
            }
        });

        colQtdAlunos.setCellValueFactory(cellData -> {
            return new javafx.beans.property.SimpleStringProperty(
                    String.valueOf(cellData.getValue().getQtdAlunos())
            );
        });


        cursosList = FXCollections.observableArrayList();
        tableCursos.setItems(cursosList);
    }

    private void setupComboBoxes() {
        comboStatus.getItems().addAll("Ativo", "Inativo");
        comboStatus.setValue("Ativo");
        comboFiltro.getItems().addAll("ID","Nome", "Carga","Limite","Status", "Qtd.Alunos");
    }

    private void setupEvents() {
        btnAdicionar.setOnAction(e -> adicionarCurso());
        btnEditar.setOnAction(e -> editarCurso());
        btnExcluir.setOnAction(e -> excluirCurso());
        btnLimpar.setOnAction(e -> limparFormulario());
        btnBuscar.setOnAction(e -> buscarCursos());
        btnLimparBusca.setOnAction(e -> LimparBusca());
        btnExportarCursos.setOnAction(e -> exportarCursosFiltrados());
        
        tableCursos.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    cursoSelecionado = newValue;
                    populateForm(newValue);
                }
            }
        );
    }

    private void loadData() {
        loadCursos();
    }

    private void loadCursos() {
        cursosList.clear();
        List<Curso> cursos = cursoDAO.listarTodos();
        cursosList.addAll(cursos);
    }

    private void adicionarCurso() {
        if (!validarFormulario()) {
            return;
        }

        try {
            Curso curso = new Curso();
            curso.setNome(txtNome.getText().trim());
            curso.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText().trim()));
            curso.setLimiteAlunos(Integer.parseInt(txtLimiteAlunos.getText().trim()));
            curso.setAtivo(comboStatus.getValue().equals("Ativo") ? 1 : 0);

            cursoDAO.inserirCurso(curso);
            showAlert("Sucesso", "Curso adicionado com sucesso!", Alert.AlertType.INFORMATION);
            limparFormulario();
            loadCursos();

        } catch (NumberFormatException ex) {
            showAlert("Erro", "Carga horária e limite de alunos devem ser números válidos!", Alert.AlertType.ERROR);
        } catch (Exception ex) {
            showAlert("Erro", "Erro ao adicionar curso: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void editarCurso() {
        if (cursoSelecionado == null) {
            showAlert("Aviso", "Selecione um curso para editar!", Alert.AlertType.WARNING);
            return;
        }

        if (!validarFormulario()) {
            return;
        }

        try {
            cursoSelecionado.setNome(txtNome.getText().trim());
            cursoSelecionado.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText().trim()));
            cursoSelecionado.setLimiteAlunos(Integer.parseInt(txtLimiteAlunos.getText().trim()));
            int novoStatus = comboStatus.getValue().equals("Ativo") ? 1 : 0;

            if (cursoSelecionado.getAtivo() != novoStatus) {
                if (novoStatus == 0) {
                    cursoDAO.desativarCurso(cursoSelecionado.getIdCurso());
                } else {
                    cursoDAO.reativarCurso(cursoSelecionado.getIdCurso());
                }
            } else {
                cursoDAO.alterarCurso(cursoSelecionado);
            }
            showAlert("Sucesso", "Curso atualizado com sucesso!", Alert.AlertType.INFORMATION);
            limparFormulario();
            loadCursos();

        } catch (NumberFormatException ex) {
            showAlert("Erro", "Carga horária e limite de alunos devem ser números válidos!", Alert.AlertType.ERROR);
        } catch (Exception ex) {
            showAlert("Erro", "Erro ao atualizar curso: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void excluirCurso() {
        if (cursoSelecionado == null) {
            showAlert("Aviso", "Selecione um curso para excluir!", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Curso");
        alert.setContentText("Tem certeza que deseja excluir o curso " + cursoSelecionado.getNome() + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    cursoDAO.excluirCurso(cursoSelecionado.getIdCurso());
                    showAlert("Sucesso", "Curso excluído com sucesso!", Alert.AlertType.INFORMATION);
                    limparFormulario();
                    loadCursos();
                } catch (Exception ex) {
                    showAlert("Erro", "Erro ao excluir curso: " + ex.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });
    }

    private void limparFormulario() {
        txtNome.clear();
        txtCargaHoraria.clear();
        txtLimiteAlunos.clear();
        comboStatus.setValue("Ativo");
        cursoSelecionado = null;
        tableCursos.getSelectionModel().clearSelection();
    }

    private void populateForm(Curso curso) {
        txtNome.setText(curso.getNome());
        txtCargaHoraria.setText(String.valueOf(curso.getCargaHoraria()));
        txtLimiteAlunos.setText(String.valueOf(curso.getLimiteAlunos()));
        comboStatus.setValue(curso.getAtivo() == 1 ? "Ativo" : "Inativo");
    }

    private boolean validarFormulario() {
        if (txtNome.getText().trim().isEmpty()) {
            showAlert("Erro", "Nome é obrigatório!", Alert.AlertType.ERROR);
            return false;
        }
        if (txtCargaHoraria.getText().trim().isEmpty()) {
            showAlert("Erro", "Carga horária é obrigatória!", Alert.AlertType.ERROR);
            return false;
        }
        if (txtLimiteAlunos.getText().trim().isEmpty()) {
            showAlert("Erro", "Limite de alunos é obrigatório!", Alert.AlertType.ERROR);
            return false;
        }
        try {
            int cargaHoraria = Integer.parseInt(txtCargaHoraria.getText().trim());
            int limiteAlunos = Integer.parseInt(txtLimiteAlunos.getText().trim());
            
            if (cargaHoraria <= 0) {
                showAlert("Erro", "Carga horária deve ser maior que zero!", Alert.AlertType.ERROR);
                return false;
            }
            if (limiteAlunos <= 0) {
                showAlert("Erro", "Limite de alunos deve ser maior que zero!", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Erro", "Carga horária e limite de alunos devem ser números válidos!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void buscarCursos() {
        String filtro = comboFiltro.getValue();
        String valor = txtBusca.getText().trim();

        if (valor.isEmpty()) {
            loadCursos();
            return;
        }


        try {
            List<Curso> cursosFiltrados = cursoDAO.buscarCursos(filtro, valor);
            cursosList.clear();
            cursosList.addAll(cursosFiltrados);
        } catch (Exception e) {
            showAlert("Erro", "Erro ao buscar cursos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void LimparBusca() {
        txtBusca.clear();
        loadCursos();
    }

    private void exportarCursosFiltrados() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar relatório de cursos");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto", "*.txt"));
            File arquivo = fileChooser.showSaveDialog(null);
            if (arquivo == null) return;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                for (Curso curso : cursosList) {
                    writer.write("Curso: " + curso.getNome());
                    writer.newLine();
                    writer.write("Carga Horária: " + curso.getCargaHoraria() + "h");
                    writer.newLine();
                    writer.write("Limite de Alunos: " + curso.getLimiteAlunos());
                    writer.newLine();
                    writer.write("Status: " + (curso.getAtivo() == 1 ? "Ativo" : "Inativo"));
                    writer.newLine();
                    writer.write("Quantidade de Alunos: " + (curso.getAlunos() != null ? curso.getAlunos().size() : 0));
                    writer.newLine();
                    writer.write("--------------------------");
                    writer.newLine();
                }
            }
            showAlert("Sucesso", "Relatório de cursos exportado com sucesso!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Erro", "Erro ao exportar relatório de cursos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


} 