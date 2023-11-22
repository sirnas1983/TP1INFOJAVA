package service.cliente;

import model.*;
import service.cuenta.CuentaService;
import service.cuenta.CuentaServiceImpl;
import service.inputService.InputService;
import service.menu.banco.MenuBancoServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ClienteServiceImpl implements ClienteService {


    private Scanner scanner;
    private Banco sucursal;
    public ClienteServiceImpl(Banco banco){
        this.scanner = InputService.getScanner();
        this.sucursal = banco;
    }
    @Override
    public Cliente altaCliente() {
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
            return new Cliente(nombre, apellido, domicilio, dni);
        } catch (Exception e){
            System.out.println("Error en generación de usuario");
            System.out.println("Error: " + e.toString());
            System.out.println(MenuBancoServiceImpl.DIVISION);
            return null;
        }
    }

    @Override
    public void abrirCuenta(Cliente cliente) {
        CuentaService cuentaService = new CuentaServiceImpl(this.sucursal);
        boolean ok = true;
        do {
            System.out.println("Ingrese una moneda (PESO - DOLAR): ");
            String opc = scanner.nextLine().toUpperCase();
            try{
                Moneda moneda = Moneda.valueOf(opc);
                Cuenta cuenta = cuentaService.abrirCajaAhorro(cliente, moneda);
                cliente.getCuentas().add(cuenta);
                ok = false;
            } catch (IllegalArgumentException e){
                System.out.println("Moneda no valida, intente nuevamente");
            }
        } while (ok);
    }

    @Override
    public void abrirCuentaCorriente(Cliente cliente) {
        CuentaService cuentaService = new CuentaServiceImpl(this.sucursal);
        boolean ok = true;
        do {
            System.out.println("Ingrese una moneda (PESO - DOLAR): ");
            String opc = scanner.nextLine().toUpperCase();
            try {
                Moneda moneda = Moneda.valueOf(opc);
                System.out.println("Ingrese descubierto:");
                Double descubierto = Double.parseDouble(scanner.nextLine());
                CuentaAhorro cuenta = cuentaService.abrirCuentaCte(cliente, moneda, descubierto);
                cliente.getCuentas().add(cuenta);
                ok = false;
            } catch (NullPointerException g){
                System.out.println("Debe ingresar un numero");
            } catch (NumberFormatException f){
                System.out.println("Descubierto invalido");
            } catch (IllegalArgumentException e){
                System.out.println("Moneda no valida, intente nuevamente");
            }
        } while (ok);
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
                System.out.println(MenuBancoServiceImpl.DIVISION);
            }
        }
        return cuentas;
    }

}
