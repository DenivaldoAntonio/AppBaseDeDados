package com.bd.sgv.repository;

import com.bd.sgv.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcedureRepository {
    private Connection connection;

    public ProcedureRepository() {
        this.connection = DbConnection.getInstance().getConnection();
    }

    /**
     * COUNT_MOVIES_MONTH_YEAR
     */
    public int contarFilmesMesAno(int mes, int ano) throws SQLException {
        String sql = "{CALL COUNT_MOVIES_MONTH_YEAR(?, ?)}";
        
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("TotalFilmes");
                }
            }
        }
        return 0;
    }

    /**
     * COUNT_MOVIES_DIRECTOR
     */
    public int contarFilmesDiretor(String nomeDiretor) throws SQLException {
        String sql = "{CALL COUNT_MOVIES_DIRECTOR(?)}";
        
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nomeDiretor);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("TotalFilmes");
                }
            }
        }
        return 0;
    }

    /**
     * COUNT_ACTORS_IN_2_YEARS
     */
    public int contarAtoresEntreDoisAnos(int ano1, int ano2) throws SQLException {
        String sql = "{CALL COUNT_ACTORS_IN_2_YEARS(?, ?)}";
        
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, ano1);
            stmt.setInt(2, ano2);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("TotalAtores");
                }
            }
        }
        return 0;
    }

    /**
     * GET_MOVIES_ACTOR_YEAR
     */
    public List<Map<String, Object>> procurarFilmesAtorAno(int ano, String nomeAtor) throws SQLException {
        String sql = "{CALL GET_MOVIES_ACTOR_YEAR(?, ?)}";
        List<Map<String, Object>> resultados = new ArrayList<>();
        
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, ano);
            stmt.setString(2, nomeAtor);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> filme = new HashMap<>();
                    filme.put("id_movie", rs.getInt("id_movie"));
                    filme.put("titulo", rs.getString("titulo"));
                    filme.put("ano", rs.getInt("ano"));
                    filme.put("duracao", rs.getBigDecimal("duracao"));
                    resultados.add(filme);
                }
            }
        }
        return resultados;
    }

    /**
     * GET_ACTORS_BY_DIRECTOR
     */
    public List<Map<String, Object>> procurarAtoresPorDiretor(int num, String nomeDiretor) throws SQLException {
        String sql = "{CALL GET_ACTORS_BY_DIRECTOR(?, ?)}";
        List<Map<String, Object>> resultados = new ArrayList<>();
        
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, num);
            stmt.setString(2, nomeDiretor);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> ator = new HashMap<>();
                    ator.put("nome", rs.getString("nome"));
                    ator.put("totalFilmes", rs.getInt("TotalFilmes"));
                    resultados.add(ator);
                }
            }
        }
        return resultados;
    }

    /**
     * TOP_MONTH_MOVIE_COUNT
     */
    public Map<String, Object> mesComMaisFilmes(int ano) throws SQLException {
        String sql = "{CALL TOP_MONTH_MOVIE_COUNT(?)}";
        
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, ano);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> resultado = new HashMap<>();
                    resultado.put("mes", rs.getInt("Mes"));
                    resultado.put("totalFilmes", rs.getInt("TotalFilmes"));
                    return resultado;
                }
            }
        }
        return null;
    }

    /**
     * TOP_VOTED_ACTORS
     */
    public List<Map<String, Object>> topAtoresMaisVotados(int num, int ano) throws SQLException {
        String sql = "{CALL TOP_VOTED_ACTORS(?, ?)}";
        List<Map<String, Object>> resultados = new ArrayList<>();

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, num);
            stmt.setInt(2, ano);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> ator = new HashMap<>();
                    ator.put("nome", rs.getString("nome"));
                    ator.put("totalVotos", rs.getInt("TotalVotos"));
                    resultados.add(ator);
                }
            }
        }
        return resultados;
    }

    /**
     * FIRST_ACTOR_ALPHABETICALLY
     */
    public String primeiroAtorAlfabeticamente(int ano) throws SQLException {
        String sql = "{CALL FIRST_ACTOR_ALPHABETICALLY(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, ano);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("PrimeiroAtor");
                }
            }
        }
        return null;
    }

    /**
     * MOVIES_WITH_MORE_THAN_N_VOTES
     */
    public List<Map<String, Object>> filmesComMaisDeNVotos(int numeroVotos) throws SQLException {
        String sql = "{CALL MOVIES_WITH_MORE_THAN_N_VOTES(?)}";
        List<Map<String, Object>> resultados = new ArrayList<>();

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, numeroVotos);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> filme = new HashMap<>();
                    filme.put("titulo", rs.getString("titulo"));
                    filme.put("ano", rs.getInt("ano"));
                    filme.put("totalVotos", rs.getInt("TotalVotos"));
                    resultados.add(filme);
                }
            }
        }
        return resultados;
    }
}
