package com.bd.sgv.view;

import com.bd.sgv.model.Ator;
import com.bd.sgv.service.AtorService;
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

public class AtorList{

    @FXML
    private TableView<Ator> tableAtores;
    
    @FXML
    private TableColumn<Ator, Integer> colId;
    
    @FXML
    private TableColumn<Ator, String> colNome;
    
    @FXML
    private TableColumn<Ator, Character> colSexo;
    
    @FXML
    private TextField txtprocurar;
    
    @FXML
    private Button btnNovo;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnApagar;
    
    @FXML
    private Button btnAtualizar;

    private AtorService atorService;
    private ObservableList<Ator> atoresList;

    @FXML
    public void initialize() {
        atorService = new AtorService();
        atoresList = FXCollections.observableArrayList();
        
        configurarTabela();
        carregarAtores();
        configurarBotoes();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idAtor"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        
        tableAtores.setItems(atoresList);
        
        // Duplo clique para editar
        tableAtores.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableAtores.getSelectionModel().getSelectedItem() != null) {
                editarAtor();
            }
        });
    }

    private void configurarBotoes() {
        btnNovo.setOnAction(e -> novoAtor());
        btnEditar.setOnAction(e -> editarAtor());
        btnApagar.setOnAction(e -> procurarAtor());
        btnAtualizar.setOnAction(e -> carregarAtores());
    }

    private void carregarAtores() {
        atoresList.clear();
        atoresList.addAll(atorService.listarAtores());
        
        Notifications.create()
                .title("Atores Carregados")
                .text(atoresList.size() + " atores encontrados")
                .showInformation();
    }

    @FXML
    private void novoAtor() {
        abrirFormulario(null);
    }

    @FXML
    private void editarAtor() {
        Ator selecionado = tableAtores.getSelectionModel().getSelectedItem();
        
        if (selecionado == null) {
            mostrarAviso("Selecione um ator para editar");
            return;
        }
        
        abrirFormulario(selecionado);
    }

    @FXML
    private void apagarAtor() {
        Ator selecionado = tableAtores.getSelectionModel().getSelectedItem();
        
        if (selecionado == null) {
            mostrarAviso("Selecione um ator para apagar");
            return;
        }
        
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar Exclusão");
        confirmacao.setHeaderText("Deseja realmente apagar o ator?");
        confirmacao.setContentText(selecionado.getNome());
        
        Optional<ButtonType> resultado = confirmacao.showAndWait();
        
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if (atorService.apagarAtor(selecionado.getIdAtor())) {
                Notifications.create()
                        .title("Sucesso")
                        .text("Ator deletado com sucesso!")
                        .showInformation();
                
                carregarAtores();
            } else {
                mostrarErro("Erro ao apagar ator");
            }
        }
    }

    private void abrirFormulario(Ator ator) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AtorFormView.fxml"));
            Parent root = loader.load();
            
            AtorForm controller = loader.getController();
            controller.setAtorListController(this);
            
            if (ator != null) {
                controller.setAtor(ator);
            }
            
            Stage stage = new Stage();
            stage.setTitle(ator == null ? "Novo Ator" : "Editar Ator");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            
        } catch (IOException e) {
            mostrarErro("Erro ao abrir formulário: " + e.getMessage());
        }
    }

    @FXML
    private void procurarAtor() {
        String termo = txtprocurar.getText().toLowerCase();
        
        if (termo.isEmpty()) {
            tableAtores.setItems(atoresList);
            return;
        }
        
        ObservableList<Ator> filtrados = atoresList.filtered(ator -> 
            ator.getNome().toLowerCase().contains(termo)
        );
        
        tableAtores.setItems(filtrados);
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
