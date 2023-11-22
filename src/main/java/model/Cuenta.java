package model;

public interface Cuenta {
    void extraer(Double cant);
    void depositar(Double cant);
    void calcularInteresDevengados();
    Boolean puedoExtraer(Double cant);
    Moneda getMoneda();
    Double getSaldo();
    void setSaldo(Double saldo);
    Long getNroCuenta();
}
