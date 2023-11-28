package service.archivo;

import com.opencsv.CSVWriter;
import input.InputService;
import model.Cuenta;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivoServiceImpl implements ArchivoService {
    private static String getCurrentProjectDirectory() {
        return new File("").getAbsolutePath() + "\\";
    }
    public void ExportarCuentasACsv(List<Cuenta> cuentas) {
        try{
            System.out.println("Ingrese nombre de archivo: ");
            String nombreArchivo = InputService.getScanner().nextLine();
            String fullPath = getCurrentProjectDirectory() + nombreArchivo + ".csv";
            CSVWriter csvWriter = new CSVWriter(new FileWriter(fullPath));
            String[] encabezados = {"nro titular","nombre titular","nro cuenta","saldo","tipo","moneda"};
            csvWriter.writeNext(encabezados, false);
            for(Cuenta cuenta : cuentas) {
                String[] registro = {
                        String.valueOf(cuenta.getTitular().getDni()),
                        cuenta.getTitular().getNombreCompleto(),
                        cuenta.getNroCuenta().toString(),
                        String.valueOf(cuenta.getSaldo()),
                        cuenta.getTipoCuenta(),
                        cuenta.getMoneda().name(),
                };
                csvWriter.writeNext(registro, false);
            }
            csvWriter.close();
            System.out.println("Archivo creado correctamente en: " + fullPath);
        } catch (IOException e) {
            System.out.println("Error en la ruta del archivo");
        } catch (Exception f){
            System.out.println("Ha ocurrido un error, intente nuevamente");
        }
    }
}