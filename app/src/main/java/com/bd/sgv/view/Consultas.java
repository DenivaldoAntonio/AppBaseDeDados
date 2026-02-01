package com.bd.sgv.view;

import com.bd.sgv.service.ProcedureService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.Notifications;

import java.util.List;
import java.util.Map;

public class Consultas{

    @FXML
    private TabPane tabPane;
    
    // Tab 1: Contar filmes por mês/ano
    @FXML
    private Spinner<Integer> spinnerMes;
    @FXML
    private Spinner<Integer> spinnerAnoMes;
    @FXML
    private Label lblResultadoMes;
    @FXML
    private Button btnConsultarMes;
    
    // Tab 2: Contar filmes por diretor
    @FXML
    private TextField txtDiretor;
    @FXML
    private Label lblResultadoDiretor;
    @FXML
    private Button btnConsultarDiretor;
    
    // Tab 3: Contar atores entre dois anos
    @FXML
    private Spinner<Integer> spinnerAno1;
    @FXML
    private Spinner<Integer> spinnerAno2;
    @FXML
    private Label lblResultadoAtores;
    @FXML
    private Button btnConsultarAtores;
    
    // Tab 4: Filmes de um ator em um ano
    @FXML
    private Spinner<Integer> spinnerAnoAtor;
    @FXML
    private TextField txtAtor;
    @FXML
    private TextArea txtResultadoFilmesAtor;
    @FXML
    private Button btnConsultarFilmesAtor;
    
    // Tab 5: Top atores por diretor
    @FXML
    private Spinner<Integer> spinnerNumAtores;
    @FXML
    private TextField txtDiretorAtores;
    @FXML
    private TextArea txtResultadoAtoresDiretor;
    @FXML
    private Button btnConsultarAtoresDiretor;
    
    // Tab 6: Mês com mais filmes
    @FXML
    private Spinner<Integer> spinnerAnoMesMais;
    @FXML
    private Label lblResultadoMesMais;
    @FXML
    private Button btnConsultarMesMais;

    private ProcedureService procedureService;

    @FXML
    public void initialize() {
        procedureService = new ProcedureService();
        configurarSpinners();
        configurarBotoes();
    }

    private void configurarSpinners() {
        // Spinner Mês (1-12)
        spinnerMes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        
        // Spinners Ano
        int anoAtual = java.time.Year.now().getValue();
        spinnerAnoMes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, anoAtual, anoAtual));
        spinnerAno1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, anoAtual, 2000));
        spinnerAno2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, anoAtual, 2020));
        spinnerAnoAtor.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, anoAtual, anoAtual));
        spinnerAnoMesMais.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, anoAtual, anoAtual));
        
        // Spinner Número de atores
        spinnerNumAtores.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 5));
    }

    private void configurarBotoes() {
        btnConsultarMes.setOnAction(e -> consultarFilmesMesAno());
        btnConsultarDiretor.setOnAction(e -> consultarFilmesDiretor());
        btnConsultarAtores.setOnAction(e -> consultarAtoresEntreDoisAnos());
        btnConsultarFilmesAtor.setOnAction(e -> consultarFilmesAtor());
        btnConsultarAtoresDiretor.setOnAction(e -> consultarAtoresPorDiretor());
        btnConsultarMesMais.setOnAction(e -> consultarMesComMaisFilmes());
    }

    // ===== CONSULTAS =====

    @FXML
    private void consultarFilmesMesAno() {
        int mes = spinnerMes.getValue();
        int ano = spinnerAnoMes.getValue();
        
        int total = procedureService.contarFilmesMesAno(mes, ano);
        
        lblResultadoMes.setText("Total de filmes: " + total);
        
        Notifications.create()
                .title("Consulta Executada")
                .text("Procedure COUNT_MOVIES_MONTH_YEAR executada!")
                .showInformation();
    }

    @FXML
    private void consultarFilmesDiretor() {
        String diretor = txtDiretor.getText().trim();
        
        if (diretor.isEmpty()) {
            mostrarAviso("Digite o nome do diretor");
            return;
        }
        
        int total = procedureService.contarFilmesDiretor(diretor);
        
        lblResultadoDiretor.setText("Total de filmes: " + total);
        
        Notifications.create()
                .title("Consulta Executada")
                .text("Procedure COUNT_MOVIES_DIRECTOR executada!")
                .showInformation();
    }

    @FXML
    private void consultarAtoresEntreDoisAnos() {
        int ano1 = spinnerAno1.getValue();
        int ano2 = spinnerAno2.getValue();
        
        int total = procedureService.contarAtoresEntreDoisAnos(ano1, ano2);
        
        lblResultadoAtores.setText("Total de atores: " + total);
        
        Notifications.create()
                .title("Consulta Executada")
                .text("Procedure COUNT_ACTORS_IN_2_YEARS executada!")
                .showInformation();
    }

    @FXML
    private void consultarFilmesAtor() {
        int ano = spinnerAnoAtor.getValue();
        String ator = txtAtor.getText().trim();
        
        if (ator.isEmpty()) {
            mostrarAviso("Digite o nome do ator");
            return;
        }
        
        List<Map<String, Object>> filmes = procedureService.procurarFilmesAtorAno(ano, ator);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Filmes encontrados: ").append(filmes.size()).append("\n\n");
        
        for (Map<String, Object> filme : filmes) {
            sb.append("ID: ").append(filme.get("id_movie"))
              .append(" | Título: ").append(filme.get("titulo"))
              .append(" | Ano: ").append(filme.get("ano"))
              .append(" | Duração: ").append(filme.get("duracao")).append("h")
              .append("\n");
        }
        
        txtResultadoFilmesAtor.setText(sb.toString());
        
        Notifications.create()
                .title("Consulta Executada")
                .text("Procedure GET_MOVIES_ACTOR_YEAR executada!")
                .showInformation();
    }

    @FXML
    private void consultarAtoresPorDiretor() {
        int num = spinnerNumAtores.getValue();
        String diretor = txtDiretorAtores.getText().trim();
        
        if (diretor.isEmpty()) {
            mostrarAviso("Digite o nome do diretor");
            return;
        }
        
        List<Map<String, Object>> atores = procedureService.procurarAtoresPorDiretor(num, diretor);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Top ").append(num).append(" atores encontrados: ").append(atores.size()).append("\n\n");
        
        int posicao = 1;
        for (Map<String, Object> ator : atores) {
            sb.append(posicao++).append(". ")
              .append(ator.get("nome"))
              .append(" - Total de Filmes: ").append(ator.get("totalFilmes"))
              .append("\n");
        }
        
        txtResultadoAtoresDiretor.setText(sb.toString());
        
        Notifications.create()
                .title("Consulta Executada")
                .text("Procedure GET_ACTORS_BY_DIRECTOR executada!")
                .showInformation();
    }

    @FXML
    private void consultarMesComMaisFilmes() {
        int ano = spinnerAnoMesMais.getValue();
        
        Map<String, Object> resultado = procedureService.mesComMaisFilmes(ano);
        
        if (resultado != null) {
            int mes = (Integer) resultado.get("mes");
            int total = (Integer) resultado.get("totalFilmes");
            
            String[] meses = {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
            
            lblResultadoMesMais.setText(meses[mes] + " com " + total + " filmes");
        } else {
            lblResultadoMesMais.setText("Nenhum resultado encontrado");
        }
        
        Notifications.create()
                .title("Consulta Executada")
                .text("Procedure TOP_MONTH_MOVIE_COUNT executada!")
                .showInformation();
    }

    private void mostrarAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
