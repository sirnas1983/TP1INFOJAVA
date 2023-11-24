package service.banco;

import model.*;

import java.util.List;

public interface BancoService {

    void getListaClientes();
    List<Cuenta> getListaCuentas();
    List<Cuenta> getListaCuentasOrdenada();
    List<Cliente> getClienteByDni(int dni);
    void registrarCliente(Cliente cliente);
    void verCuentas();
    void exportarCuentasACsv();
    Banco getBanco();


}
