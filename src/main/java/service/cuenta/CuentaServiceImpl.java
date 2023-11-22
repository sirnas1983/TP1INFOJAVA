package service.cuenta;

import model.*;
import service.banco.BancoServiceImpl;

import java.time.LocalDate;

public class CuentaServiceImpl implements CuentaService {

    private Banco sucursal;
    public CuentaServiceImpl(Banco banco){
        this.sucursal = banco;
    }

    public CuentaServiceImpl(){}

    @Override
    public CuentaAhorro abrirCajaAhorro(Cliente cliente, Moneda moneda) {
        CuentaAhorro cuenta = new CuentaAhorro();
        cuenta.setSaldo(0D);
        cuenta.setHabilitada(true);
        cuenta.setMoneda(moneda);
        cuenta.setTitular(cliente);
        cuenta.setInteres(BancoServiceImpl.getTasaInteres());
        cuenta.setSucursal(this.sucursal);
        this.sucursal.getCuentas().add(cuenta);
        return cuenta;
    }

    @Override
    public CuentaCte abrirCuentaCte(Cliente cliente, Moneda moneda, Double descubierto) {
        CuentaCte ctaCte = new CuentaCte();
        ctaCte.setSaldo(0D);
        ctaCte.setHabilitada(true);
        ctaCte.setMoneda(moneda);
        ctaCte.setTitular(cliente);
        ctaCte.setInteres(BancoServiceImpl.getTasaInteres());
        ctaCte.setLimiteDescubierto(descubierto);
        ctaCte.setSucursal(this.sucursal);
        this.sucursal.getCuentas().add(ctaCte);
        return ctaCte;
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
                System.out.println("Saldo insuficiente.");
            }
        }
    }

    @Override
    public void depositarDinero(Double cant, Cuenta cta) {
        if (cant > 0){
            cta.setSaldo(cta.getSaldo() + cant);
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
            System.out.println("El monto debe ser mayor a 0");
        } else if(!cuenta.puedoExtraer(monto)){
            System.out.println("Saldo insuficiente.");
        }
    }
    @Override
    public void devengarIntereses(Cuenta cta) {
        cta.calcularInteresDevengados();
    }
}
