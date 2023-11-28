package service.banco;

import model.*;
import service.cuenta.CuentaService;

import java.util.List;

public interface BancoService {

    void verClientes();
    List<Cuenta> getListaCuentas();
    void registrarCliente(Cliente cliente);
    void verCuentas();
    void exportarCuentasACsv(CuentaService cs);
    Banco getBanco();


}
