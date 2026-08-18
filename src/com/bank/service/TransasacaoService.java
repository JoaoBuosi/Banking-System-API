package com.bank.service;

import com.bank.exception.ContaNotFoundException;
import com.bank.exception.SaldoInsuficienteException;
import com.bank.model.Conta;
import com.bank.model.Transacao;
import com.bank.repository.ContaRepository;
import com.bank.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;
    private final ContaRepository contaRepository;

    public TransacaoService(TransacaoRepository repository, ContaRepository contaRepository) {
        this.repository = repository;
        this.contaRepository = contaRepository;
    }

    @Transactional
    public Transacao salvar(Transacao t) {
        Long contaId = t.getConta().getId();

        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada: " + contaId));

        if (t.getValor() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero");
        }

        switch (t.getTipo().toUpperCase()) {
            case "DEPOSITO":
                conta.setSaldo(conta.getSaldo() + t.getValor());
                break;

            case "SAQUE":
                if (conta.getSaldo() < t.getValor()) {
                    throw new SaldoInsuficienteException(
                            "Saldo insuficiente. Saldo atual: " + conta.getSaldo());
                }
                conta.setSaldo(conta.getSaldo() - t.getValor());
                break;

            default:
                throw new IllegalArgumentException("Tipo de transação inválido: " + t.getTipo());
        }

        contaRepository.save(conta);

        t.setData(LocalDateTime.now());
        t.setConta(conta);
        return repository.save(t);
    }

    public List<Transacao> listar() {
        return repository.findAll();
    }

    public Optional<Transacao> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
