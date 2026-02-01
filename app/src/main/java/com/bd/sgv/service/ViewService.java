package com.bd.sgv.service;

import com.bd.sgv.repository.ViewRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ViewService {
    private ViewRepository viewRepository;

    public ViewService() {
        this.viewRepository = new ViewRepository();
    }

    public List<Map<String, Object>> procurarTop5Diretores() {
        try {
            return viewRepository.procurarTop5Diretores();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes e diretores: " + e.getMessage());
            return List.of();
        }
    }

    public List<Map<String, Object>> procurarTop10AtoresFilmes() {
        try {
            return viewRepository.procurarTop10AtoresFilmes();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes e gêneros: " + e.getMessage());
            return List.of();
        }
    }

    public List<Map<String, Object>> procurarPaisesMenos5Filmes() {
        try {
            return viewRepository.procurarPaisesMenos5Filmes();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar atores e filmes: " + e.getMessage());
            return List.of();
        }
    }

    public List<Map<String, Object>> procurarContinentesMais10Filmes() {
        try {
            return viewRepository.procurarContinentesMais10Filmes();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar votos: " + e.getMessage());
            return List.of();
        }
    }
}
