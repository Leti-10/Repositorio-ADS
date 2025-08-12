package gui;

import DAO.AlunoDAO;
import DAO.CursoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import modelo.Aluno;
import modelo.Curso;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.io.*;

public class AlunosController implements Initializable {

    @FXML private TextField txtNome;
    @FXML private MaskedTextField txtCpf;
    @FXML private MaskedTextField txtTelefone;
    @FXML private TextField txtEmail;
    @FXML private DatePicker dateNascimento;
    @FXML private ComboBox<Curso> comboCurso;
    @FXML private ComboBox<String> comboStatus2;

    @FXML private Button btnAdicionar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
    @FXML private Button btnBuscar;
    @FXML private Button btnLimparBusca;
    @FXML private Button btnExportarAlunos;

    @FXML private ComboBox<String> comboFiltro;
    @FXML private TextField txtBusca;


    @FXML private TableView<Aluno> tableAlunos;
    @FXML private TableColumn<Aluno, Integer> colId;
    @FXML private TableColumn<Aluno, String> colNome;
    @FXML private TableColumn<Aluno, String> colCpf;
    @FXML private TableColumn<Aluno, String> colTelefone;
    @FXML private TableColumn<Aluno, String> colEmail;
    @FXML private TableColumn<Aluno, String> colNascimento;
    @FXML private TableColumn<Aluno, String> colCurso;
    @FXML private TableColumn<Aluno, String> colAtivo;

    private AlunoDAO alunoDAO;
    private CursoDAO cursoDAO;
    private Aluno alunoSelecionado;
    private ObservableList<Aluno> alunosList;
    private ObservableList<Curso> cursosList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alunoDAO = new AlunoDAO();
        cursoDAO = new CursoDAO();

        setupTable();
        setupComboBoxes();
        setupEvents();
        loadData();
        for (TableColumn<?, ?> col : tableAlunos.getColumns()) {
            col.setReorderable(false);
        }

