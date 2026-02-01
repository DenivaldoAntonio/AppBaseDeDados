package com.bd.sgv.repository;


import com.bd.sgv.model.Diretor;
import com.bd.sgv.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiretorRepository {
    private Connection connection;

    public DiretorRepository() {
        this.connection = DbConnection.getInstance().getConnection();
    }

    /**
     * procurar todos os diretores (excluindo hidden)
     */
    public List<Diretor> findAll() throws SQLException {
        return findAll(false);
    }

    /**
     * procurar todos os diretores
     * @param includeHidden incluir diretores ocultos
     */
    public List<Diretor> findAll(boolean includeHidden) throws SQLException {
        List<Diretor> diretores = new ArrayList<>();
        String sql = includeHidden 
            ? "SELECT id_diretor, nome, hidden FROM Diretor ORDER BY nome"
            : "SELECT id_diretor, nome, hidden FROM Diretor WHERE hidden = 0 ORDER BY nome";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Diretor diretor = new Diretor();
                diretor.setIdDiretor(rs.getInt("id_diretor"));
                diretor.setNome(rs.getString("nome"));
                diretor.setHidden(rs.getBoolean("hidden"));
                diretores.add(diretor);
            }
        }
        return diretores;
    }

    /**
     * procurar diretor por ID
     */
    public Diretor findById(Integer id) throws SQLException {
        String sql = "SELECT id_diretor, nome, hidden FROM Diretor WHERE id_diretor = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Diretor diretor = new Diretor();
                    diretor.setIdDiretor(rs.getInt("id_diretor"));
                    diretor.setNome(rs.getString("nome"));
                    diretor.setHidden(rs.getBoolean("hidden"));
                    return diretor;
                }
            }
        }
        return null;
    }

    /**
     * Inserir novo diretor
     */
    public boolean insert(Diretor diretor) throws SQLException {
        String sql = "INSERT INTO Diretor (id_diretor, nome, hidden) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, diretor.getIdDiretor());
            pstmt.setString(2, diretor.getNome());
            pstmt.setBoolean(3, diretor.getHidden() != null ? diretor.getHidden() : false);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Atualizar diretor
     */
    public boolean update(Diretor diretor) throws SQLException {
        String sql = "UPDATE Diretor SET nome = ?, hidden = ? WHERE id_diretor = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, diretor.getNome());
            pstmt.setBoolean(2, diretor.getHidden() != null ? diretor.getHidden() : false);
            pstmt.setInt(3, diretor.getIdDiretor());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Apagar diretor (TRIGGER fará soft delete)
     */
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Diretor WHERE id_diretor = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * procurar próximo ID disponível
     */
    public Integer getNextId() throws SQLException {
        String sql = "SELECT ISNULL(MAX(id_diretor), 0) + 1 AS next_id FROM Diretor";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("next_id");
            }
        }
        return 1;
    }
}


