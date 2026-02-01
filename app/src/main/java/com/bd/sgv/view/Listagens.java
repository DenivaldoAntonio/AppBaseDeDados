package com.bd.sgv.view;

import com.bd.sgv.service.ListagensService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;

import java.util.List;
import java.util.Map;

public class Listagens{

    @FXML private TabPane tabPane;
    
    // Tab 1: Filmes Action
    @FXML private TableView<Map> tableFilmesAction;
    @FXML private Button btnCarregarFilmesAction;
    
    // Tab 2: Diretores Portugal
    @FXML private TableView<Map> tableDiretoresPortugal;
    @FXML private Button btnCarregarDiretoresPortugal;
    
    // Tab 3: Atores Masculinos Ásia
    @FXML private TableView<Map> tableAtoresMasculinosAsia;
    @FXML private Button btnCarregarAtoresMasculinosAsia;
    
    // Tab 4: Filmes Verão
    @FXML private TableView<Map> tableFilmesVerao;
    @FXML private Button btnCarregarFilmesVerao;
    
    // Tab 5: Action Europa Dezembro
    @FXML private TableView<Map> tableActionEuropaDesembro;
    @FXML private Button btnCarregarActionEuropaDesembro;
    
    // Tab 6: Filmes 18+
    @FXML private TableView<Map> tableFilmes18Mais;
    @FXML private Button btnCarregarFilmes18Mais;
    
    // Tab 7: Continentes Filmes -10
    @FXML private TableView<Map> tableContinentesFilmesMenos10;
    @FXML private Button btnCarregarContinentesFilmesMenos10;
    
    // Tab 8: Países Europa 18+
    @FXML private TableView<Map> tablePaisesEuropa18Mais;
    @FXML private Button btnCarregarPaisesEuropa18Mais;
    
    // Tab 9: Top 10 Diretores Rating
    @FXML private TableView<Map> tableTop10DiretoresRating;
    @FXML private Button btnCarregarTop10DiretoresRating;

    private ListagensService service;

    @FXML
    public void initialize() {
        service = new ListagensService();
        configurarTabelas();
        configurarBotoes();
    }

    private void configurarTabelas() {
        configurarTabelaFilmesAction();
        configurarTabelaDiretoresPortugal();
        configurarTabelaAtoresMasculinosAsia();
        configurarTabelaFilmesVerao();
        configurarTabelaActionEuropaDesembro();
        configurarTabelaFilmes18Mais();
        configurarTabelaContinentesFilmesMenos10();
        configurarTabelaPaisesEuropa18Mais();
        configurarTabelaTop10DiretoresRating();
    }

    private void configurarTabelaFilmesAction() {
        TableColumn<Map, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new MapValueFactory<>("titulo"));
        colTitulo.setPrefWidth(400);
        
        TableColumn<Map, Integer> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(new MapValueFactory<>("ano_lancamento"));
        colAno.setPrefWidth(80);
        
