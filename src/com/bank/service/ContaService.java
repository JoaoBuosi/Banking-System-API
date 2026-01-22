package com.bank.service;

import com.bank.model.Conta;
import com.bank.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public Conta salvar(Conta c) {
        return repository.save(c);
    }

    public List<Conta> listar() {
        return repository.findAll();
    }

    public Optional<Conta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
