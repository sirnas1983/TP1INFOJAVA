package service.banco;

import model.*;

import java.util.List;

public interface BancoService {

    void getListaClientes();
    List<Cuenta> getListaCuentas();
    List<Cliente> getClienteByDni(int dni);
    void altaCliente(Cliente cliente);
    void verCuentas();
    Banco getBanco();


}
