package com.bd.sgv.view;

import com.bd.sgv.service.ViewService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;

import java.util.List;
import java.util.Map;

public class Views {

    @FXML
    private TabPane tabPane;
    
    // Tab 1: Top 5 Diretores com Mais Filmes
    @FXML
    private TableView<Map> tableTop5Diretores;
    @FXML
    private Button btnCarregarTop5Diretores;
    
    // Tab 2: Top 10 Atores com Mais Filmes
    @FXML
    private TableView<Map> tableTop10Atores;
    @FXML
    private Button btnCarregarTop10Atores;
    
    // Tab 3: Países com Menos de 5 Filmes
    @FXML
    private TableView<Map> tablePaisesMenos5;
    @FXML
    private Button btnCarregarPaisesMenos5;
    
    // Tab 4: Continentes com Mais de 10 Filmes
    @FXML
    private TableView<Map> tableContinentesMais10;
    @FXML
    private Button btnCarregarContinentesMais10;

    private ViewService viewService;

    @FXML
    public void initialize() {
        viewService = new ViewService();
        configurarTabelas();
        configurarBotoes();
    }

    private void configurarTabelas() {
        configurarTabelaTop5Diretores();
        configurarTabelaTop10Atores();
        configurarTabelaPaisesMenos5();
        configurarTabelaContinentesMais10();
    }

    /**
     * Configurar Tabela: Top 5 Diretores com Mais Filmes
     */
    private void configurarTabelaTop5Diretores() {
        TableColumn<Map, String> colDiretor = new TableColumn<>("Diretor");
        colDiretor.setCellValueFactory(new MapValueFactory<>("Diretor"));
        colDiretor.setPrefWidth(300);
        
        TableColumn<Map, Integer> colTotalFilmes = new TableColumn<>("Total de Filmes");
        colTotalFilmes.setCellValueFactory(new MapValueFactory<>("TotalFilmes"));
        colTotalFilmes.setPrefWidth(150);
        
        tableTop5Diretores.getColumns().addAll(colDiretor, colTotalFilmes);
    }

    /**
     * Configurar Tabela: Top 10 Atores com Mais Filmes
     */
    private void configurarTabelaTop10Atores() {
        TableColumn<Map, String> colAtor = new TableColumn<>("Ator");
        colAtor.setCellValueFactory(new MapValueFactory<>("Ator"));
        colAtor.setPrefWidth(300);
        
        TableColumn<Map, Integer> colTotalFilmes = new TableColumn<>("Total de Filmes");
        colTotalFilmes.setCellValueFactory(new MapValueFactory<>("TotalFilmes"));
        colTotalFilmes.setPrefWidth(150);
        
        tableTop10Atores.getColumns().addAll(colAtor, colTotalFilmes);
    }

    /**
     * Configurar Tabela: Países com Menos de 5 Filmes
     */
    private void configurarTabelaPaisesMenos5() {
        TableColumn<Map, String> colPais = new TableColumn<>("País");
        colPais.setCellValueFactory(new MapValueFactory<>("Pais"));
        colPais.setPrefWidth(300);
        
        TableColumn<Map, Integer> colTotalFilmes = new TableColumn<>("Total de Filmes");
        colTotalFilmes.setCellValueFactory(new MapValueFactory<>("TotalFilmes"));
        colTotalFilmes.setPrefWidth(150);
        
        tablePaisesMenos5.getColumns().addAll(colPais, colTotalFilmes);
    }

    /**
     * Configurar Tabela: Continentes com Mais de 10 Filmes
     */
    private void configurarTabelaContinentesMais10() {
        TableColumn<Map, String> colContinente = new TableColumn<>("Continente");
        colContinente.setCellValueFactory(new MapValueFactory<>("Continente"));
        colContinente.setPrefWidth(300);
        
        TableColumn<Map, Integer> colTotalFilmes = new TableColumn<>("Total de Filmes");
        colTotalFilmes.setCellValueFactory(new MapValueFactory<>("TotalFilmes"));
        colTotalFilmes.setPrefWidth(150);
        
        tableContinentesMais10.getColumns().addAll(colContinente, colTotalFilmes);
    }

    private void configurarBotoes() {
        btnCarregarTop5Diretores.setOnAction(e -> carregarTop5Diretores());
        btnCarregarTop10Atores.setOnAction(e -> carregarTop10Atores());
        btnCarregarPaisesMenos5.setOnAction(e -> carregarPaisesMenos5());
        btnCarregarContinentesMais10.setOnAction(e -> carregarContinentesMais10());
    }

    // ===== CARREGAR VIEWS =====

    /**
     * Carregar Top 5 Diretores com Mais Filmes
     */
    @FXML
    private void carregarTop5Diretores() {
        List<Map<String, Object>> dados = viewService.procurarTop5Diretores();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableTop5Diretores.setItems(items);
        
        mostrarInfo("Top 5 Diretores", dados.size() + " registros carregados");
    }

    /**
     * Carregar Top 10 Atores com Mais Filmes
     */
    @FXML
    private void carregarTop10Atores() {
        List<Map<String, Object>> dados = viewService.procurarTop10AtoresFilmes();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableTop10Atores.setItems(items);
        
        mostrarInfo("Top 10 Atores", dados.size() + " registros carregados");
    }

    /**
     * Carregar Países com Menos de 5 Filmes
     */
    @FXML
    private void carregarPaisesMenos5() {
        List<Map<String, Object>> dados = viewService.procurarPaisesMenos5Filmes();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tablePaisesMenos5.setItems(items);
        
        mostrarInfo("Países com Menos de 5 Filmes", dados.size() + " registros carregados");
    }

    /**
     * Carregar Continentes com Mais de 10 Filmes
     */
    @FXML
    private void carregarContinentesMais10() {
        List<Map<String, Object>> dados = viewService.procurarContinentesMais10Filmes();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableContinentesMais10.setItems(items);
        
        mostrarInfo("Continentes com Mais de 10 Filmes", dados.size() + " registros carregados");
    }

    /**
     * Exibir mensagem informativa
     */
    private void mostrarInfo(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
