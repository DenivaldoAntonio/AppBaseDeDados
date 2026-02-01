package com.bd.sgv.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Main{

    @FXML
    private MenuItem menuAtoresListar;

    @FXML
    private MenuItem menuAtoresInserir;

    @FXML
    private MenuItem menuDiretoresListar;

    @FXML
    private MenuItem menuDiretoresInserir;

    @FXML
    private MenuItem menuConsultas;

    @FXML
    private MenuItem menuViews;

    @FXML
    private MenuItem menuListagens;

    @FXML
    private MenuItem menuSair;

    @FXML
    public void initialize() {
        configurarMenus();
    }

    private void configurarMenus() {
        // Menu Atores
        menuAtoresListar.setOnAction(e -> abrirTela("/fxml/AtorListView.fxml", "Gerir Atores"));
        menuAtoresInserir.setOnAction(e -> abrirTela("/fxml/AtorFormView.fxml", "Inserir Ator"));

        // Menu Diretores
        menuDiretoresListar.setOnAction(e -> abrirTela("/fxml/DiretorListView.fxml", "Gerir Diretores"));
        menuDiretoresInserir.setOnAction(e -> abrirTela("/fxml/DiretorFormView.fxml", "Inserir Diretor"));

        // Menu Consultas
        menuConsultas.setOnAction(e -> abrirTela("/fxml/ConsultasView.fxml", "Consultas SQL"));
        menuListagens.setOnAction(e -> abrirTela("/fxml/listagens.fxml", "Listagens"));

        // Menu Views
        menuViews.setOnAction(e -> abrirTela("/fxml/ViewsView.fxml", "Views do Sistema"));

        // Sair
        menuSair.setOnAction(e -> System.exit(0));
    }

    private void abrirTela(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            mostrarErro("Erro ao abrir tela", e.getMessage());
        }
    }

    private void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void handleSobre() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre");
        alert.setHeaderText("DEISIMDB - Sistema de Gestão de Filmes/Videos");
        alert.setContentText("Versão 1.0.0\n\n" +
                "Desenvolvido para o Projeto Final de Base de Dados\n" +
                "Arquitetura MVC com JavaFX");
        alert.showAndWait();
    }
}