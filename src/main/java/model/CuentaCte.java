package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CuentaCte extends Cuenta {
    private Double limiteDescubierto;
    private Set<Cheque> cheques;
    public CuentaCte() {
        this.nroCuenta = getNroCuentaAutoIncremental();
        this.ultimaActualizacionIntereses = LocalDate.now();
        this.cheques = new HashSet<>();
    }
    public Double getLimiteDescubierto() {
        return limiteDescubierto;
    }
    public void setLimiteDescubierto(Double limiteDescubierto) {
        this.limiteDescubierto = limiteDescubierto;
    }
    public Set<Cheque> getCheques() {
        return cheques;
    }
    @Override
    public Boolean puedoExtraer(Double monto){
        return this.getSaldo() - monto + this.getLimiteDescubierto() >= 0;
    }
    public void setCheques(Set<Cheque> cheques) {
        this.cheques = cheques;
    }
    @Override
    public String toString(){
        return "C. Cte N°: " + this.nroCuenta + " - Saldo: "+ this.getSaldo() + " - " + this.titular;
    }
    @Override
    public String getTipoCuenta() {
        return "Cuenta Corriente";
    }
}
