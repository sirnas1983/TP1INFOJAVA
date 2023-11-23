package service.menu.cuenta;

import model.Cliente;
import model.Cuenta;
import model.CuentaCte;
import service.banco.BancoService;
import service.cliente.ClienteService;
import service.cliente.ClienteServiceImpl;
import service.cuenta.CuentaService;
import service.cuenta.CuentaServiceImpl;
import input.InputService;
import service.menu.banco.MenuBancoServiceImpl;
import service.menu.cliente.MenuClienteImpl;

import java.util.List;

public class MenuCuentaImpl implements MenuCuenta {
    private BancoService bancoService;
    private String TITULO = "MENU DE CUENTA";
    public static final String SIN_CUENTAS_TEMPLATE ="No hay cuentas cargadas";
    public MenuCuentaImpl(BancoService bancoService){
        this.bancoService = bancoService;
    }
    @Override
    public void operarConCuentaCliente(Cliente cliente) {
        ClienteService clienteService = new ClienteServiceImpl(bancoService.getBanco());
        List<Cuenta> cuentas = clienteService.getCuentasCliente(cliente);
        if (cuentas.isEmpty()){
            System.out.println(SIN_CUENTAS_TEMPLATE);
        } else {
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println(MenuClienteImpl.SELECCION_TEMPLATE);
            Long nroCuenta = Long.parseLong(InputService.getScanner().nextLine());
            cuentas = cuentas.stream().filter(
                    cta -> cta.getNroCuenta().equals(nroCuenta)
            ).toList();
            if (cuentas.isEmpty()) {
                System.out.println(MenuClienteImpl.SIN_COINCIDENCIAS_TEMPLATE);
            } else {
                menuCuenta(cuentas.get(0));
            }
        }
    }
    @Override
    public void operarConCuenta() {
        List<Cuenta> cuentas = bancoService.getListaCuentas();
        if (cuentas.isEmpty()){
            System.out.println(SIN_CUENTAS_TEMPLATE);
        } else {
            System.out.println(MenuBancoServiceImpl.DIVISION);
            System.out.println(MenuClienteImpl.SELECCION_TEMPLATE);
            Long nroCuenta = Long.parseLong(InputService.getScanner().nextLine());
            cuentas = cuentas.stream().filter(
                    cta -> cta.getNroCuenta().equals(nroCuenta)
            ).toList();
            if (cuentas.isEmpty()) {
                System.out.println(MenuClienteImpl.SIN_COINCIDENCIAS_TEMPLATE);
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
                System.out.println("6- Ver cheques");
            }
            System.out.println("9- Eliminar cuenta");
            System.out.println("0- Volver al menu anterior");
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
                    if(cuenta instanceof CuentaCte){
                        System.out.println("Ingrese monto de cheque a emitir:");
                        monto = Double.parseDouble(InputService.getScanner().nextLine());
                        cuentaService.emitirCheque(monto, cuenta);
                    }
                    break;
                case "6":
                    if(cuenta instanceof CuentaCte) {
                        cuentaService.listarCheques(cuenta);
                    }
                    break;
                case "9":
                    cuentaService.borrarCuenta(cuenta);
                    break;
            }
        } while (!opc.equals("0"));
    }
}
