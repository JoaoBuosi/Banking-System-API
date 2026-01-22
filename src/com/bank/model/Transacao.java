package com.bank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private LocalDateTime data;
    private String tipo; // DEPOSITO, SAQUE, TRANSFERENCIA

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
}