        configurarDatePicker();
        dateNascimento.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            String value = newValue.replaceAll("[^0-9/]", "");
            if (value.length() > 10) value = value.substring(0, 10);
            StringBuilder sb = new StringBuilder(value);
            if (sb.length() > 2 && sb.charAt(2) != '/') sb.insert(2, '/');
            if (sb.length() > 5 && sb.charAt(5) != '/') sb.insert(5, '/');
            if (!sb.toString().equals(newValue)) {
                dateNascimento.getEditor().setText(sb.toString());
            }
        });


        comboStatus2.getItems().addAll("Ativo", "Inativo");
        comboStatus2.setValue("Ativo");
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idAluno"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNascimento.setCellValueFactory(cellData -> {
            LocalDate data = cellData.getValue().getData_Nascimento();
            return new javafx.beans.property.SimpleStringProperty(
                    data != null ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""
            );
        });
        colCurso.setCellValueFactory(cellData -> {
            Curso curso = cellData.getValue().getCurso();
            return new javafx.beans.property.SimpleStringProperty(
                    curso != null ? curso.getNome() : ""
            );
        });
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


        alunosList = FXCollections.observableArrayList();
        tableAlunos.setItems(alunosList);
    }

    private void setupComboBoxes() {
        comboFiltro.getItems().addAll("ID","Nome", "CPF","Status", "Curso");

        cursosList = FXCollections.observableArrayList();
        comboCurso.setItems(cursosList);
        comboCurso.setCellFactory(param -> new ListCell<Curso>() {
            @Override
            protected void updateItem(Curso item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome());
                }
            }
        });
        comboCurso.setButtonCell(comboCurso.getCellFactory().call(null));

        /*Converte o objeto Curso em uma String para exibição no ComboBox */
        comboCurso.setConverter(new StringConverter<>() {
            public String toString(Curso curso) {
                return (curso != null) ? curso.getNome() : "";
            }

            public Curso fromString(String nome) {
                return null;
            }
        });
    }

    /*Configura os eventos  dos botões e da seleção na tabela de alunos*/
    private void setupEvents() {
        btnAdicionar.setOnAction(e -> adicionarAluno());
        btnEditar.setOnAction(e -> editarAluno());
        btnExcluir.setOnAction(e -> excluirAluno());
        btnLimpar.setOnAction(e -> limparFormulario());
        btnBuscar.setOnAction(e -> buscarAlunos());
        btnLimparBusca.setOnAction(e -> limparBusca());
        btnExportarAlunos.setOnAction(e -> exportarAlunosFiltrados());

        tableAlunos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        alunoSelecionado = newValue;
                        populateForm(newValue);
                    }
                }
        );
    }

    private void loadData() {
        loadAlunos();
        loadCursos();
    }

    private void loadAlunos() {
        alunosList.clear();
        List<Aluno> alunos = alunoDAO.listarAlunos();
        alunosList.addAll(alunos);
    }

    private void loadCursos() {
        cursosList.clear();
        List<Curso> cursos = cursoDAO.listarTodos();
        cursosList.addAll(cursos);
    }

    private void adicionarAluno() {
        if (!validarFormulario()) {
            return;
        }

        try {
            Aluno aluno = new Aluno();
            aluno.setNome(txtNome.getText().trim());
            aluno.setCpf(txtCpf.getText().trim());
            aluno.setTelefone(txtTelefone.getText().trim());
            aluno.setEmail(txtEmail.getText().trim());
            aluno.setData_Nascimento(dateNascimento.getValue());
            aluno.setAtivo(comboStatus2.getValue().equals("Ativo") ? 1 : 0);
            aluno.setCurso(comboCurso.getValue());

            if (alunoDAO.cpfExiste(aluno.getCpf())) {
                showAlert("Erro", "CPF já cadastrado!", Alert.AlertType.ERROR);
                return;
            }
            if (!alunoDAO.isCPFValido(aluno.getCpf())) {
                showAlert("Erro", "O CPF não é real.", Alert.AlertType.ERROR);
                return;
            }
            if(!alunoDAO.cursoAtivo(aluno.getCurso().getIdCurso())) {
                showAlert("Erro", "O curso está inativo! Escolha outro", Alert.AlertType.ERROR);
                return;
            }

            alunoDAO.inserirAluno(aluno);
            showAlert("Sucesso", "Aluno adicionado com sucesso!", Alert.AlertType.INFORMATION);
            limparFormulario();
            loadAlunos();

        } catch (NumberFormatException ex) {
            showAlert("Erro", "Telefone deve ser um número válido!", Alert.AlertType.ERROR);
        } catch (IllegalArgumentException ex) {
            showAlert("Erro", "Erro ao adicionar aluno: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void editarAluno() {
        if (alunoSelecionado == null) {
            showAlert("Aviso", "Selecione um aluno para editar!", Alert.AlertType.WARNING);
            return;
        }

        if (!validarFormulario()) {
            return;
        }

        try {
            alunoSelecionado.setNome(txtNome.getText().trim());
            alunoSelecionado.setCpf(txtCpf.getText().trim());
            alunoSelecionado.setTelefone(txtTelefone.getText().trim());
            alunoSelecionado.setEmail(txtEmail.getText().trim());
            alunoSelecionado.setData_Nascimento(dateNascimento.getValue());
            alunoSelecionado.setCurso(comboCurso.getValue());
            alunoSelecionado.setAtivo(comboStatus2.getValue().equals("Ativo") ? 1 : 0);


            if(!alunoDAO.cursoAtivo(alunoSelecionado.getCurso().getIdCurso())) {
                showAlert("Erro", "O curso está inativo! Escolha outro", Alert.AlertType.ERROR);
                return;
            }
            alunoDAO.alterarAluno(alunoSelecionado);
            showAlert("Sucesso", "Aluno atualizado com sucesso!", Alert.AlertType.INFORMATION);
            limparFormulario();
            loadAlunos();

        } catch (NumberFormatException ex) {
            showAlert("Erro", "Telefone deve ser um número válido!", Alert.AlertType.ERROR);
        } catch (IllegalArgumentException ex) {
            showAlert("Erro", "Erro ao atualizar aluno: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void excluirAluno() {
        if (alunoSelecionado == null) {
            showAlert("Aviso", "Selecione um aluno para excluir!", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Aluno");
        alert.setContentText("Tem certeza que deseja excluir o aluno " + alunoSelecionado.getNome() + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    alunoDAO.excluirAluno(alunoSelecionado.getIdAluno());
                    showAlert("Sucesso", "Aluno excluído com sucesso!", Alert.AlertType.INFORMATION);
                    limparFormulario();
                    loadAlunos();
                } catch (Exception ex) {
                    showAlert("Erro", "Erro ao excluir aluno: " + ex.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });
    }

    private void buscarAlunos() {
        String filtro = comboFiltro.getValue();
        String valor = txtBusca.getText().trim();

        if (valor.isEmpty()) {
            loadAlunos();
            return;
        }

        try {
            List<Aluno> alunos = alunoDAO.buscarAlunos(filtro, valor);
            alunosList.clear();
            alunosList.addAll(alunos);
        } catch (Exception ex) {
            showAlert("Erro", "Erro ao buscar alunos: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limparBusca() {
        txtBusca.clear();
        loadAlunos();
    }

    private void limparFormulario() {
        txtNome.clear();
        txtCpf.clear();
        txtTelefone.clear();
        txtEmail.clear();
        dateNascimento.setValue(null);
        comboCurso.setValue(null);
        alunoSelecionado = null;
        tableAlunos.getSelectionModel().clearSelection();
    }

    private void populateForm(Aluno aluno) {
        txtNome.setText(aluno.getNome());
        txtCpf.setText(aluno.getCpf());
        txtTelefone.setText(String.valueOf(aluno.getTelefone()));
        txtEmail.setText(aluno.getEmail());
        dateNascimento.setValue(aluno.getData_Nascimento());
        comboCurso.setValue(aluno.getCurso());
        comboStatus2.setValue(aluno.getAtivo() == 1 ? "Ativo" : "Inativo");
    }

    private boolean validarFormulario() {
        if (txtNome.getText().trim().isEmpty()) {
            showAlert("Erro", "Nome é obrigatório!", Alert.AlertType.ERROR);
            return false;
        }
        if (txtCpf.getText().trim().isEmpty()) {
            showAlert("Erro", "CPF é obrigatório!", Alert.AlertType.ERROR);
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            showAlert("Erro", "Email é obrigatório!", Alert.AlertType.ERROR);
            return false;
        }
        if (dateNascimento.getValue() == null) {
            showAlert("Erro", "Data de nascimento é obrigatória!", Alert.AlertType.ERROR);
            return false;
        }
        if (comboCurso.getValue() == null) {
            showAlert("Erro", "Curso é obrigatório!", Alert.AlertType.ERROR);
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

    private void exportarAlunosFiltrados() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar relatório de alunos");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto", "*.txt"));
            File arquivo = fileChooser.showSaveDialog(null);
            if (arquivo == null) return;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                String cursoAtual = "";
                for (Aluno aluno : alunosList) {
                    String nomeCurso = aluno.getCurso() != null ? aluno.getCurso().getNome() : "Sem curso";
                    if (!nomeCurso.equals(cursoAtual)) {
                        cursoAtual = nomeCurso;
                        writer.newLine();
                        writer.write("Curso: " + cursoAtual);
                        writer.newLine();
                        writer.write("--------------------------");
                        writer.newLine();
                    }
                    writer.write(String.format("Nome: %s | CPF: %s | Status: %s | Email: %s",
                            aluno.getNome(),
                            aluno.getCpf(),
                            aluno.getAtivo() == 1 ? "Ativo" : "Inativo",
                            aluno.getEmail()));
                    writer.newLine();
                }
            }
            showAlert("Sucesso", "Relatório de alunos exportado com sucesso!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Erro", "Erro ao exportar relatório de alunos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void configurarDatePicker() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        dateNascimento.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(string, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida: " + string);
                    return null;
                }
            }
        });
        dateNascimento.setEditable(true);
    }



}
