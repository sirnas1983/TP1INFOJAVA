package service.cliente;

import model.*;
import service.banco.BancoService;
import service.banco.BancoServiceImpl;
import service.cuenta.CuentaService;
import service.cuenta.CuentaServiceImpl;
import input.InputService;
import menu.banco.MenuBancoServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClienteServiceImpl implements ClienteService {


    private Scanner scanner;
    private Banco sucursal;
    private BancoService bancoService;
    public ClienteServiceImpl(Banco banco){
        this.scanner = InputService.getScanner();
        this.sucursal = banco;
        this.bancoService = new BancoServiceImpl(banco);
    }
    @Override
    public void generarCliente() {
        try {
            System.out.println("ALTA DE CLIENTE");
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println("Ingrese Nombre:");
            String nombre = this.scanner.nextLine().toUpperCase();
            System.out.println("Ingrese Apellido");
            String apellido = this.scanner.nextLine().toUpperCase();
            System.out.println("Ingrese domicilio:");
            String domicilio = this.scanner.nextLine().toUpperCase();
            System.out.println("Ingrese DNI:");
            int dni = Integer.parseInt(this.scanner.nextLine());
            System.out.println(MenuBancoServiceImpl.DIVISION);
            bancoService.registrarCliente(new Cliente(nombre, apellido, domicilio, dni));
        } catch (Exception e){
            System.out.println("Error en generación de cliente");
            System.out.println("Error: " + e.toString());
            System.out.println(MenuBancoServiceImpl.DIVISION);
        }
    }

    @Override
    public void abrirCuenta(Cliente cliente) {
        CuentaService cuentaService = new CuentaServiceImpl(this.sucursal);
        cuentaService.abrirCajaAhorro(this.scanner, cliente);
    }

    @Override
    public List<Cliente> getClienteByDni(int dni) {
        return this.sucursal.getClientes().values().stream().filter((cliente) -> cliente.getDni() == dni).collect(Collectors.toList());
    }

    @Override
    public void abrirCuentaCorriente(Cliente cliente) {
        CuentaService cuentaService = new CuentaServiceImpl(this.sucursal);
        cuentaService.abrirCuentaCte(this.scanner, cliente);
    }

    @Override
    public List<Cuenta> getCuentasCliente(Cliente cliente) {
        List<Cuenta> cuentas = cliente.getCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("Cliente sin cuentas");
            System.out.println(MenuBancoServiceImpl.DIVISION);
        } else {
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
        return cuentas;
    }

}
