import model.Banco;
import service.menu.banco.MenuBancoService;
import service.menu.banco.MenuBancoServiceImpl;

public class GestionBanco {

    public static void main(String[] args) {

        MenuBancoService menuBancoService = new MenuBancoServiceImpl(new Banco(1234,"Resitencia", 118F));
        menuBancoService.iniciarPrograma();
    }
}
