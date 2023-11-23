package org.informatorio.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private Long idCliente;
    private String nombre;
    private String direccion;
    private List<Cuenta> cuentas = new ArrayList<>();

    public Cliente(Long idCliente, String nombre, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }
}