package service.menu.cliente;

import model.Cliente;
import service.banco.BancoService;
import service.cliente.ClienteService;
import service.cliente.ClienteServiceImpl;
import input.InputService;
import service.menu.banco.MenuBancoServiceImpl;
import service.menu.cuenta.MenuCuenta;
import service.menu.cuenta.MenuCuentaImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MenuClienteImpl implements MenuCliente{

    private Scanner scanner;
    private final String TITULO = "MENU CLIENTE";
    public static final String SELECCION_TEMPLATE = "Ingrese identificador para operar:";
    public static final String SIN_COINCIDENCIAS_TEMPLATE ="No hay coincidencias para ese ID";
    public static final String SIN_CLIENTES_TEMPLATE ="No hay clientes cargados";

    private BancoService bancoService;

    public MenuClienteImpl(BancoService bancoService){
        this.scanner = InputService.getScanner();
        this.bancoService = bancoService;
    }
    @Override
    public void operarConCliente() {
        bancoService.getListaClientes();
        HashMap<Long, Cliente> clientes = bancoService.getBanco().getClientes();
        if (clientes.isEmpty()){
            System.out.println(SIN_CLIENTES_TEMPLATE);
        } else {
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println(SELECCION_TEMPLATE);
            int dni = Integer.parseInt(scanner.nextLine());
            List<Cliente> cliente = bancoService.getClienteByDni(dni);
            if (!cliente.isEmpty()) {
                menuCliente(cliente.get(0));
            } else {
                System.out.println(SIN_COINCIDENCIAS_TEMPLATE);
            }
        }
    }

    public void menuCliente(Cliente cliente) {
        String opc;
        ClienteService clienteService = new ClienteServiceImpl(this.bancoService.getBanco());
        MenuCuenta menuCuenta = new MenuCuentaImpl(bancoService);
        do {
            System.out.println(TITULO);
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println("Ingrese una opcion:");
            System.out.println("1- Ver cuentas");
            System.out.println("2- Operar con cuenta");
            System.out.println("3- Abrir caja de ahorro");
            System.out.println("4- Abrir cuenta corriente");
            System.out.println("0- Volver al menu anterior");
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println("Seleccione una opcion:");
            opc = scanner.nextLine();
            System.out.println(MenuBancoServiceImpl.DIVISION);
            switch (opc) {
                case "1":
                    clienteService.getCuentasCliente(cliente);
                    break;
                case "2":
                    menuCuenta.operarConCuentaCliente(cliente);
                    break;
                case "3":
                    clienteService.abrirCuenta(cliente);
                    break;
                case "4":
                    clienteService.abrirCuentaCorriente(cliente);
            }
        } while (!opc.equals("0"));

    }
}
