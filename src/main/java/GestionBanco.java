import model.Banco;
import menu.banco.MenuBancoService;
import menu.banco.MenuBancoServiceImpl;

public class GestionBanco {

    private static int SUCURSAL = 1234;
    private static String DIRECCION = "Belgrano 123, Resistencia";
    private static Float TNA = 110F;

    public static Banco banco;

    public static void main(String[] args) {
        banco = new Banco(SUCURSAL,DIRECCION, TNA);
        MenuBancoService menuBancoService = new MenuBancoServiceImpl(GestionBanco.banco);
        menuBancoService.iniciarPrograma();
    }
}
