package com.bd.sgv.view;

import com.bd.sgv.model.Diretor;
import com.bd.sgv.service.DiretorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.Optional;

public class DiretorList{

    @FXML
    private TableView<Diretor> tableDiretores;
    
    @FXML
    private TableColumn<Diretor, Integer> colId;
    
    @FXML
    private TableColumn<Diretor, String> colNome;
    
    @FXML
    private TableColumn<Diretor, Boolean> colHidden;
    
    @FXML
    private TextField txtprocurar;
    
    @FXML
    private CheckBox chkMostrarOcultos;
    
    @FXML
    private Button btnNovo;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnApagar;
    
    @FXML
    private Button btnAtualizar;

    private DiretorService diretorService;
    private ObservableList<Diretor> diretoresList;

    @FXML
    public void initialize() {
        diretorService = new DiretorService();
        diretoresList = FXCollections.observableArrayList();
        
        configurarTabela();
        carregarDiretores();
        configurarBotoes();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idDiretor"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colHidden.setCellValueFactory(new PropertyValueFactory<>("hidden"));
        
        tableDiretores.setItems(diretoresList);
        
        // Duplo clique para editar
        tableDiretores.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableDiretores.getSelectionModel().getSelectedItem() != null) {
                editarDiretor();
            }
        });
        
        // Listener para mostrar ocultos
        chkMostrarOcultos.selectedProperty().addListener((obs, oldVal, newVal) -> {
            carregarDiretores();
        });
    }

    private void configurarBotoes() {
        btnNovo.setOnAction(e -> novoDiretor());
        btnEditar.setOnAction(e -> editarDiretor());
        btnApagar.setOnAction(e -> apagarDiretor());
        btnAtualizar.setOnAction(e -> carregarDiretores());
    }

    private void carregarDiretores() {
        diretoresList.clear();
        boolean incluirOcultos = chkMostrarOcultos.isSelected();
        diretoresList.addAll(diretorService.listarDiretores(incluirOcultos));
        
        Notifications.create()
                .title("Diretores Carregados")
                .text(diretoresList.size() + " diretores encontrados")
                .showInformation();
    }

    @FXML
    private void novoDiretor() {
        abrirFormulario(null);
    }

    @FXML
    private void editarDiretor() {
        Diretor selecionado = tableDiretores.getSelectionModel().getSelectedItem();
        
        if (selecionado == null) {
            mostrarAviso("Selecione um diretor para editar");
            return;
        }
        
        abrirFormulario(selecionado);
    }

    @FXML
    private void apagarDiretor() {
        Diretor selecionado = tableDiretores.getSelectionModel().getSelectedItem();
        
        if (selecionado == null) {
            mostrarAviso("Selecione um diretor para apagar");
            return;
        }
        
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar Exclusão");
        confirmacao.setHeaderText("Deseja realmente apagar o diretor?");
        confirmacao.setContentText(selecionado.getNome() + "\n\nNOTA: Será feito SOFT DELETE (hidden=1) via TRIGGER");
        
        Optional<ButtonType> resultado = confirmacao.showAndWait();
        
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if (diretorService.apagarDiretor(selecionado.getIdDiretor())) {
                Notifications.create()
                        .title("Sucesso")
                        .text("Diretor marcado como oculto!\n(Trigger de soft delete acionado)")
                        .showInformation();
                
                carregarDiretores();
            } else {
                mostrarErro("Erro ao apagar diretor");
            }
        }
    }

    private void abrirFormulario(Diretor diretor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DiretorFormView.fxml"));
            Parent root = loader.load();
            
            DiretorForm controller = loader.getController();
            controller.setDiretorListController(this);
            
            if (diretor != null) {
                controller.setDiretor(diretor);
            }
            
            Stage stage = new Stage();
            stage.setTitle(diretor == null ? "Novo Diretor" : "Editar Diretor");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            
        } catch (IOException e) {
            mostrarErro("Erro ao abrir formulário: " + e.getMessage());
        }
    }

    private void mostrarAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
