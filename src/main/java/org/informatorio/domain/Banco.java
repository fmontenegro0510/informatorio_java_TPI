package org.informatorio.domain;


import com.opencsv.CSVWriter;
import org.informatorio.enums.TipoCuenta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Banco {

    private List<Cliente> clientes;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public Cliente crearCliente(String nombre, String direccion) {
//        Long nuevoId = (long) (clientes.size() + 1);  // Genera un nuevo id basado en la cantidad de clientes
        Cliente nuevoCliente = new Cliente(generarIdCliente(), nombre, direccion);
        clientes.add(nuevoCliente);
        return nuevoCliente;
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clientes;
    }

    public Cliente buscarClientePorId(Long idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }


    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void abrirCuenta(Cliente cliente, TipoCuenta tipoCuenta, double montoInicial) {
        Cuenta nuevaCuenta=null;

        switch (tipoCuenta) {
            case AHORRO:
                nuevaCuenta = new CuentaAhorro(generarIdCuenta() , "AH" + generarNumeroCuenta(), montoInicial, cliente.getNombre(), cliente, 0.0);
                break;
            case CORRIENTE:
                nuevaCuenta = new CuentaCorriente(generarIdCuenta(), "CC" + generarNumeroCuenta(), montoInicial, cliente.getNombre(), cliente, 0.0);
                break;
        }

        if (nuevaCuenta != null) {
            cliente.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta creada con éxito para " + cliente.getNombre());
        }

    }


    public void depositar(Long idCuenta, double monto) {
        Cuenta cuenta = buscarCuentaPorId(idCuenta);
        if (cuenta != null) {
            cuenta.depositar(monto);
        }
    }

    public void retirar(Long idCuenta, double monto) {
        Cuenta cuenta = buscarCuentaPorId(idCuenta);
        if (cuenta != null) {
            cuenta.retirar(monto);
        }
    }

    public void consultarSaldo(Cliente cliente, String numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta);
        if (cuenta != null) {
            System.out.println("Saldo de la cuenta " + numeroCuenta + ": " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada para el cliente " + cliente.getNombre());
        }
    }

    public void calcularIntereses(Cliente cliente, String numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta);

        if (cuenta instanceof CuentaAhorro) {
            CuentaAhorro cuentaAhorro = (CuentaAhorro) cuenta;
            cuentaAhorro.calcularIntereses();
        } else {
            System.out.println("La cuenta ingresada no es una cuenta de ahorro.");
        }
    }

    public void calcularIntereses(Long idCuenta) {
        Cuenta cuenta = buscarCuentaPorId(idCuenta);
        if (cuenta instanceof CuentaAhorro) {
            ((CuentaAhorro) cuenta).calcularIntereses();
        }
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

    private Cuenta buscarCuentaPorNumero(Cliente cliente, String numeroCuenta) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    private Cuenta buscarCuentaPorId(Long idCuenta) {
        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.getIdCuenta().equals(idCuenta)) {
                    return cuenta;
                }
            }
        }
        return null;
    }


    private Long generarIdCliente() {
        // Simulación de generación de ID único para el cliente
        return System.currentTimeMillis();
    }

    private Long generarIdCuenta() {
        // Simulación de generación de ID único para la cuenta
        return System.currentTimeMillis();
    }

    private String generarNumeroCuenta() {
        // Simulación de generación de número de cuenta único
        Random random = new Random();
        return "ACC" + (100000 + random.nextInt(900000));
    }
}
