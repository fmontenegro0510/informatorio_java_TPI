package org.informatorio.domain;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres;

    public CuentaAhorro(Long idCuenta, String numeroCuenta, double saldo, String titular, Cliente cliente, double tasaInteres) {
        super(idCuenta, numeroCuenta, saldo, titular, cliente);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void calcularIntereses() {
        double intereses = getSaldo() * getTasaInteres();
        depositar(intereses);
        System.out.println("Intereses calculados y agregados al saldo.");
    }





}