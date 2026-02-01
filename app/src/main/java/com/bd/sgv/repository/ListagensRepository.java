package com.bd.sgv.repository;

import com.bd.sgv.util.DbConnection;

import java.sql.*;
import java.util.*;

public class ListagensRepository {

    /**
     * 1. Procurar Filmes do Gênero Action
     */
    public List<Map<String, Object>> procurarFilmesAction() throws SQLException {
        String sql = "SELECT * FROM VW_FILMES_ACTION ORDER BY titulo";
        return executeQuery(sql);
    }

    /**
     * 2. Procurar Diretores de Filmes de Portugal
     */
    public List<Map<String, Object>> procurarDiretoresPortugal() throws SQLException {
        String sql = "SELECT * FROM VW_DIRETORES_PORTUGAL ORDER BY nome";
        return executeQuery(sql);
    }

    /**
     * 3. Procurar Atores Masculinos de Filmes da Ásia
     */
    public List<Map<String, Object>> procurarAtoresMasculinosAsia() throws SQLException {
        String sql = "SELECT * FROM VW_ATORES_MASCULINOS_ASIA ORDER BY nome";
        return executeQuery(sql);
    }

    /**
     * 4. Procurar Filmes Lançados no Verão
     */
    public List<Map<String, Object>> procurarFilmesVerao() throws SQLException {
        String sql = "SELECT * FROM VW_FILMES_VERAO ORDER BY data_lancamento";
        return executeQuery(sql);
    }

    /**
     * 5. Procurar Filmes de Action da Europa Lançados em Dezembro
     */
    public List<Map<String, Object>> procurarActionEuropaDesembro() throws SQLException {
        String sql = "SELECT * FROM VW_ACTION_EUROPA_DEZEMBRO ORDER BY titulo";
        return executeQuery(sql);
    }

    /**
     * 6. Procurar Filmes com Classificação 18+
     */
    public List<Map<String, Object>> procurarFilmes18Mais() throws SQLException {
        String sql = "SELECT * FROM VW_FILMES_18_MAIS ORDER BY titulo";
        return executeQuery(sql);
    }

    /**
     * 7. Procurar Continentes com Filmes -10
     */
    public List<Map<String, Object>> procurarContinentesFilmesMenos10() throws SQLException {
        String sql = "SELECT * FROM VW_CONTINENTES_FILMES_MENOS_10 ORDER BY TotalVideos DESC";
        return executeQuery(sql);
    }

    /**
     * 8. Procurar Países da Europa com Filmes 18+
     */
    public List<Map<String, Object>> procurarPaisesEuropa18Mais() throws SQLException {
        String sql = "SELECT * FROM VW_PAISES_EUROPA_18_MAIS ORDER BY TotalVideos DESC";
        return executeQuery(sql);
    }

    /**
     * 9. Procurar Top 10 Diretores com Maior Rating Médio
     */
    public List<Map<String, Object>> procurarTop10DiretoresRating() throws SQLException {
        String sql = "SELECT * FROM VW_TOP_10_DIRETORES_RATING";
        return executeQuery(sql);
    }

    /**
     * Método genérico para executar queries e retornar resultados como lista de mapas
     */
    private List<Map<String, Object>> executeQuery(String sql) throws SQLException {
        List<Map<String, Object>> resultados = new ArrayList<>();
        
        try (Connection conn = DbConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                resultados.add(row);
            }
        }
        
        return resultados;
    }
}
