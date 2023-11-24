import model.Banco;
import menu.banco.MenuBancoService;
import menu.banco.MenuBancoServiceImpl;

public class GestionBanco {

    private static int SUCURSAL = 1234;
    private static String DIRECCION = "Belgrano 123, Resistencia";
    private static Float TNA = 110F;
    public static void main(String[] args) {
        MenuBancoService menuBancoService = new MenuBancoServiceImpl(
                new Banco(SUCURSAL,DIRECCION, TNA)
        );
        menuBancoService.iniciarPrograma();
    }
}
