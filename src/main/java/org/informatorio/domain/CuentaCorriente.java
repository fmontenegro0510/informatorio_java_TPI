package org.informatorio.domain;

public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;

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
}