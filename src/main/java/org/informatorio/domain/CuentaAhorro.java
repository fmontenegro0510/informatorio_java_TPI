package org.informatorio.domain;

import org.informatorio.enums.TipoCuenta;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres = 0.1;

    public CuentaAhorro(Long idCuenta, String numeroCuenta, double saldo, String titular, Cliente cliente, double tasaInteres) {
        super(idCuenta, numeroCuenta, saldo, titular, cliente);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void calcularIntereses() {
     //   double intereses = this.getSaldo() * this.getTasaInteres();
      //  this.depositar(intereses);
       // System.out.println("Intereses calculados y agregados al saldo."
        double intereses = this.getSaldo() * this.getTasaInteres();
        this.depositar(intereses);
        System.out.println("Intereses calculados y agregados al saldo.");
    }


    @Override
    public TipoCuenta getTipoCuenta() {
        return TipoCuenta.AHORRO;  // Cambiar según el tipo específico de la cuenta

    }

    @Override
    public void depositar(double monto) {
        if (monto >= 0) {
            setSaldo(getSaldo() + monto);
            System.out.println("Deposito exitoso. Nuevo saldo: " + getSaldo());
        } else {
            System.out.println("Ocurrió un error al realizar el depósito, monto incorrecto.");
        }
    }

    @Override
    public void retirar(double monto) {
    //    if (monto <= getSaldo()) {
    //        setSaldo(getSaldo() - monto);
    //        System.out.println("Retiro exitoso. Nuevo saldo: " + getSaldo());
    //    } else {
    //        System.out.println("Saldo insuficiente para el retiro.");
     //   }
        if (monto >= 0 && monto <= getSaldo()) {
            setSaldo(getSaldo() - monto);
            System.out.println("Retiro exitoso. Nuevo saldo: " + getSaldo());
        } else {
            System.out.println("Monto incorrecto o saldo insuficiente para el retiro.");
        }

    }

    @Override
    public void consultarSaldo() {
        System.out.println("Saldo de la cuenta de ahorro: " + getSaldo());
    }
}