package service.banco;

import model.*;
import service.menu.banco.MenuBancoServiceImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BancoServiceImpl implements BancoService{

    private final Banco banco;
    private static float TASA_INTERES;
    public BancoServiceImpl(Banco banco) {
        this.banco = banco;
        TASA_INTERES = banco.getTna();
    }

    @Override
    public Banco getBanco() {
        return banco;
    }

    @Override
    public void getListaClientes() {
        Set<Cliente> clientes = this.banco.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("Sucursal sin clientes");
        } else {
            for (Cliente cliente : this.banco.getClientes()) {
                System.out.println(cliente);
            }
        }
    }
    @Override
    public List<Cuenta> getListaCuentas() {
        List<Cuenta> cuentas = this.banco.getCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("Sucursal sin cuentas");
            System.out.println(MenuBancoServiceImpl.DIVISION);
        } else {
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
                System.out.println(MenuBancoServiceImpl.DIVISION);
            }
        }
        return cuentas;
    }
    @Override
    public List<Cliente> getClienteByDni(int dni) {
        return this.banco.getClientes().stream().filter((cliente) -> cliente.getDni() == dni).collect(Collectors.toList());
    }
    @Override
    public void altaCliente(Cliente cliente) {
        Set<Cliente> clientes = this.banco.getClientes();
        if(clientes.contains(cliente)){
            System.out.println("Cliente ya esta cargado");
        } else {
            this.banco.getClientes().add(cliente);
            System.out.println("Cliente cargado correctamente");
            System.out.println(MenuBancoServiceImpl.DIVISION);
        }
    }

    @Override
    public void verCuentas() {
        List<Cuenta> cuentas = this.banco.getCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("Sucursal sin cuentas");
            System.out.println(MenuBancoServiceImpl.DIVISION);
        } else {
            for (Cuenta cuenta : cuentas) {
                System.out.println("antes");
                System.out.println(cuenta);
                System.out.println("despues");
                System.out.println(MenuBancoServiceImpl.DIVISION);
            }
        }
    }

    public static Float getTasaInteres() {
        return TASA_INTERES;
    }

}
