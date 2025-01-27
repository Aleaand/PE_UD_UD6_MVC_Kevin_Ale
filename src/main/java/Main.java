import app.core.App;
import app.vistas.VistaConsolaRouter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tipoVista; // O "consola" dependiendo de la vista que quieras inicializar.
        System.out.println("Escriba el tipo de vista (1 = javafx, 2 = consola)");
        tipoVista = sc.nextInt();
        App.setTipo(tipoVista);
        App.main(args);
    }
}

