package com.bd.sgv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.bd.sgv.util.DbConnection;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Testar ligação com o Base
            DbConnection.getInstance();

            // Carregar FXML principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("DEISIMDB - Sistema de Gestão de Filmes");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();

            System.out.println("Aplicação iniciada com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao iniciar aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Fechar ligação com BD ao encerrar
        DbConnection.getInstance().closeConnection();
        System.out.println("Aplicação encerrada.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
