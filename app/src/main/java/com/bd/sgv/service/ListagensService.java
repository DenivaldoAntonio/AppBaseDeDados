package com.bd.sgv.service;

import com.bd.sgv.repository.ListagensRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListagensService {

    private final ListagensRepository repository;

    public ListagensService() {
        this.repository = new ListagensRepository();
    }

    public List<Map<String, Object>> procurarFilmesAction() {
        try {
            return repository.procurarFilmesAction();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes de Action: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarDiretoresPortugal() {
        try {
            return repository.procurarDiretoresPortugal();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar diretores de Portugal: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarAtoresMasculinosAsia() {
        try {
            return repository.procurarAtoresMasculinosAsia();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar atores masculinos da Ásia: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarFilmesVerao() {
        try {
            return repository.procurarFilmesVerao();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes do verão: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarActionEuropaDesembro() {
        try {
            return repository.procurarActionEuropaDesembro();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes Action da Europa em dezembro: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarFilmes18Mais() {
        try {
            return repository.procurarFilmes18Mais();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes 18+: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarContinentesFilmesMenos10() {
        try {
            return repository.procurarContinentesFilmesMenos10();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar continentes com filmes -10: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarPaisesEuropa18Mais() {
        try {
            return repository.procurarPaisesEuropa18Mais();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar países da Europa com filmes 18+: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Map<String, Object>> procurarTop10DiretoresRating() {
        try {
            return repository.procurarTop10DiretoresRating();
        } catch (SQLException e) {
            System.err.println("Erro ao procurar top 10 diretores por rating: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
