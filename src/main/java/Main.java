import app.core.App;
import app.vistas.VistaConsolaRouter;

import java.util.Scanner;

import app.core.App;
import java.util.Scanner;

/**
 * La clase {@code Main} es la clase principal que inicia la aplicación, solicitando al usuario
 * el tipo de vista que desea utilizar (JavaFX o Consola).
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tipoVista;

        // Solicitar al usuario el tipo de vista y asegurarse de que sea 1 o 2
        do {
            System.out.println("Escriba el tipo de vista (1 = javafx, 2 = consola)");
            while (!sc.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido (1 = javafx, 2 = consola)");
                sc.next(); // Descarta la entrada no válida
            }
            tipoVista = sc.nextInt();
        } while (tipoVista != 1 && tipoVista != 2);

        // Establecer el tipo de vista en la aplicación
        App.setTipo(tipoVista);

        // Iniciar la aplicación
        App.main(args);
    }
}
