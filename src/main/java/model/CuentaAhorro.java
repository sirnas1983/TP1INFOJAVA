package model;

import java.time.LocalDate;
import java.time.Period;

public class CuentaAhorro extends Cuenta {

    public CuentaAhorro() {
        this.nroCuenta = getNroCuentaAutoIncremental();
        this.ultimaActualizacionIntereses = LocalDate.now();
    }

}
