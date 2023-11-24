package org.informatorio.domain;


import com.opencsv.CSVWriter;
import org.informatorio.enums.TipoCuenta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<Cliente> clientes;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void abrirCuenta(Cliente cliente, TipoCuenta tipoCuenta, double montoInicial) {
        Cuenta nuevaCuenta;
        if (tipoCuenta == TipoCuenta.AHORRO) {
            nuevaCuenta = new CuentaAhorro(montoInicial);
        } else {
            nuevaCuenta = new CuentaCorriente(montoInicial);
        }

        cliente.agregarCuenta(nuevaCuenta);
    }

    public void depositar(Cuenta cuenta, double monto) {
        cuenta.depositar(monto);
    }

    public void retirar(Cuenta cuenta, double monto) {
        cuenta.retirar(monto);
    }

    public void consultarSaldo(Cuenta cuenta) {
        double saldo = cuenta.getSaldo();
        System.out.println("Saldo de la cuenta: " + saldo);
    }

    public void calcularIntereses(CuentaAhorro cuentaAhorro) {
        cuentaAhorro.calcularIntereses();
    }

    public void exportarReporteCuentasCSV() {
        String csvFile = "reporte_cuentas.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Escribir encabezados en el archivo CSV
            writer.writeNext(new String[]{"Número único del titular", "Nombre de titular", "Saldo", "Tipo"});

            // Escribir detalles de las cuentas en el archivo CSV
            for (Cliente cliente : clientes) {
                for (Cuenta cuenta : cliente.getCuentas()) {
                    writer.writeNext(new String[]{cliente.getIdCliente().toString(), cliente.getNombre(), Double.toString(cuenta.getSaldo()), cuenta.getTipoCuenta().toString()});
                }
            }

            System.out.println("Reporte de cuentas exportado a " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
