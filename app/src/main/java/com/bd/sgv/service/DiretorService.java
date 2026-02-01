package com.bd.sgv.service;

import com.bd.sgv.model.Diretor;
import com.bd.sgv.repository.DiretorRepository;

import java.sql.SQLException;
import java.util.List;

public class DiretorService {
    private DiretorRepository diretorRepository;

    public DiretorService() {
        this.diretorRepository = new DiretorRepository();
    }

    /**
     * Listar todos os diretores
     */
    public List<Diretor> listarDiretores() {
        return listarDiretores(false);
    }

    /**
     * Listar diretores
     * @param includeHidden incluir diretores ocultos
     */
    public List<Diretor> listarDiretores(boolean includeHidden) {
        try {
            return diretorRepository.findAll(includeHidden);
        } catch (SQLException e) {
            System.err.println("Erro ao listar diretores: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * procurar diretor por ID
     */
    public Diretor procurarDiretorPorId(Integer id) {
        try {
            return diretorRepository.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao procurar diretor: " + e.getMessage());
            return null;
        }
    }

    /**
     * Criar novo diretor
     */
    public boolean criarDiretor(String nome) {
        try {
            // Validações
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }

            // Obter próximo ID
            Integer nextId = diretorRepository.getNextId();

            // Criar diretor
            Diretor diretor = new Diretor(nextId, nome.trim(), false);
            
            return diretorRepository.insert(diretor);
            
        } catch (SQLException e) {
            System.err.println("Erro ao criar diretor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualizar diretor
     */
    public boolean atualizarDiretor(Integer id, String nome) {
        try {
            Diretor diretor = diretorRepository.findById(id);
            if (diretor == null) {
                System.err.println("Diretor não encontrado");
                return false;
            }

            diretor.setNome(nome.trim());

            return diretorRepository.update(diretor);
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar diretor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Apagar diretor (Soft Delete via Trigger)
     */
    public boolean apagarDiretor(Integer id) {
        try {
            return diretorRepository.delete(id);
        } catch (SQLException e) {
            System.err.println("Erro ao apagar diretor: " + e.getMessage());
            return false;
        }
    }
}
