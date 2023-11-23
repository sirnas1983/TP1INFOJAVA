package service.archivo;

import model.Cuenta;

import java.util.List;

public interface ArchivoService {

    void ExportarCuentasACsv(List<Cuenta> cuentas, String ruta);
}
