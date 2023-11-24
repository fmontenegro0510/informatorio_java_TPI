package org.informatorio.domain;

import org.informatorio.enums.TipoCuenta;

public class Cuenta {
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

    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
    }

    public void retirar(double monto) {
        if (monto <= saldo) {
            this.setSaldo(this.getSaldo() - monto);
            System.out.println("Retiro exitoso. Nuevo saldo: " + this.getSaldo());
        } else {
            System.out.println("Fondos insuficientes para realizar el retiro.");
        }
    }

    public TipoCuenta getTipoCuenta() {
        return TipoCuenta.INDEFINIDA;  // Cambiar según el tipo específico de la cuenta
    }
}