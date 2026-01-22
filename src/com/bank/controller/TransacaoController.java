package com.bank.controller;

import com.bank.model.Transacao;
import com.bank.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transacao> listar() {
        return service.listar();
    }

    @PostMapping
    public Transacao salvar(@RequestBody Transacao t) {
        return service.salvar(t);
    }
}
