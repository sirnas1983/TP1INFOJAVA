package service.menu.banco;

import model.Banco;
import service.banco.BancoService;
import service.banco.BancoServiceImpl;
import input.InputService;
import service.cliente.ClienteService;
import service.cliente.ClienteServiceImpl;
import service.menu.cliente.MenuClienteImpl;
import service.menu.cuenta.MenuCuenta;
import service.menu.cuenta.MenuCuentaImpl;

import java.util.Scanner;

public class MenuBancoServiceImpl implements MenuBancoService{


    private Banco banco;
    private BancoService bancoService;
    private MenuClienteImpl menuCliente;
    private ClienteService clienteService;
    private MenuCuenta menuCuenta;
    private static String TITULO = "Bienvenido al sistema de gestion bancaria";
    public static String DIVISION = "-".repeat(25);


    public MenuBancoServiceImpl(Banco banco) {
        this.banco = banco;
        this.clienteService = new ClienteServiceImpl(banco);
    }

    public void iniciarPrograma(){
        String opc;
        Scanner scanner = InputService.getScanner();
        this.bancoService = new BancoServiceImpl(this.banco);
        this.menuCliente = new MenuClienteImpl(bancoService);
        this.menuCuenta = new MenuCuentaImpl(bancoService);
        System.out.println(TITULO);
        do {
            System.out.println(DIVISION);
            System.out.println("Ingrese una opcion:");
            System.out.println("1- Ver clientes");
            System.out.println("2- Ver cuentas");
            System.out.println("3- Alta cliente");
            System.out.println("4- Operar con cliente");
            System.out.println("5- Operar con cuenta");
            System.out.println("6- Exportar cuentas a CSV");
            System.out.println("0- Salir");
            System.out.println(DIVISION);
            System.out.println("Seleccione una opcion:");
            opc = scanner.nextLine();
            System.out.println(DIVISION);
            switch (opc){
                case "1":
                    bancoService.getListaClientes();
                    break;
                case "2":
                    bancoService.verCuentas();
                    break;
                case "3":
                    clienteService.generarCliente();
                    break;
                case "4":
                    menuCliente.operarConCliente();
                    break;
                case "5":
                    bancoService.verCuentas();
                    menuCuenta.operarConCuenta();
                    break;
                case "6":
                    bancoService.exportarCuentasACsv();
            }
        } while(!opc.equals("0"));
    }

}
