package app.vistas;

import app.core.RouterCV;
import app.modelos.Jugador;
import app.modelos.Partida;
import app.modelos.Videojuego;

import java.time.LocalDate;
import java.util.Scanner;

public class VistaConsolaRouter implements Vista{
    private final RouterCV router;

    public VistaConsolaRouter(RouterCV router) {
        this.router = router;
    }

    @Override
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Menú Principal con Router ===");
            System.out.println("1. Jugadores");
            System.out.println("2. Videojuego");
            System.out.println("3. Partida");
            System.out.println("4. Estadistica");
            System.out.println("5. Configuarción");
            System.out.println("6. Salir del sistema");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> menuJugador(scanner);
                case 2 -> menuVideojuego(scanner);
                case 3 -> menuPartida(scanner);
                case 4 -> menuEstadistica(scanner);
                case 5 -> congiguracion(scanner);
                case 6 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }


    private void menuJugador(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Menú de Jugadores ===");
            System.out.println("1. Agregar Jugador");
            System.out.println("2. Actualizar Jugador");
            System.out.println("3. Eliminar Jugador");
            System.out.println("4. Ver Jugador por ID");
            System.out.println("5. Ver todos los Jugadores");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarJugador(scanner);
                case 2 -> actualizaJugador(scanner);
                case 3 -> eliminarJugador(scanner);
                case 4 -> verJugadorID(scanner);
                case 5 -> listarJugadores();
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void menuVideojuego(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Menú de Videojuegos ===");
            System.out.println("1. Agregar Videojuego");
            System.out.println("2. Actualizar Videojuego");
            System.out.println("3. Eliminar Videojuego");
            System.out.println("4. Ver Videojuego por ID");
            System.out.println("5. Ver todos los Videojuegos");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> agregarVideojuego(scanner);
                case 2 -> actualizarVideojuego(scanner);
                case 3 -> eliminarVideojuego(scanner);
                case 4 -> verVideojuegoID(scanner);
                case 5 -> listarVideojuegos();
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void menuPartida(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Menú de Partidas ===");
            System.out.println("1. Agregar Partida");
            System.out.println("2. Actualizar Partida");
            System.out.println("3. Eliminar Partida");
            System.out.println("4. Ver Partida por ID");
            System.out.println("5. Ver todas las Partidas");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> agregarPartida(scanner);
                case 2 -> actualizarPartida(scanner);
                case 3 -> eliminarPartida(scanner);
                case 4 -> verPartidaID(scanner);
                case 5 -> listarPartidas();
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void menuEstadistica(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Menú de Estadísticas ===");
            System.out.println("1. Ver Estadísticas de horas de juego");
            System.out.println("2. Ver Estadísticas de puntuación");
            System.out.println("3. Ver Estadísticas de experiencia");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> verEstadisticasHoras();
                case 2 -> verEstadisticasPuntuacion();
                case 3 -> verEstadisticasExperiencia();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private void agregarJugador(Scanner scanner) {
        System.out.print("Ingrese nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nivel del jugador: ");
        int nivel = scanner.nextInt();
        System.out.print("Ingrese puntuación del jugador: ");
        int puntuacion = scanner.nextInt();

        System.out.println(router.ejecutarAccion("jugadores", "agregarJugador", nombre, nivel, puntuacion));
    }

    private void actualizaJugador(Scanner scanner) {
        String nombre = "", respuesta = "";
        int nivel = 0;
        int puntuacion = 0;

        System.out.println("ID del jugador: :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre del jugador: ");
        nombre = scanner.nextLine();

        System.out.print("Ingrese el nuevo nivel del jugador: ");
        nivel = scanner.nextInt();
        System.out.print("Ingrese la nueva puntuación del jugador: ");
        puntuacion = scanner.nextInt();
        System.out.println(router.ejecutarAccion("jugadores", "actualizarJugador", id,nombre, nivel, puntuacion));
    }
    private void eliminarJugador(Scanner scanner) {
        System.out.print("Ingrese ID del jugador que desea eliminar: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("jugadores", "eliminarJugador", id));
    }

    private void verJugadorID(Scanner scanner) {
        System.out.print("Ingrese ID del jugador que desea ver: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("jugadores", "verJugadorID", id));
    }
    private void listarJugadores() {
        System.out.println(router.ejecutarAccion("jugadores", "listarJugadores"));
    }


    private void agregarVideojuego(Scanner scanner) {
        System.out.print("Ingrese título del videojuego: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese género del videojuego: ");
        String genero = scanner.nextLine();
        System.out.print("Ingrese precio del videojuego: ");
        double precio = scanner.nextDouble();

        System.out.println(router.ejecutarAccion("videojuegos", "agregarVideojuego", titulo, genero, precio));
    }
    private void actualizarVideojuego(Scanner scanner) {
        String titulo = "";
        String genero = "";
        double precio = 0.0;
        System.out.println("ID del videojuego: :");
        int id = scanner.nextInt();
        System.out.print("Ingrese el nuevo título del videojuego: ");
        titulo = scanner.nextLine();
        System.out.print("Ingrese el nuevo género del videojuego: ");
        genero = scanner.nextLine();
        System.out.print("Ingrese el nuevo precio del videojuego: ");
        precio = scanner.nextDouble();
        System.out.println(router.ejecutarAccion("videojuegos", "actualizarVideojuego", id,titulo, genero, precio));
    }

    private void eliminarVideojuego(Scanner scanner) {
        System.out.print("Ingrese ID del videojuego que desea eliminar: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("videojuegos", "eliminarVideojuego", id));
    }

    private void verVideojuegoID(Scanner scanner) {
        System.out.print("Ingrese ID del videojuego que desea ver: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("videojuegos", "getVideojuego", id));
    }

    private void listarVideojuegos() {
        System.out.println(router.ejecutarAccion("videojuegos", "listarVideojuegos"));
    }

    private void agregarPartida(Scanner scanner) {
        System.out.print("Ingrese ID del jugador: ");
        int idJugador = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de la línea pendiente
        System.out.print("Ingrese ID del videojuego: ");
        int idVideojuego = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de la línea pendiente
        System.out.print("Ingrese duración de la partida (en minutos): ");
        int duracion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de la línea pendiente
        System.out.print("Ingrese fecha de la partida (formato yyyy-mm-dd): ");
        String fechaStr = scanner.nextLine();
        System.out.println("Ingrese el numero de puntos conseguidos");
        int puntos = scanner.nextInt();
        LocalDate fecha = LocalDate.parse(fechaStr);
        Jugador jugador = (Jugador) router.ejecutarAccion("jugadores", "verJugadorID", idJugador);
        Videojuego videojuego = (Videojuego) router.ejecutarAccion("videojuegos", "getVideojuego", idVideojuego);
        System.out.println(router.ejecutarAccion("partidas", "agregarPartida", jugador, videojuego,puntos, duracion, fecha));
    }

    private void actualizarPartida(Scanner scanner) {
        int idJugador = 0;
        int idVideojuego = 0;
        int duracion = 0;
        int puntuacion = 0;
        LocalDate fecha = null;
        System.out.println("ID de la partida: :");
        int id = scanner.nextInt();
        System.out.print("Ingrese el nuevo ID del jugador: ");
        idJugador = scanner.nextInt();
        System.out.print("Ingrese el nuevo ID del videojuego: ");
        idVideojuego = scanner.nextInt();
        System.out.print("Ingrese la nueva duración de la partida (int): ");
        duracion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de la línea pendiente
        System.out.print("Ingrese la nueva fecha de la partida (formato yyyy-mm-dd): ");
        String fechaStr = scanner.nextLine();
        System.out.print("Ingrese la puntuacion: ");
        puntuacion = scanner.nextInt();

        fecha = LocalDate.parse(fechaStr);
        Jugador jugador = (Jugador) router.ejecutarAccion("jugadores", "verJugadorID", idJugador);
        Videojuego videojuego = (Videojuego) router.ejecutarAccion("videojuegos", "getVideojuego", idVideojuego);
        Partida partida = new Partida(id,jugador, videojuego, duracion, puntuacion,fecha);
        System.out.println(router.ejecutarAccion("partidas", "actualizarPartida", partida));
    }

    private void eliminarPartida(Scanner scanner) {
        System.out.print("Ingrese ID de la partida que desea eliminar: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("partidas", "eliminarPartida", id));
    }

    private void verPartidaID(Scanner scanner) {
        System.out.print("Ingrese ID de la partida que desea ver: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("partidas", "getPartida", id));
    }

    private void listarPartidas() {
        System.out.println(router.ejecutarAccion("partidas", "listarPartidas"));
    }
    //MIRAR BIEN
    private void verEstadisticasHoras() {
        System.out.println(router.ejecutarAccion("partidas", "verEstadisticasHoras"));
    }
    private void verEstadisticasPuntuacion() {
        System.out.println(router.ejecutarAccion("jugadores", "verEstadisticasPuntuacion"));
    }
    private void verEstadisticasExperiencia() {
        System.out.println(router.ejecutarAccion("jugadores", "verEstadisticasExperiencia"));
    }

    private void congiguracion(Scanner scanner) {
        System.out.println("Ingrese el idioma (Español, Inglés):");
        String idioma = scanner.nextLine();
        System.out.println("Seleccione el tema (claro, oscuro):");
        String tema = scanner.nextLine();
        System.out.println("¿Desea activar las notificaciones? (true/false):");
        boolean notificaciones = Boolean.parseBoolean(scanner.nextLine());
        //System.out.println(guardarConfiguracionEnJSON(configuracion););
        System.out.println(idioma+" "+tema+" "+notificaciones);
    }
}

