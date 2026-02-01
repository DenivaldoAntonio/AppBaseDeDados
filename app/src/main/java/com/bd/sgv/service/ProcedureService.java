package com.bd.sgv.service;

import com.bd.sgv.repository.ProcedureRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProcedureService {
    private ProcedureRepository procedureRepository;

    public ProcedureService() {
        this.procedureRepository = new ProcedureRepository();
    }

    public int contarFilmesMesAno(int mes, int ano) {
        try {
            return procedureRepository.contarFilmesMesAno(mes, ano);
        } catch (SQLException e) {
            System.err.println("Erro ao contar filmes: " + e.getMessage());
            return 0;
        }
    }

    public int contarFilmesDiretor(String nomeDiretor) {
        try {
            return procedureRepository.contarFilmesDiretor(nomeDiretor);
        } catch (SQLException e) {
            System.err.println("Erro ao contar filmes do diretor: " + e.getMessage());
            return 0;
        }
    }

    public int contarAtoresEntreDoisAnos(int ano1, int ano2) {
        try {
            return procedureRepository.contarAtoresEntreDoisAnos(ano1, ano2);
        } catch (SQLException e) {
            System.err.println("Erro ao contar atores: " + e.getMessage());
            return 0;
        }
    }

    public List<Map<String, Object>> procurarFilmesAtorAno(int ano, String nomeAtor) {
        try {
            return procedureRepository.procurarFilmesAtorAno(ano, nomeAtor);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes: " + e.getMessage());
            return List.of();
        }
    }

    public List<Map<String, Object>> procurarAtoresPorDiretor(int num, String nomeDiretor) {
        try {
            return procedureRepository.procurarAtoresPorDiretor(num, nomeDiretor);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar atores: " + e.getMessage());
            return List.of();
        }
    }

    public Map<String, Object> mesComMaisFilmes(int ano) {
        try {
            return procedureRepository.mesComMaisFilmes(ano);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar mês: " + e.getMessage());
            return null;
        }
    }

    public List<Map<String, Object>> topAtoresMaisVotados(int num, int ano) {
        try {
            return procedureRepository.topAtoresMaisVotados(num, ano);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar atores votados: " + e.getMessage());
            return List.of();
        }
    }

    public String primeiroAtorAlfabeticamente(int ano) {
        try {
            return procedureRepository.primeiroAtorAlfabeticamente(ano);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar ator: " + e.getMessage());
            return null;
        }
    }

    public List<Map<String, Object>> filmesComMaisDeNVotos(int numeroVotos) {
        try {
            return procedureRepository.filmesComMaisDeNVotos(numeroVotos);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar filmes: " + e.getMessage());
            return List.of();
        }
    }
}
