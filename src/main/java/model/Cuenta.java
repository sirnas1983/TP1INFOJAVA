package model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Cuenta {

    protected Long nroCuenta;
    protected Cliente titular;
    protected Double saldo;
    protected Moneda moneda;
    protected Banco sucursal;
    private static Long nroCuentaAutoIncremental = 0L;
    protected LocalDate ultimaActualizacionIntereses;
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
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    public Double getSaldo() {
        return saldo;
    }
    public Moneda getMoneda() {
        return moneda;
    }
    public Long getNroCuenta() {
        return this.nroCuenta;
    }
    public String getTipoCuenta() {
        return "Caja de Ahorro";
    }
    public Cliente getTitular() {
        return this.titular;
    }
    protected static Long getNroCuentaAutoIncremental(){
        nroCuentaAutoIncremental ++;
        return nroCuentaAutoIncremental;
    }
    //Este metodo calcula los intereses devengados
    public void calcularInteresDevengados() {
        Integer periodo = Period.between(this.ultimaActualizacionIntereses, LocalDate.now()).getDays();
        if (periodo.equals(0)){
            System.out.println("Se generan intereses a partir de 1 dia de permanencia");
            // TODO: Borrar una vez probada la aplicacion
            this.setUltimaActualizacionIntereses(LocalDate.of(2023,10,25));
        } else {
            this.ultimaActualizacionIntereses = LocalDate.now();
            Float interes = periodo * this.getSucursal().getTna()/(365f*100);
            double intereses = redondear(interes * this.getSaldo(),2);
            System.out.println("Sumaste " + intereses + " por intereses");
            this.saldo += intereses;
        }
    }
    public Boolean puedoExtraer(Double monto){
        return this.getSaldo() - monto >= 0;
    }
    public String toString(){
        return "C. Ahorro N°: " + this.nroCuenta + " - Saldo: "+ this.getSaldo() + " - " + this.titular;
    }
    public static Double redondear(Double num, int dec) {
        return Math.round(num * Math.pow(10,dec))/Math.pow(10,dec);
    }
}
