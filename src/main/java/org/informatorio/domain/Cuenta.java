package org.informatorio.domain;

import org.informatorio.enums.TipoCuenta;

public abstract class Cuenta {
    private Long idCuenta;
    private String numeroCuenta;
    private double saldo;
    private String titular;
    private Cliente cliente;

    public Cuenta(Long idCuenta, String numeroCuenta, double saldo, String titular, Cliente cliente) {
        this.idCuenta = idCuenta;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.titular = titular;
        this.cliente = cliente;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public abstract TipoCuenta getTipoCuenta();

    // Métodos específicos de operaciones bancarias
    public abstract void depositar(double monto);

    public abstract void retirar(double monto);

    public abstract void consultarSaldo();
}