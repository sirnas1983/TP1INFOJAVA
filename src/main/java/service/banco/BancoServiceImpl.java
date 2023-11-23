package service.banco;

import input.InputService;
import model.*;
import service.archivo.ArchivoService;
import service.archivo.ArchivoServiceImpl;
import service.menu.banco.MenuBancoServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BancoServiceImpl implements BancoService{

    private final Banco banco;
    public BancoServiceImpl(Banco banco) {
        this.banco = banco;
    }

    @Override
    public Banco getBanco() {
        return banco;
    }

    @Override
    public void getListaClientes() {
        HashMap<Long, Cliente> clientes = this.banco.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("Sucursal sin clientes");
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
    public List<Cliente> getClienteByDni(int dni) {
        return this.banco.getClientes().values().stream().filter((cliente) -> cliente.getDni() == dni).collect(Collectors.toList());
    }
    @Override
    public void altaCliente(Cliente cliente) {
        HashMap<Long, Cliente> clientes = this.banco.getClientes();
        if(clientes.containsKey(cliente.getDni())){
            System.out.println("Cliente ya esta cargado");
        } else {
            this.banco.getClientes().put(cliente.getDni(), cliente);
            System.out.println("Cliente cargado correctamente");
            System.out.println(MenuBancoServiceImpl.DIVISION);
        }
    }
    @Override
    public void verCuentas() {
        List<Cuenta> cuentas = this.getListaCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("Sucursal sin cuentas");
        } else {
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }
    @Override
    public void exportarCuentasACsv() {
        if(this.getBanco().getCuentas().isEmpty()){
            System.out.println("No hay cuentas para reportar");
        } else {
            System.out.println("Ingrese nombre de archivo: ");
            String nombreArchivo = InputService.getScanner().nextLine();
            ArchivoService archivoService = new ArchivoServiceImpl();
            archivoService.ExportarCuentasACsv(this.getListaCuentas(), nombreArchivo);
        }
    }
}
