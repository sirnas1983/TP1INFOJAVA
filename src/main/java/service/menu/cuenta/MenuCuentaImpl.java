package service.menu.cuenta;

import model.Cliente;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCte;
import service.banco.BancoService;
import service.cliente.ClienteService;
import service.cliente.ClienteServiceImpl;
import service.cuenta.CuentaService;
import service.cuenta.CuentaServiceImpl;
import service.inputService.InputService;
import service.menu.banco.MenuBancoServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class MenuCuentaImpl implements MenuCuenta {

    private BancoService bancoService;
    private String TITULO = "Menu de cuenta";

    public MenuCuentaImpl(BancoService bancoService){
        this.bancoService = bancoService;
    }

    @Override
    public void operarConCuentaCliente(Cliente cliente) {
        ClienteService clienteService = new ClienteServiceImpl(bancoService.getBanco());
        List<Cuenta> cuentas = clienteService.getCuentasCliente(cliente);
        if (cuentas.isEmpty()){
            System.out.println("No hay cuentas disponibles");
        } else {
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println("Ingrese numero de cuenta para operar:");
            Long nroCuenta = Long.parseLong(InputService.getScanner().nextLine());
            cuentas = cuentas.stream().filter(
                    cta -> cta.getNroCuenta().equals(nroCuenta)
            ).toList();
            if (cuentas.isEmpty()) {
                System.out.println("No hay cuentas con ese identificador");
            } else {
                menuCuenta(cuentas.get(0));
            }
        }
    }

    @Override
    public void operarConCuenta() {
        if (bancoService.getListaCuentas().isEmpty()){
            System.out.println("No hay cuentas disponibles");
        } else {
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println("Ingrese numero de cuenta para operar:");
            Long nroCuenta = Long.parseLong(InputService.getScanner().nextLine());
            List<Cuenta> cuentas = bancoService.getListaCuentas().stream().filter(
                    cta -> cta.getNroCuenta().equals(nroCuenta)
            ).toList();
            if (cuentas.isEmpty()) {
                System.out.println("No hay cuentas con ese identificador");
            } else {
                menuCuenta(cuentas.get(0));
            }
        }
    }


    @Override
    public void menuCuenta(Cuenta cuenta) {
        String opc;
        double monto;
        CuentaService cuentaService = new CuentaServiceImpl();
        do {
            System.out.println(TITULO);
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println("Ingrese una opcion:");
            System.out.println("1- Extraer");
            System.out.println("2- Depositar");
            System.out.println("3- Consulta de saldo");
            System.out.println("4- Calcular interés");
            if (cuenta instanceof CuentaCte) {
                System.out.println("5- Emitir Cheque");
            }
            System.out.println("0- Salir");
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println("Seleccione una opcion:");
            opc = InputService.getScanner().nextLine();
            System.out.println(MenuBancoServiceImpl.DIVISION);
            switch (opc) {
                case "1":
                    System.out.println("Ingrese el monto a extraer:");
                    monto = Double.parseDouble(InputService.getScanner().nextLine());
                    cuentaService.extraerDinero(monto, cuenta);
                    break;
                case "2":
                    System.out.println("Ingrese el monto a depositar:");
                    monto = Double.parseDouble(InputService.getScanner().nextLine());
                    cuentaService.depositarDinero(monto, cuenta);
                    break;
                case "3":
                    cuentaService.consultarSaldo(cuenta);
                    break;
                case "4":
                    cuentaService.devengarIntereses(cuenta);
                    break;
                case "5":
                    System.out.println("Ingrese monto de cheque a emitir:");
                    monto = Double.parseDouble(InputService.getScanner().nextLine());
                    cuentaService.emitirCheque(monto, cuenta);
                    break;
            }
        } while (!opc.equals("0"));

    }

}
