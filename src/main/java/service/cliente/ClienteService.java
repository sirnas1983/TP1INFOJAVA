package service.cliente;

import model.Cliente;
import model.Cuenta;

import java.util.List;

public interface ClienteService {

    Cliente altaCliente();

    void abrirCuenta(Cliente cliente);

    void abrirCuentaCorriente(Cliente cliente);

    List<Cuenta> getCuentasCliente(Cliente cliente);


}
