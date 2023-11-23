package service.cuenta;

import model.*;

public interface CuentaService {

    CuentaAhorro abrirCajaAhorro(Cliente cliente, Moneda moneda);
    CuentaCte abrirCuentaCte(Cliente cliente, Moneda moneda, Double descubierto);
    void consultarSaldo(Cuenta cuenta);
    void extraerDinero(Double cant, Cuenta cuenta);
    void depositarDinero(Double cant, Cuenta cuenta);
    void emitirCheque(Double cant, Cuenta cuenta);
    void devengarIntereses(Cuenta cuenta);
    void listarCheques(Cuenta cta);
    void borrarCuenta(Cuenta cuenta);
}
