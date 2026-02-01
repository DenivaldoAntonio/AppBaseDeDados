package com.bd.sgv.view;

import com.bd.sgv.model.Ator;
import com.bd.sgv.service.AtorService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

public class AtorForm{

    @FXML
    private TextField txtNome;
    
    @FXML
    private RadioButton rbMasculino;
    
    @FXML
    private RadioButton rbFeminino;
    
    @FXML
    private ToggleGroup groupSexo;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnCancelar;

    private AtorService atorService;
    private Ator atorAtual;
    private AtorList listController;

    @FXML
    public void initialize() {
        atorService = new AtorService();
        
        // Configurar grupo de radio buttons
        groupSexo = new ToggleGroup();
        rbMasculino.setToggleGroup(groupSexo);
        rbFeminino.setToggleGroup(groupSexo);
        
        configurarBotoes();
    }

    private void configurarBotoes() {
        btnSalvar.setOnAction(e -> salvar());
        btnCancelar.setOnAction(e -> fechar());
    }

    public void setAtorListController(AtorList controller) {
        this.listController = controller;
    }

    public void setAtor(Ator ator) {
        this.atorAtual = ator;
        
        if (ator != null) {
            txtNome.setText(ator.getNome());
            
            if (ator.getSexo() != null) {
                if (ator.getSexo() == 'M') {
                    rbMasculino.setSelected(true);
                } else if (ator.getSexo() == 'F') {
                    rbFeminino.setSelected(true);
                }
            }
        }
    }

    @FXML
    private void salvar() {
        // Validações
        if (!validarCampos()) {
            return;
        }
        
        String nome = txtNome.getText().trim();
        Character sexo = null;
        
        if (rbMasculino.isSelected()) {
            sexo = 'M';
        } else if (rbFeminino.isSelected()) {
            sexo = 'F';
        }
        
        boolean sucesso;
        
        if (atorAtual == null) {
            // Inserir novo ator (TRIGGER será acionado)
            sucesso = atorService.criarAtor(nome, sexo);
        } else {
            // Atualizar ator existente
            sucesso = atorService.atualizarAtor(atorAtual.getIdAtor(), nome, sexo);
        }
        
        if (sucesso) {
            Notifications.create()
                    .title("Sucesso")
                    .text("Ator salvo com sucesso!\n(Trigger de auditoria acionado)")
                    .showInformation();
            
            if (listController != null) {
                listController.initialize();
            }
            
            fechar();
        } else {
            mostrarErro("Erro ao salvar ator");
        }
    }

    private boolean validarCampos() {
        if (txtNome.getText().trim().isEmpty()) {
            mostrarAviso("O nome é obrigatório");
            txtNome.requestFocus();
            return false;
        }
        
        return true;
    }

    @FXML
    private void fechar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
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
