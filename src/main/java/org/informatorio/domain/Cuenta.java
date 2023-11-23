package org.informatorio.domain;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
    }

    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("Fondos insuficientes para realizar el retiro.");
        }
    }
}