package service.menu.cuenta;

import model.Cliente;
import model.Cuenta;

public interface MenuCuenta {

    void operarConCuentaCliente(Cliente cliente);
    void operarConCuenta();
    void menuCuenta(Cuenta cuenta);

}