        tableFilmesAction.getColumns().addAll(colTitulo, colAno);
    }

    private void configurarTabelaDiretoresPortugal() {
        TableColumn<Map, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new MapValueFactory<>("nome"));
        colNome.setPrefWidth(300);
        

        
        tableDiretoresPortugal.getColumns().addAll(colNome);
    }

    private void configurarTabelaAtoresMasculinosAsia() {
        TableColumn<Map, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new MapValueFactory<>("nome"));
        colNome.setPrefWidth(300);
        
        TableColumn<Map, String> colSexo = new TableColumn<>("Sexo");
        colSexo.setCellValueFactory(new MapValueFactory<>("sexo"));
        colSexo.setPrefWidth(80);
        
        tableAtoresMasculinosAsia.getColumns().addAll(colNome, colSexo);
    }

    private void configurarTabelaFilmesVerao() {
        TableColumn<Map, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new MapValueFactory<>("titulo"));
        colTitulo.setPrefWidth(350);
        
        TableColumn<Map, Object> colData = new TableColumn<>("Data Lançamento");
        colData.setCellValueFactory(new MapValueFactory<>("data_lancamento"));
        colData.setPrefWidth(150);
        
        tableFilmesVerao.getColumns().addAll(colTitulo, colData);
    }

    private void configurarTabelaActionEuropaDesembro() {
        TableColumn<Map, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new MapValueFactory<>("titulo"));
        colTitulo.setPrefWidth(400);
        
        TableColumn<Map, Object> colData = new TableColumn<>("Data Lançamento");
        colData.setCellValueFactory(new MapValueFactory<>("data_lancamento"));
        colData.setPrefWidth(150);
        
        tableActionEuropaDesembro.getColumns().addAll(colTitulo, colData);
    }

    private void configurarTabelaFilmes18Mais() {
        TableColumn<Map, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new MapValueFactory<>("titulo"));
        colTitulo.setPrefWidth(400);
        
        TableColumn<Map, Integer> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(new MapValueFactory<>("ano_lancamento"));
        colAno.setPrefWidth(80);
        
        tableFilmes18Mais.getColumns().addAll(colTitulo, colAno);
    }

    private void configurarTabelaContinentesFilmesMenos10() {
        TableColumn<Map, String> colContinente = new TableColumn<>("Continente");
        colContinente.setCellValueFactory(new MapValueFactory<>("Continente"));
        colContinente.setPrefWidth(300);
        
        TableColumn<Map, Integer> colTotal = new TableColumn<>("Total Vídeos");
        colTotal.setCellValueFactory(new MapValueFactory<>("TotalVideos"));
        colTotal.setPrefWidth(150);
        
        tableContinentesFilmesMenos10.getColumns().addAll(colContinente, colTotal);
    }

    private void configurarTabelaPaisesEuropa18Mais() {
        TableColumn<Map, String> colPais = new TableColumn<>("País");
        colPais.setCellValueFactory(new MapValueFactory<>("Pais"));
        colPais.setPrefWidth(300);
        
        TableColumn<Map, Integer> colTotal = new TableColumn<>("Total Vídeos");
        colTotal.setCellValueFactory(new MapValueFactory<>("TotalVideos"));
        colTotal.setPrefWidth(150);
        
        tablePaisesEuropa18Mais.getColumns().addAll(colPais, colTotal);
    }

    private void configurarTabelaTop10DiretoresRating() {
        TableColumn<Map, String> colDiretor = new TableColumn<>("Diretor");
        colDiretor.setCellValueFactory(new MapValueFactory<>("Diretor"));
        colDiretor.setPrefWidth(300);
        
        TableColumn<Map, Double> colRating = new TableColumn<>("Rating Médio");
        colRating.setCellValueFactory(new MapValueFactory<>("RatingMedio"));
        colRating.setPrefWidth(150);
        
        tableTop10DiretoresRating.getColumns().addAll(colDiretor, colRating);
    }

    private void configurarBotoes() {
        btnCarregarFilmesAction.setOnAction(e -> carregarFilmesAction());
        btnCarregarDiretoresPortugal.setOnAction(e -> carregarDiretoresPortugal());
        btnCarregarAtoresMasculinosAsia.setOnAction(e -> carregarAtoresMasculinosAsia());
        btnCarregarFilmesVerao.setOnAction(e -> carregarFilmesVerao());
        btnCarregarActionEuropaDesembro.setOnAction(e -> carregarActionEuropaDesembro());
        btnCarregarFilmes18Mais.setOnAction(e -> carregarFilmes18Mais());
        btnCarregarContinentesFilmesMenos10.setOnAction(e -> carregarContinentesFilmesMenos10());
        btnCarregarPaisesEuropa18Mais.setOnAction(e -> carregarPaisesEuropa18Mais());
        btnCarregarTop10DiretoresRating.setOnAction(e -> carregarTop10DiretoresRating());
    }

    // === MÉTODOS DE CARREGAMENTO ===

    @FXML
    private void carregarFilmesAction() {
        List<Map<String, Object>> dados = service.procurarFilmesAction();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableFilmesAction.setItems(items);
        mostrarInfo("Filmes de Action", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarDiretoresPortugal() {
        List<Map<String, Object>> dados = service.procurarDiretoresPortugal();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableDiretoresPortugal.setItems(items);
        mostrarInfo("Diretores de Portugal", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarAtoresMasculinosAsia() {
        List<Map<String, Object>> dados = service.procurarAtoresMasculinosAsia();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableAtoresMasculinosAsia.setItems(items);
        mostrarInfo("Atores Masculinos da Ásia", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarFilmesVerao() {
        List<Map<String, Object>> dados = service.procurarFilmesVerao();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableFilmesVerao.setItems(items);
        mostrarInfo("Filmes do Verão", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarActionEuropaDesembro() {
        List<Map<String, Object>> dados = service.procurarActionEuropaDesembro();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableActionEuropaDesembro.setItems(items);
        mostrarInfo("Action Europa Dezembro", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarFilmes18Mais() {
        List<Map<String, Object>> dados = service.procurarFilmes18Mais();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableFilmes18Mais.setItems(items);
        mostrarInfo("Filmes 18+", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarContinentesFilmesMenos10() {
        List<Map<String, Object>> dados = service.procurarContinentesFilmesMenos10();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableContinentesFilmesMenos10.setItems(items);
        mostrarInfo("Continentes com Filmes -10", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarPaisesEuropa18Mais() {
        List<Map<String, Object>> dados = service.procurarPaisesEuropa18Mais();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tablePaisesEuropa18Mais.setItems(items);
        mostrarInfo("Países Europa 18+", dados.size() + " registros carregados");
    }

    @FXML
    private void carregarTop10DiretoresRating() {
        List<Map<String, Object>> dados = service.procurarTop10DiretoresRating();
        ObservableList<Map> items = FXCollections.observableArrayList(dados);
        tableTop10DiretoresRating.setItems(items);
        mostrarInfo("Top 10 Diretores por Rating", dados.size() + " registros carregados");
    }

    private void mostrarInfo(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
