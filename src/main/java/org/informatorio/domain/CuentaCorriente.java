package org.informatorio.domain;

import org.informatorio.enums.TipoCuenta;

public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro = 500;

    public CuentaCorriente(Long idCuenta, String numeroCuenta, double saldo, String titular, Cliente cliente, double limiteSobregiro) {
        super(idCuenta, numeroCuenta, saldo, titular, cliente);
        this.limiteSobregiro = limiteSobregiro;
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    @Override
    public void retirar(double monto) {
        if (monto <= getSaldo() + getLimiteSobregiro()) {
            setSaldo(getSaldo() - monto);
            System.out.println("Retiro exitoso. Nuevo saldo: " + getSaldo());
        } else {
            System.out.println("Fondos insuficientes para realizar el retiro con el límite de sobregiro.");
        }
    }

    @Override
    public TipoCuenta getTipoCuenta() {
        return TipoCuenta.CORRIENTE;  // Cambiar según el tipo específico de la cuenta

    }

    @Override
    public void depositar(double monto) {
        if ( monto >= 0 ) {
            setSaldo(getSaldo() + monto);
            System.out.println("Deposito exitoso. Nuevo saldo: " + getSaldo());
        } else {
            System.out.println("Ocurrio un error al realizar el deposito, monto incorrecto.");
        }
    }

    @Override
    public void consultarSaldo() {
        System.out.println("Saldo de la cuenta corriente: " + getSaldo());
    }

}