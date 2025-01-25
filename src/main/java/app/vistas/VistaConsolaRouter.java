package app.vistas;

import app.core.RouterCV;
import app.modelos.Jugador;
import app.modelos.Partida;
import app.modelos.Videojuego;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


public class VistaConsolaRouter implements Vista {
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
            System.out.println("4. Ver Clasificación de Videojuegos"); // Nueva opción
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> verEstadisticasHoras();
                case 2 -> verEstadisticasPuntuacion();
                case 3 -> verEstadisticasExperiencia();
                case 4 -> mostrarClasificacionVideojuegos(); // Llama al nuevo método
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
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
        System.out.println(router.ejecutarAccion("jugadores", "actualizarJugador", id, nombre, nivel, puntuacion));
    }

    private void eliminarJugador(Scanner scanner) {
        System.out.print("Ingrese ID del jugador que desea eliminar: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("jugadores", "eliminarJugador", id));
    }

    private void verJugadorID(Scanner scanner) {
        System.out.print("Ingrese ID del jugador que desea ver: ");
        int id = scanner.nextInt();

        Jugador jugador = (Jugador) router.ejecutarAccion("jugadores", "verJugadorID", id);

        if (jugador != null) {
            System.out.println("\n+------------------ Detalles del Jugador ------------------+");
            System.out.printf("| %-20s: %-30s |\n", "ID", jugador.getId());
            System.out.printf("| %-20s: %-30s |\n", "Nombre", jugador.getNombre());
            System.out.printf("| %-20s: %-30s |\n", "Nivel", jugador.getNivel());
            System.out.printf("| %-20s: %-30s |\n", "Puntuación", jugador.getPuntuacion());
            System.out.println("+---------------------------------------------------------+");
        } else {
            System.out.println("\nNo se encontró ningún jugador con el ID proporcionado.");
        }
    }


    private void listarJugadores() {
        @SuppressWarnings("unchecked")
        List<Jugador> jugadores = (List<Jugador>) router.ejecutarAccion("jugadores", "listarJugadores");

        if (jugadores != null && !jugadores.isEmpty()) {
            // Calcula el ancho dinámico del nombre más largo
            int maxNombreLength = jugadores.stream()
                    .map(j -> j.getNombre() != null ? j.getNombre().length() : 0)
                    .max(Integer::compare)
                    .orElse(20); // Longitud mínima por defecto

            // Encabezado estilizado
            System.out.println("\n╔═════╦" + "═".repeat(maxNombreLength + 2) + "╦════════╦══════════════╗");
            System.out.printf("║ %-3s ║ %-" + maxNombreLength + "s ║ %-6s ║ %-12s ║\n", "ID", "Nombre", "Nivel", "Puntuación");
            System.out.println("╠═════╬" + "═".repeat(maxNombreLength + 2) + "╬════════╬══════════════╣");

            // Recorre e imprime cada jugador
            for (Jugador jugador : jugadores) {
                String nombre = jugador.getNombre() != null ? jugador.getNombre() : "Desconocido";
                System.out.printf(
                        "║ %-3d ║ %-" + maxNombreLength + "s ║ %-6d ║ %-12d ║\n",
                        jugador.getId(),
                        nombre,
                        jugador.getNivel(),
                        jugador.getPuntuacion()
                );
            }

            // Línea inferior de la tabla
            System.out.println("╚═════╩" + "═".repeat(maxNombreLength + 2) + "╩════════╩══════════════╝");
        } else {
            System.out.println("\n⚠️  No se encontraron jugadores.");
        }
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
        System.out.println(router.ejecutarAccion("videojuegos", "actualizarVideojuego", id, titulo, genero, precio));
    }

    private void eliminarVideojuego(Scanner scanner) {
        System.out.print("Ingrese ID del videojuego que desea eliminar: ");
        int id = scanner.nextInt();
        System.out.println(router.ejecutarAccion("videojuegos", "eliminarVideojuego", id));
    }

    private void verVideojuegoID(Scanner scanner) {
        System.out.print("Ingrese ID del videojuego que desea ver: ");
        int id = scanner.nextInt();

        // Obtiene el objeto Videojuego desde el router
        Videojuego videojuego = (Videojuego) router.ejecutarAccion("videojuegos", "getVideojuego", id);

        // Verifica si el objeto Videojuego no es nulo
        if (videojuego != null) {
            System.out.println("\n--- Detalles del Videojuego ---");
            System.out.printf("ID: %d\n", videojuego.getId());
            System.out.printf("Título: %s\n", videojuego.getTitulo() != null ? videojuego.getTitulo() : "Desconocido");
            System.out.printf("Género: %s\n", videojuego.getGenero() != null ? videojuego.getGenero() : "No especificado");
            System.out.printf("Precio: %.2f €\n", videojuego.getPrecio() != null ? videojuego.getPrecio() : BigDecimal.valueOf(0));

            // Imprime las partidas asociadas si existen
            if (videojuego.getPartidas() != null && !videojuego.getPartidas().isEmpty()) {
                System.out.println("Partidas asociadas:");
                videojuego.getPartidas().forEach(partida -> {
                    System.out.printf("  - ID Partida: %d\n", partida.getId()); // Ajustar según el modelo Partida
                });
            } else {
                System.out.println("No hay partidas asociadas.");
            }

            System.out.println("-------------------------------");
        } else {
            System.out.println("No se encontró un videojuego con el ID especificado.");
        }
    }


    private void listarVideojuegos() {
        // Obtiene la lista de videojuegos desde el router
        List<Videojuego> videojuegos = (List<Videojuego>) router.ejecutarAccion("videojuegos", "listarVideojuegos");

        // Comprueba si la lista no está vacía
        if (videojuegos != null && !videojuegos.isEmpty()) {
            // Calcula el ancho máximo del título dinámicamente
            int maxTituloLength = videojuegos.stream()
                    .map(v -> v.getTitulo() != null ? v.getTitulo().length() : 0)
                    .max(Integer::compare)
                    .orElse(20); // Longitud mínima por defecto

            // Encabezado dinámico
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                   📜 Lista de Videojuegos                  ║");
            System.out.println("╠═════╦" + "═".repeat(maxTituloLength + 2) + "╦═══════════════╦═════════════╣");
            System.out.printf("║ %-3s ║ %-" + maxTituloLength + "s ║ %-13s ║ %-11s ║\n", "ID", "Título", "Género", "Precio (€)");
            System.out.println("╠═════╬" + "═".repeat(maxTituloLength + 2) + "╬═══════════════╬═════════════╣");

            // Recorre e imprime cada videojuego
            for (Videojuego videojuego : videojuegos) {
                String titulo = videojuego.getTitulo() != null ? videojuego.getTitulo() : "Desconocido";
                String genero = videojuego.getGenero() != null ? videojuego.getGenero() : "No especificado";
                BigDecimal precio = videojuego.getPrecio() != null ? videojuego.getPrecio() : BigDecimal.ZERO;

                System.out.printf(
                        "║ %-3d ║ %-" + maxTituloLength + "s ║ %-13s ║ %-11.2f ║\n",
                        videojuego.getId(),
                        titulo,
                        genero,
                        precio
                );
            }

            System.out.println("╚═════╩" + "═".repeat(maxTituloLength + 2) + "╩═══════════════╩═════════════╝");
        } else {
            System.out.println("\n⚠️  No hay videojuegos disponibles.");
        }
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
        System.out.println(router.ejecutarAccion("partidas", "agregarPartida", jugador, videojuego, puntos, duracion, fecha));
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
        Partida partida = new Partida(id, jugador, videojuego, duracion, puntuacion, fecha);
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

        // Obtén el objeto Partida desde el router
        Partida partida = (Partida) router.ejecutarAccion("partidas", "getPartida", id);

        // Comprueba si la partida existe
        if (partida != null) {
            System.out.println("\n--- Detalles de la Partida ---");
            System.out.printf("ID: %d\n", partida.getId());
            System.out.printf("Jugador: %s\n", partida.getIdJugador().getNombre());
            System.out.printf("Videojuego: %s\n", partida.getIdVideojuego().getTitulo());
            System.out.printf("Fecha de la Partida: %s\n", partida.getFechaPartida());
            System.out.printf("Horas Jugadas: %d\n", partida.getHorasJugadas());
            System.out.printf("Puntos Obtenidos: %d\n", partida.getPuntosObtenidos());
        } else {
            System.out.println("No se encontró ninguna partida con el ID especificado.");
        }
    }


    private void listarPartidas() {
        @SuppressWarnings("unchecked")
        List<Partida> partidas = (List<Partida>) router.ejecutarAccion("partidas", "listarPartidas");

        if (partidas != null && !partidas.isEmpty()) {
            // Calcula el ancho dinámico para nombres de jugadores y videojuegos
            int maxNombreJugadorLength = partidas.stream()
                    .map(p -> p.getIdJugador() != null && p.getIdJugador().getNombre() != null ? p.getIdJugador().getNombre().length() : 0)
                    .max(Integer::compare)
                    .orElse(10);

            int maxTituloVideojuegoLength = partidas.stream()
                    .map(p -> p.getIdVideojuego() != null && p.getIdVideojuego().getTitulo() != null ? p.getIdVideojuego().getTitulo().length() : 0)
                    .max(Integer::compare)
                    .orElse(15);

            // Encabezado estilizado
            System.out.println("\n╔═════╦" + "═".repeat(maxTituloVideojuegoLength + 2) + "╦" + "═".repeat(maxNombreJugadorLength + 2) + "╦════════════╦════════════╦══════════════╗");
            System.out.printf("║ %-3s ║ %-" + maxTituloVideojuegoLength + "s ║ %-" + maxNombreJugadorLength + "s ║ %-10s ║ %-10s ║ %-12s ║\n",
                    "ID", "Videojuego", "Jugador", "Fecha", "Horas", "Puntos");
            System.out.println("╠═════╬" + "═".repeat(maxTituloVideojuegoLength + 2) + "╬" + "═".repeat(maxNombreJugadorLength + 2) + "╬════════════╬════════════╬══════════════╣");

            // Recorre e imprime cada partida
            for (Partida partida : partidas) {
                String videojuegoTitulo = partida.getIdVideojuego() != null && partida.getIdVideojuego().getTitulo() != null
                        ? partida.getIdVideojuego().getTitulo()
                        : "Desconocido";

                String jugadorNombre = partida.getIdJugador() != null && partida.getIdJugador().getNombre() != null
                        ? partida.getIdJugador().getNombre()
                        : "Desconocido";

                System.out.printf(
                        "║ %-3d ║ %-" + maxTituloVideojuegoLength + "s ║ %-" + maxNombreJugadorLength + "s ║ %-10s ║ %-10d ║ %-12d ║\n",
                        partida.getId(),
                        videojuegoTitulo,
                        jugadorNombre,
                        partida.getFechaPartida() != null ? partida.getFechaPartida().toString() : "N/A",
                        partida.getHorasJugadas() != null ? partida.getHorasJugadas() : 0,
                        partida.getPuntosObtenidos() != null ? partida.getPuntosObtenidos() : 0
                );
            }

            // Línea inferior de la tabla
            System.out.println("╚═════╩" + "═".repeat(maxTituloVideojuegoLength + 2) + "╩" + "═".repeat(maxNombreJugadorLength + 2) + "╩════════════╩════════════╩══════════════╝");
        } else {
            System.out.println("\n⚠️  No se encontraron partidas.");
        }
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
        System.out.println("Ingrese la url de la congiguracion con la base de datos:");
        String URL = scanner.nextLine();
        System.out.println("Ingrese el usuario de la congiguracion con la base de datos:");
        String USER = scanner.nextLine();
        System.out.println("Ingrese la contraseña de la congiguracion con la base de datos:");
        String PASS = scanner.nextLine();
        //System.out.println(guardarConfiguracionEnJSON(configuracion););
    }

    private void mostrarClasificacionVideojuegos() {
        List<Map<String, Object>> clasificacion = (List<Map<String, Object>>) router.ejecutarAccion("partidas", "obtenerClasificacionVideojuegos");

        if (clasificacion == null || clasificacion.isEmpty()) {
            System.out.println("\nNo se encontraron datos para la clasificación de videojuegos.");
            return;
        }

        System.out.println("\n=== Clasificación de Videojuegos ===");
        System.out.printf("%-25s %-15s %-15s\n", "Videojuego", "Jugadores", "Horas");
        System.out.println("-------------------------------------------------");

        for (Map<String, Object> item : clasificacion) {
            // Obtener los valores del mapa con las claves correctas
            String titulo = (String) item.getOrDefault("nombre_videojuego", "Desconocido");
            int jugadores = item.get("total_jugadores") instanceof Integer ? (Integer) item.get("total_jugadores") : 0;
            int horas = item.get("total_horas") instanceof Integer ? (Integer) item.get("total_horas") : 0;

            // Formatear la salida de manera amigable
            System.out.printf("%-25s %-15d %-15d\n", titulo, jugadores, horas);
        }


        System.out.println("-------------------------------------------------");
    }
}

