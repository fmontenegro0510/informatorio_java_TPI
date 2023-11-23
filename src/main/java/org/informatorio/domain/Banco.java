package org.informatorio.domain;


import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class Banco {


    public void exportarReporteCuentasCSV() {
        String csvFile = "reporte_cuentas.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Escribir encabezados en el archivo CSV
            writer.writeNext(new String[]{"Número único del titular", "Nombre de titular", "Saldo", "Tipo"});

            // Escribir detalles de las cuentas
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
