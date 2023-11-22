package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Banco {

    private int sucursal;
    private float tna;
    private String direccion;
    private Set<Cliente> clientes;
    private List<Cuenta> cuentas;

    public Banco() {
    }
    public Banco(int sucursal, String direccion, Float tna) {
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.cuentas = new ArrayList<>();
        this.clientes = new HashSet<>();
    }

    public float getTna() {
        return tna;
    }

    public void setTna(float tna) {
        this.tna = tna;
    }

    public int getSucursal() {
        return sucursal;
    }
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
    public List<Cuenta> getCuentas() {
        return cuentas;
    }
    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}