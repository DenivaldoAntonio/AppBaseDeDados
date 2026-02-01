package com.bd.sgv.view;

import com.bd.sgv.model.Diretor;
import com.bd.sgv.service.DiretorService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

public class DiretorForm{

    @FXML
    private TextField txtNome;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnCancelar;

    private DiretorService diretorService;
    private Diretor diretorAtual;
    private DiretorList listController;

    @FXML
    public void initialize() {
        diretorService = new DiretorService();
        configurarBotoes();
    }

    private void configurarBotoes() {
        btnSalvar.setOnAction(e -> salvar());
        btnCancelar.setOnAction(e -> fechar());
    }

    public void setDiretorListController(DiretorList controller) {
        this.listController = controller;
    }

    public void setDiretor(Diretor diretor) {
        this.diretorAtual = diretor;
        
        if (diretor != null) {
            txtNome.setText(diretor.getNome());
        }
    }

    @FXML
    private void salvar() {
        if (!validarCampos()) {
            return;
        }
        
        String nome = txtNome.getText().trim();
        boolean sucesso;
        
        if (diretorAtual == null) {
            sucesso = diretorService.criarDiretor(nome);
        } else {
            sucesso = diretorService.atualizarDiretor(diretorAtual.getIdDiretor(), nome);
        }
        
        if (sucesso) {
            Notifications.create()
                    .title("Sucesso")
                    .text("Diretor salvo com sucesso!")
                    .showInformation();
            
            if (listController != null) {
                listController.initialize();
            }
            
            fechar();
        } else {
            mostrarErro("Erro ao salvar diretor");
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
