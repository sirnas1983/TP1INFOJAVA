package service.banco;

import input.InputService;
import model.*;
import service.archivo.ArchivoService;
import service.archivo.ArchivoServiceImpl;

import java.util.Comparator;
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
    public void getListaClientes() {
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
    public List<Cuenta> getListaCuentasOrdenada() {
        List<Cuenta> cuentas = this.getListaCuentas();
        cuentas.sort(Comparator.comparingLong((Cuenta c) -> c.getTitular().getDni()).
                thenComparing(Cuenta::getSaldo));
        return cuentas;
    }

    @Override
    public List<Cliente> getClienteByDni(int dni) {
        return this.banco.getClientes().values().stream().filter((cliente) -> cliente.getDni() == dni).collect(Collectors.toList());
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
    public void exportarCuentasACsv() {
        if(this.getBanco().getCuentas().isEmpty()){
            System.out.println(SUC_SIN_CUENTAS_TEMPLATE);
        } else {
            System.out.println("Ingrese nombre de archivo: ");
            String nombreArchivo = InputService.getScanner().nextLine();
            ArchivoService archivoService = new ArchivoServiceImpl();
            archivoService.ExportarCuentasACsv(this.getListaCuentasOrdenada(), nombreArchivo);
        }
    }
}
