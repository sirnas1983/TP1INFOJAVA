package model;

import java.util.HashSet;
import java.util.Set;

public class CuentaCte extends CuentaAhorro implements Cuenta {
    private Double limiteDescubierto;
    private Set<Cheque> cheques;

    public CuentaCte() {
        super();
        this.cheques = new HashSet<>();
    }

    public CuentaCte(Cliente titular, Banco sucursal, Float interes, Double saldo, Boolean habilitada, Moneda moneda, Double limiteDescubierto) {
        super(titular, sucursal,interes, saldo, habilitada, moneda);
        this.limiteDescubierto = limiteDescubierto;
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
        return this.getSaldo() - monto + this.getLimiteDescubierto() > 0;
    }

    public void setCheques(Set<Cheque> cheques) {
        this.cheques = cheques;
    }
    @Override
    public String toString(){
        return "C. Cte N°: " + this.nroCuenta + " - Saldo: "+ this.getSaldo() + " - " + this.titular;
    }
}
