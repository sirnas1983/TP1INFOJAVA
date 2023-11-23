package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String apellido;
    private String domicilio;
    private int dni;
    private List<Cuenta> cuentas;
    public Cliente() {
        this.cuentas = new ArrayList<>();
    }
    public Cliente(String nombre, String apellido, String domicilio, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.cuentas = new ArrayList<>();
    }
    public String getNombre() {
        return nombre;
    }
    public List<Cuenta> getCuentas() {
        return cuentas;
    }
    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public long getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getNombreCompleto(){
        return this.nombre + " " + this.apellido;
    }
    @Override
    public String toString(){
        return "DNI: " + this.dni + " Titular: " + this.getNombreCompleto();
    }
}
