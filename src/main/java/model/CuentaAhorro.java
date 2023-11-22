package model;

import service.banco.BancoServiceImpl;

import java.time.LocalDate;
import java.time.Period;

public class CuentaAhorro implements Cuenta{

    protected Long nroCuenta;
    protected Cliente titular;
    protected Float interes;
    protected Double saldo;
    protected Boolean habilitada;
    protected Moneda moneda;
    protected Banco sucursal;
    private static Long nroCuentaAutoIncremental = 0L;
    protected LocalDate ultimaActualizacionIntereses;

    public CuentaAhorro() {
        this.nroCuenta = getNroCuentaAutoIncremental();
        this.ultimaActualizacionIntereses = LocalDate.now();
    }

    public CuentaAhorro(Cliente titular, Banco banco, Float interes, Double saldo, Boolean habilitada, Moneda moneda) {
        this.nroCuenta = getNroCuentaAutoIncremental();
        this.sucursal = banco;
        this.titular = titular;
        this.interes = interes;
        this.saldo = saldo;
        this.habilitada = habilitada;
        this.moneda = moneda;
        this.ultimaActualizacionIntereses = LocalDate.now();
    }

    public void setNroCuenta(Long nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public Banco getSucursal() {
        return sucursal;
    }

    public void setSucursal(Banco sucursal) {
        this.sucursal = sucursal;
    }

    public LocalDate getUltimaActualizacionIntereses() {
        return ultimaActualizacionIntereses;
    }

    public void setUltimaActualizacionIntereses(LocalDate ultimaActualizacionIntereses) {
        this.ultimaActualizacionIntereses = ultimaActualizacionIntereses;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setHabilitada(Boolean habilitada) {
        this.habilitada = habilitada;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Cliente getTitular() {
        return titular;
    }

    public Float getInteres() {
        return interes;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    @Override
    public Moneda getMoneda() {
        return moneda;
    }
    @Override
    public Long getNroCuenta() {
        return this.nroCuenta;
    }

    protected static Long getNroCuentaAutoIncremental(){
        nroCuentaAutoIncremental ++;
        return nroCuentaAutoIncremental;
    }
    @Override
    public void extraer(Double cant){
        this.saldo -= cant;
    }
    @Override
    public void depositar(Double cant){
        this.saldo += cant;
    }

    @Override
    public void calcularInteresDevengados() {
        Integer tiempo = Period.between(this.ultimaActualizacionIntereses, LocalDate.now()).getDays();
        if (tiempo.equals(0)){
            System.out.println("No se pueden calcular intereses en este periodo");
        } else {
            this.ultimaActualizacionIntereses = LocalDate.now();
            Float interes = tiempo * BancoServiceImpl.getTasaInteres()/365;
            double intereses = interes * this.getSaldo();
            this.saldo += intereses;
        }
    }
    @Override
    public Boolean puedoExtraer(Double monto){
        return this.getSaldo() - monto >= 0;
    }
    @Override
    public String toString(){
        return "C. Ahorro N°: " + this.nroCuenta + " - Saldo: "+ this.getSaldo() + " - " + this.titular;
    }
}
