package com.bd.sgv.repository;

import  com.bd.sgv.model.Ator;
import  com.bd.sgv.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtorRepository{
    private Connection connection;

    public AtorRepository(){
        this.connection = DbConnection.getInstance().getConnection();
    }

    /**
     * procurar todos os atores
     */
    public List <Ator> findAll() throws SQLException{
        List <Ator> atores = new ArrayList <>(); String sql = "SELECT id_ator, nome, sexo FROM Ator ORDER BY id_ator";
        try(Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                Ator ator = new Ator();
                ator.setIdAtor(rs.getInt("id_ator"));
                ator.setNome(rs.getString("nome"));
                String sexoStr = rs.getString("sexo");
                if(sexoStr != null && !sexoStr.isEmpty()){
                    ator.setSexo(sexoStr.charAt(0));
                } atores.add(ator);
            }
        }
        return atores;
    }

    /**
     * procurar ator por ID
     */
    public Ator findById(Integer id) throws SQLException{
        String sql = "SELECT id_ator, nome, sexo FROM Ator WHERE id_ator = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id); try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    Ator ator = new Ator(); ator.setIdAtor(rs.getInt("id_ator")); ator.setNome(rs.getString("nome"));
                    String sexoStr = rs.getString("sexo"); if(sexoStr != null && !sexoStr.isEmpty()){
                        ator.setSexo(sexoStr.charAt(0));
                    } return ator;
                }
            }
        } return null;
    }

    /**
     * Inserir novo ator (TRIGGER será ativado)
     */
    public boolean insert(Ator ator) throws SQLException{
        String sql = "INSERT INTO Ator (id_ator, nome, sexo) VALUES (?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, ator.getIdAtor()); pstmt.setString(2, ator.getNome()); if(ator.getSexo() != null){
                pstmt.setString(3, String.valueOf(ator.getSexo()));
            } else{
                pstmt.setNull(3, Types.CHAR);
            } int rowsAffected = pstmt.executeUpdate(); return rowsAffected > 0;
        }
    }

    /**
     * Atualizar ator
     */
    public boolean update(Ator ator) throws SQLException{
        String sql = "UPDATE Ator SET nome = ?, sexo = ? WHERE id_ator = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, ator.getNome()); if(ator.getSexo() != null){
                pstmt.setString(2, String.valueOf(ator.getSexo()));
            } else{
                pstmt.setNull(2, Types.CHAR);
            } pstmt.setInt(3, ator.getIdAtor()); int rowsAffected = pstmt.executeUpdate(); return rowsAffected > 0;
        }
    }

    /**
     * Apagar ator
     */
    public boolean delete(Integer id) throws SQLException{
        String sql = "DELETE FROM Ator WHERE id_ator = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id); int rowsAffected = pstmt.executeUpdate(); return rowsAffected > 0;
        }
    }

    /**
     * procurar próximo ID disponível
     */
    public Integer getNextId() throws SQLException{
        String sql = "SELECT ISNULL(MAX(id_ator), 0) + 1 AS next_id FROM Ator";
        try(Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            if(rs.next()){
                return rs.getInt("next_id");
            }
        } return 1;
    }
}
