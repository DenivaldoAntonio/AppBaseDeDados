package com.bd.sgv.repository;

import com.bd.sgv.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewRepository {
    private final Connection connection;

    public ViewRepository() {
        this.connection = DbConnection.getInstance().getConnection();
    }

    /**
     * VIEW: VW_TOP_5_DIRETORES_MAIS_FILMES
     */
    public List<Map<String, Object>> procurarTop5Diretores() throws SQLException {
        String sql = "SELECT * FROM VW_TOP_5_DIRETORES_MAIS_FILMES ORDER BY TotalFilmes DESC";
        return executeQuery(sql, "Diretor", "TotalFilmes");
    }

    /**
     * VIEW: VW_TOP_10_ATORES_FILMES
     */
    public List<Map<String, Object>> procurarTop10AtoresFilmes() throws SQLException {
        String sql = "SELECT * FROM VW_TOP_10_ATORES_FILMES ORDER BY TotalFilmes DESC";
        return executeQuery(sql, "Ator", "TotalFilmes");
    }

    /**
     * VIEW: VW_PAISES_MENOS_5_FILMES
     */
    public List<Map<String, Object>> procurarPaisesMenos5Filmes() throws SQLException {
        String sql = "SELECT * FROM VW_PAISES_MENOS_5_FILMES ORDER BY Pais";
        return executeQuery(sql, "Pais", "TotalFilmes");
    }

    /**
     * VIEW: VW_CONTINENTES_MAIS_10_FILMES
     */
    public List<Map<String, Object>> procurarContinentesMais10Filmes() throws SQLException {
        String sql = "SELECT * FROM VW_CONTINENTES_MAIS_10_FILMES ORDER BY TotalFilmes DESC";
        return executeQuery(sql, "Continente", "TotalFilmes");
    }

    /**
     * Correr query e ter resultados em lista de mapas
     */
    private List<Map<String, Object>> executeQuery(String sql, String keyField, String valueField) throws SQLException {
        List<Map<String, Object>> resultados = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put(keyField, rs.getString(keyField));
                row.put(valueField, rs.getInt(valueField));
                resultados.add(row);
            }
        }
        return resultados;
    }
}
