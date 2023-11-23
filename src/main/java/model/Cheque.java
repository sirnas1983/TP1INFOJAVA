package model;

import java.time.LocalDate;

public class Cheque {
    private Long nroCheque;
    private CuentaCte cuenta;
    private LocalDate fechaEmision;
    private Double monto;
    private static Long nroChequeautoIncremental = 0L;

    public Cheque() {
        this.nroCheque = getNroChequeautoIncremental();
    }
    public Cheque(CuentaCte cuenta, LocalDate fechaEmision, Double monto) {
        this.nroCheque = getNroChequeautoIncremental();
        this.cuenta = cuenta;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
    }
    private static Long getNroChequeautoIncremental(){
        nroChequeautoIncremental ++;
        return nroChequeautoIncremental;
    }

    public Long getNroCheque() {
        return nroCheque;
    }

    public void setNroCheque(Long nroCheque) {
        this.nroCheque = nroCheque;
    }

    public CuentaCte getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaCte cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public static void setNroChequeautoIncremental(Long nroChequeautoIncremental) {
        Cheque.nroChequeautoIncremental = nroChequeautoIncremental;
    }
    @Override
    public String toString(){
        return this.nroCheque + " - " + getFechaEmision() + " - Monto: " + this.getMonto();
    }
}
