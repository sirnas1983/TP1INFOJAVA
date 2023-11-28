package service.cuenta;

import model.*;

import java.util.List;
import java.util.Scanner;

public interface CuentaService {

    void abrirCajaAhorro(Scanner scanner, Cliente cliente);
    void abrirCuentaCte(Scanner scanner, Cliente cliente);
    void consultarSaldo(Cuenta cuenta);
    void extraerDinero(Double cant, Cuenta cuenta);
    void depositarDinero(Double cant, Cuenta cuenta);
    void emitirCheque(Double cant, Cuenta cuenta);
    void devengarIntereses(Cuenta cuenta);
    void listarCheques(Cuenta cta);
    void borrarCuenta(Cuenta cuenta);
    List<Cuenta> getListaCuentasOrdenada();

}
