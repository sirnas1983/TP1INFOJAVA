package service.banco;

import input.InputService;
import model.*;
import service.archivo.ArchivoService;
import service.archivo.ArchivoServiceImpl;
import service.cuenta.CuentaService;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BancoServiceImpl implements BancoService{

    private final Banco banco;
    public static String SUC_SIN_CUENTAS_TEMPLATE = "Sucursal sin cuentas";
    public static String SUC_SIN_CLIENTES_TEMPLATE = "Sucursal sin clientes";
    public BancoServiceImpl(Banco banco) {
        this.banco = banco;
    }

    @Override
    public Banco getBanco() {
        return banco;
    }

    @Override
    public void verClientes() {
        HashMap<Long, Cliente> clientes = this.banco.getClientes();
        if (clientes.isEmpty()) {
            System.out.println(SUC_SIN_CLIENTES_TEMPLATE);
        } else {
            for (Cliente cliente : this.banco.getClientes().values()) {
                System.out.println(cliente);
            }
        }
    }
    @Override
    public List<Cuenta> getListaCuentas() {
        return this.banco.getCuentas();
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        HashMap<Long, Cliente> clientes = this.banco.getClientes();
        if(clientes.containsKey(cliente.getDni())){
            System.out.println("Cliente ya esta registrado");
        } else {
            this.banco.getClientes().put(cliente.getDni(), cliente);
            System.out.println("Cliente cargado correctamente");
        }
    }
    @Override
    public void verCuentas() {
        List<Cuenta> cuentas = this.getListaCuentas();
        if (cuentas.isEmpty()) {
            System.out.println(SUC_SIN_CUENTAS_TEMPLATE);
        } else {
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }
    @Override
    public void exportarCuentasACsv(CuentaService cs) {
        if(this.getBanco().getCuentas().isEmpty()){
            System.out.println(SUC_SIN_CUENTAS_TEMPLATE);
        } else {
            ArchivoService archivoService = new ArchivoServiceImpl();
            archivoService.ExportarCuentasACsv(cs.getListaCuentasOrdenada());
        }
    }
}
