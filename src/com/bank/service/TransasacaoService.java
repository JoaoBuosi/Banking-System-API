package com.bank.service;

import com.bank.exception.ContaNotFoundException;
import com.bank.exception.SaldoInsuficienteException;
import com.bank.model.Conta;
import com.bank.model.Transacao;
import com.bank.repository.ContaRepository;
import com.bank.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    private Conta conta;

    @BeforeEach
    void setUp() {
        conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(100.0);
    }

    @Test
    void deveRealizarDepositoComSucesso() {
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(50.0);
        transacao.setTipo("DEPOSITO");

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao resultado = transacaoService.salvar(transacao);

        assertEquals(150.0, conta.getSaldo());
        verify(contaRepository).save(conta);
        verify(transacaoRepository).save(transacao);
    }

    @Test
    void deveRealizarSaqueComSucesso() {
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(30.0);
        transacao.setTipo("SAQUE");

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        transacaoService.salvar(transacao);

        assertEquals(70.0, conta.getSaldo());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(500.0);
        transacao.setTipo("SAQUE");

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

        assertThrows(SaldoInsuficienteException.class,
                () -> transacaoService.salvar(transacao));

        // Saldo não deve mudar
        assertEquals(100.0, conta.getSaldo());
        verify(contaRepository, never()).save(any());
        verify(transacaoRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoContaNaoExiste() {
        Conta contaInexistente = new Conta();
        contaInexistente.setId(999L);

        Transacao transacao = new Transacao();
        transacao.setConta(contaInexistente);
        transacao.setValor(50.0);
        transacao.setTipo("DEPOSITO");

        when(contaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ContaNotFoundException.class,
                () -> transacaoService.salvar(transacao));
    }

    @Test
    void deveLancarExcecaoQuandoValorForZeroOuNegativo() {
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(-10.0);
        transacao.setTipo("DEPOSITO");

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

        assertThrows(IllegalArgumentException.class,
                () -> transacaoService.salvar(transacao));
    }

    @Test
    void deveLancarExcecaoQuandoTipoForInvalido() {
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(50.0);
        transacao.setTipo("PIX_ALEATORIO");

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

        assertThrows(IllegalArgumentException.class,
                () -> transacaoService.salvar(transacao));
    }
}
