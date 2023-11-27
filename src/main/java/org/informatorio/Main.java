package org.informatorio;

import org.informatorio.domain.Banco;
import org.informatorio.domain.Cliente;
import org.informatorio.domain.Cuenta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.informatorio.enums.TipoCuenta;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Banco banco = new Banco();

        while (true) {
            // Mostrar opciones del menú
            System.out.println("--------------------------");
            System.out.println("-------- OCTOBANK --------");
            System.out.println("--------------------------");
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Crear nuevo cliente");
            System.out.println("2. Mostrar todos los clientes");
            System.out.println("3. Abrir nueva cuenta para un cliente");
            System.out.println("4. Consultar saldo de una cuenta");
            System.out.println("5. Calcular intereses de una cuenta de ahorro");
            System.out.println("6. Depositar Dinero en una cuenta");
            System.out.println("7. Retirar Dinero de una cuenta");
            System.out.println("8. Exportar reporte de cuentas a CSV");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            // Leer la opción del usuario
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Crear nuevo cliente
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese la dirección del cliente: ");
                    String direccion = scanner.next();
                    // Crear cliente y agregarlo al banco
                    Cliente nuevoCliente = banco.crearCliente(nombre, direccion);
                    System.out.println("Cliente creado con éxito. ID: " + nuevoCliente.getIdCliente() + " - Nombre: " + nuevoCliente.getNombre());
                    break;
                case 2:
                    // Mostrar todos los clientes
                    List<Cliente> clientes = banco.obtenerTodosLosClientes();

                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        System.out.println("----- Lista de Clientes -----");
                        for (Cliente cliente : clientes) {
                            System.out.println("ID: " + cliente.getIdCliente() + ", Nombre: " + cliente.getNombre() + ", Dirección: " + cliente.getDireccion());
                            System.out.println("Cuentas:");

                            for (Cuenta cuenta : cliente.getCuentas()) {
                                System.out.println("   - Número de Cuenta: " + cuenta.getNumeroCuenta() + ", Saldo: " + cuenta.getSaldo());
                            }

                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case 3:
                    // Abrir nueva cuenta para un cliente
                    System.out.print("Ingrese el ID del cliente: ");
                    Long idClienteCuenta = scanner.nextLong();
                    Cliente clienteParaCuenta = banco.buscarClientePorId(idClienteCuenta);

                    if (clienteParaCuenta != null) {
                        System.out.println("Seleccione el tipo de cuenta:");
                        System.out.println("1. Cuenta de Ahorro");
                        System.out.println("2. Cuenta Corriente");
                        int tipoCuenta = scanner.nextInt();

                        switch (tipoCuenta) {
                            case 1:
                                // Abrir cuenta de ahorro
                                System.out.print("Ingrese el monto inicial: ");
                                double montoInicialAhorro = scanner.nextDouble();
                                banco.abrirCuenta(clienteParaCuenta, TipoCuenta.AHORRO, montoInicialAhorro);
                                break;

                            case 2:
                                // Abrir cuenta corriente
                                System.out.print("Ingrese el monto inicial: ");
                                double montoInicialCorriente = scanner.nextDouble();
                                banco.abrirCuenta(clienteParaCuenta, TipoCuenta.CORRIENTE, montoInicialCorriente);
                                break;

                            default:
                                System.out.println("Opción no válida.");
                        }
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 4:
                    // Consultar saldo de una cuenta
                    System.out.print("Ingrese el ID del cliente: ");
                    Long idClienteConsulta = scanner.nextLong();
                    Cliente clienteConsulta = banco.buscarClientePorId(idClienteConsulta);

                    if (clienteConsulta != null) {
                        System.out.print("Ingrese el número de cuenta: ");
                        String numeroCuentaConsulta = scanner.next();
                        banco.consultarSaldo(clienteConsulta, numeroCuentaConsulta);
                    } else {
                        System.out.println("Cliente no encontrado.");

                    }
                    break;

                case 5:
                    // Calcular intereses de una cuenta de ahorro
                    System.out.print("Ingrese el ID del cliente: ");
                    Long idClienteIntereses = scanner.nextLong();
                    Cliente clienteIntereses = banco.buscarClientePorId(idClienteIntereses);

                    if (clienteIntereses != null) {
                        System.out.print("Ingrese el número de cuenta de ahorro: ");
                        String numeroCuentaAhorro = scanner.next();
                        banco.calcularIntereses(clienteIntereses, numeroCuentaAhorro);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 6:
                    // Depositar Dinero
                    System.out.print("Ingrese el ID del cliente: ");
                    Long idClienteDeposito = scanner.nextLong();
                    Cliente clienteDeposito = banco.buscarClientePorId(idClienteDeposito);

                    if (clienteDeposito != null) {
                        System.out.print("Ingrese el número de cuenta: ");
                        String numeroCuentaDeposito = scanner.next();
                        System.out.print("Ingrese el monto a depositar: ");
                        double montoDeposito = scanner.nextDouble();
                        banco.depositar(clienteDeposito, numeroCuentaDeposito, montoDeposito);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 7:
                    // Retirar Dinero
                    System.out.print("Ingrese el ID del cliente: ");
                    Long idClienteRetiro = scanner.nextLong();
                    Cliente clienteRetiro = banco.buscarClientePorId(idClienteRetiro);

                    if (clienteRetiro != null) {
                        System.out.print("Ingrese el número de cuenta: ");
                        String numeroCuentaRetiro = scanner.next();
                        System.out.print("Ingrese el monto a extraer: ");
                        double montoExtraccion = scanner.nextDouble();
                        banco.retirar(clienteRetiro, numeroCuentaRetiro, montoExtraccion);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 8:
                    // Exportar reporte de cuentas a CSV
                    banco.exportarReporteCuentasCSV();
                    break;

                case 9:
                    // Salir del programa
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}