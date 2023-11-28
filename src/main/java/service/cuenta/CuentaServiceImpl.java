package service.cuenta;

import model.*;
import service.cliente.ClienteServiceImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CuentaServiceImpl implements CuentaService {

    private Banco sucursal;
    private String MONTO_POSITIVO_TEMPLATE = "Debe ingresar un monto mayor a 0";
    private String SALDO_INSUFICIENTE_TEMPLATE = "Saldo insuficiente";
    public static String INGRESE_MONEDA_TEMPLATE = "Ingrese una moneda (PESO - DOLAR):";
    public static String MONEDA_NO_VALIDA_TEMPLATE = "Moneda no valida, intente nuevamente";

    public CuentaServiceImpl(Banco banco){
        this.sucursal = banco;
    }
    public CuentaServiceImpl(){}

    @Override
    public void abrirCajaAhorro(Scanner scanner, Cliente cliente) {
        boolean ok = true;
        do {
            System.out.println(INGRESE_MONEDA_TEMPLATE);
            String opc = scanner.nextLine().toUpperCase();
            try{
                Moneda moneda = Moneda.valueOf(opc);
                CuentaAhorro cuenta = new CuentaAhorro();
                cuenta.setSaldo(0D);
                cuenta.setMoneda(moneda);
                cuenta.setTitular(cliente);
                cuenta.setSucursal(this.sucursal);
                cliente.getCuentas().add(cuenta);
                this.sucursal.getCuentas().add(cuenta);
                ok = false;
            } catch (IllegalArgumentException e){
                System.out.println(MONEDA_NO_VALIDA_TEMPLATE);
            }
        } while (ok);
    }
    @Override
    public List<Cuenta> getListaCuentasOrdenada() {
        List<Cuenta> cuentas = this.sucursal.getCuentas();
        cuentas.sort(Comparator.comparingLong((Cuenta c) -> c.getTitular().getDni()).
                thenComparing(Cuenta::getSaldo));
        return cuentas;
    }
    @Override
    public void abrirCuentaCte(Scanner scanner, Cliente cliente) {
        boolean ok = true;
        do {
            System.out.println(INGRESE_MONEDA_TEMPLATE);
            String opc = scanner.nextLine().toUpperCase();
            try {
                Moneda moneda = Moneda.valueOf(opc);
                System.out.println("Ingrese descubierto:");
                Double descubierto = Double.parseDouble(scanner.nextLine());
                CuentaCte ctaCte = new CuentaCte();
                ctaCte.setSaldo(0D);
                ctaCte.setMoneda(moneda);
                ctaCte.setTitular(cliente);
                ctaCte.setLimiteDescubierto(descubierto);
                ctaCte.setSucursal(this.sucursal);
                cliente.getCuentas().add(ctaCte);
                this.sucursal.getCuentas().add(ctaCte);
                ok = false;
            } catch (NullPointerException g){
                System.out.println("Debe ingresar un numero");
            } catch (NumberFormatException f){
                System.out.println("Descubierto invalido");
            } catch (IllegalArgumentException e){
                System.out.println(MONEDA_NO_VALIDA_TEMPLATE);
            }
        } while (ok);
    }
    @Override
    public void consultarSaldo(Cuenta cuenta){
        System.out.println("Su saldo es de: $ " + cuenta.getSaldo() + " " + cuenta.getMoneda());
    }
    @Override
    public void extraerDinero(Double cant, Cuenta cta) {
        if (cant > 0){
            if(cta.puedoExtraer(cant)){
                cta.setSaldo(cta.getSaldo() - cant);
            } else {
                System.out.println(SALDO_INSUFICIENTE_TEMPLATE);
            }
        } else {
            System.out.println(MONTO_POSITIVO_TEMPLATE);
        }
    }
    @Override
    public void depositarDinero(Double cant, Cuenta cta) {
        if (cant > 0){
            cta.setSaldo(cta.getSaldo() + cant);
        } else {
            System.out.println(MONTO_POSITIVO_TEMPLATE);
        }
    }
    @Override
    public void emitirCheque(Double monto, Cuenta cuenta) {
        if(!(cuenta instanceof CuentaCte cta)){
            System.out.println("Operacion no valida para cajas de ahorro");
        } else if(cuenta.puedoExtraer(monto) && monto > 0){
            Cheque cheque = new Cheque();
            cta.getCheques().add(cheque);
            cheque.setCuenta(cta);
            cheque.setMonto(monto);
            cheque.setFechaEmision(LocalDate.now());
            cta.setSaldo(cta.getSaldo() - monto);
        } else if(monto <= 0) {
            System.out.println(MONTO_POSITIVO_TEMPLATE);
        } else if(!cuenta.puedoExtraer(monto)){
            System.out.println(SALDO_INSUFICIENTE_TEMPLATE);
        }
    }
    @Override
    public void devengarIntereses(Cuenta cta) {
        cta.calcularInteresDevengados();
    }
    @Override
    public void listarCheques(Cuenta cta) {
        if(!(cta instanceof CuentaCte)){
            System.out.println("Operacion no valida para cajas de ahorro");
        } else {
            Set<Cheque> cheques = ((CuentaCte) cta).getCheques();
            if (cheques.isEmpty()){
                System.out.println("No hay cheques emitidos desde esta cuenta");
            } else {
                for (Cheque cheque : cheques){
                    System.out.println(cheque);
                }
            }

        }
    }
    @Override
    public void borrarCuenta(Cuenta cuenta) {
        if (cuenta.getSaldo().equals(0D)) {
            cuenta.getTitular().getCuentas().remove(cuenta);
            cuenta.getSucursal().getCuentas().remove(cuenta);
            System.out.println("Cuenta eliminada correctamente");
        } else {
            System.out.println("Para poder realizar esta operacion la cuenta debe estar en 0");
        }
    }
}
