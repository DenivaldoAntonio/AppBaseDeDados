package com.bd.sgv.service;

import com.bd.sgv.model.Ator;
import com.bd.sgv.repository.AtorRepository;

import java.sql.SQLException;
import java.util.List;

public class AtorService {
    private AtorRepository atorRepository;

    public AtorService() {
        this.atorRepository = new AtorRepository();
    }

    /**
     * Listar todos os atores
     */
    public List<Ator> listarAtores() {
        try {
            return atorRepository.findAll();
        } catch (SQLException e) {
            System.err.println("Erro ao listar atores: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Procurar ator por ID
     */
    public Ator procurarAtorPorId(Integer id) {
        try {
            return atorRepository.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar ator: " + e.getMessage());
            return null;
        }
    }

    /**
     * Criar ator
     */
    public boolean criarAtor(String nome, Character sexo) {
        try {
            // Validações
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }
            
            if (sexo != null && sexo != 'M' && sexo != 'F') {
                throw new IllegalArgumentException("Sexo deve ser 'M' ou 'F'");
            }

            // Obter próximo ID
            Integer nextId = atorRepository.getNextId();

            // Criar ator
            Ator ator = new Ator(nextId, nome.trim(), sexo);
            
            return atorRepository.insert(ator);
            
        } catch (SQLException e) {
            System.err.println("Erro ao criar ator: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualizar ator
     */
    public boolean atualizarAtor(Integer id, String nome, Character sexo) {
        try {
            Ator ator = atorRepository.findById(id);
            if (ator == null) {
                System.err.println("Ator não encontrado");
                return false;
            }

            ator.setNome(nome.trim());
            ator.setSexo(sexo);

            return atorRepository.update(ator);
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ator: " + e.getMessage());
            return false;
        }
    }

    /**
     * Apagar ator
     */
    public boolean apagarAtor(Integer id) {
        try {
            return atorRepository.delete(id);
        } catch (SQLException e) {
            System.err.println("Erro ao apagar ator: " + e.getMessage());
            return false;
        }
    }
}
