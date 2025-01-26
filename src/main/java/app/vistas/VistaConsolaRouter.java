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

        // Mensaje de bienvenida
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                🌟 Bienvenidos                      ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        do {
            // Menú principal con formato bonito
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║                   📜 Menú Principal               ║");
            System.out.println("╠═════╦══════════════════════════════════════════════╣");
            System.out.println("║  1  ║ Jugadores                                   ║");
            System.out.println("║  2  ║ Videojuego                                  ║");
            System.out.println("║  3  ║ Partida                                     ║");
            System.out.println("║  4  ║ Estadísticas                                ║");
            System.out.println("║  5  ║ Salir del sistema                           ║");
            System.out.println("╠═════╩══════════════════════════════════════════════╣");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerNumeroEnteroValido(scanner);

            switch (opcion) {
                case 1 -> menuJugador(scanner);
                case 2 -> menuVideojuego(scanner);
                case 3 -> menuPartida(scanner);
                case 4 -> menuEstadistica(scanner);
                case 5 -> {
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }


    private void menuJugador(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║                📋 Menú de Jugadores               ║");
            System.out.println("╠═════╦══════════════════════════════════════════════╣");
            System.out.println("║  1  ║ Agregar Jugador                              ║");
            System.out.println("║  2  ║ Actualizar Jugador                           ║");
            System.out.println("║  3  ║ Eliminar Jugador                             ║");
            System.out.println("║  4  ║ Ver Jugador por ID                           ║");
            System.out.println("║  5  ║ Ver todos los Jugadores                      ║");
            System.out.println("║  6  ║ Volver al menú principal                     ║");
            System.out.println("╠═════╩══════════════════════════════════════════════╣");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerNumeroEnteroValido(scanner);

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
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║               🎮 Menú de Videojuegos               ║");
            System.out.println("╠═════╦══════════════════════════════════════════════╣");
            System.out.println("║  1  ║ Agregar Videojuego                           ║");
            System.out.println("║  2  ║ Actualizar Videojuego                        ║");
            System.out.println("║  3  ║ Eliminar Videojuego                          ║");
            System.out.println("║  4  ║ Ver Videojuego por ID                        ║");
            System.out.println("║  5  ║ Ver todos los Videojuegos                    ║");
            System.out.println("║  6  ║ Volver al menú principal                     ║");
            System.out.println("╠═════╩══════════════════════════════════════════════╣");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerNumeroEnteroValido(scanner);

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
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║                 🎮 Menú de Partidas                ║");
            System.out.println("╠═════╦══════════════════════════════════════════════╣");
            System.out.println("║  1  ║ Agregar Partida                              ║");
            System.out.println("║  2  ║ Actualizar Partida                           ║");
            System.out.println("║  3  ║ Eliminar Partida                             ║");
            System.out.println("║  4  ║ Ver Partida por ID                           ║");
            System.out.println("║  5  ║ Ver todas las Partidas                       ║");
            System.out.println("║  6  ║ Volver al menú principal                     ║");
            System.out.println("╠═════╩══════════════════════════════════════════════╣");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerNumeroEnteroValido(scanner);

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
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║              📊 Menú de Estadísticas               ║");
            System.out.println("╠═════╦══════════════════════════════════════════════╣");
            System.out.println("║  1  ║ Ver Estadísticas de horas de juego          ║");
            System.out.println("║  2  ║ Ver Estadísticas de puntuación              ║");
            System.out.println("║  3  ║ Ver Estadísticas de experiencia             ║");
            System.out.println("║  4  ║ Ver Clasificación de Videojuegos            ║");
            System.out.println("║  5  ║ Volver al menú principal                    ║");
            System.out.println("╠═════╩══════════════════════════════════════════════╣");
            System.out.print("Seleccione una opción: ");
            opcion = obtenerNumeroEnteroValido(scanner);

            switch (opcion) {
                case 1 -> verEstadisticasHoras();
                case 2 -> verEstadisticasPuntuacion();
                case 3 -> verEstadisticasExperiencia();
                case 4 -> mostrarClasificacionVideojuegos();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }


    private void agregarJugador(Scanner scanner) {
        System.out.print("Ingrese nombre del jugador: ");
        String nombre = obtenerTextoValido(scanner);
        System.out.print("Ingrese nivel del jugador: ");
        int nivel = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese puntuación del jugador: ");
        int puntuacion = obtenerNumeroEnteroValido(scanner);

        System.out.println(router.ejecutarAccion("jugadores", "agregarJugador", nombre, nivel, puntuacion));
    }

    private void actualizaJugador(Scanner scanner) {
        String nombre = "", respuesta = "";
        int nivel = 0;
        int puntuacion = 0;

        System.out.println("ID del jugador: :");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese el nuevo nombre del jugador: ");
        nombre = obtenerTextoValido(scanner);
        System.out.print("Ingrese el nuevo nivel del jugador: ");
        nivel = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese la nueva puntuación del jugador: ");
        puntuacion = obtenerNumeroEnteroValido(scanner);
        System.out.println(router.ejecutarAccion("jugadores", "actualizarJugador", id, nombre, nivel, puntuacion));
    }

    private void eliminarJugador(Scanner scanner) {
        System.out.print("Ingrese ID del jugador que desea eliminar: ");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.println(router.ejecutarAccion("jugadores", "eliminarJugador", id));
    }

    private void verJugadorID(Scanner scanner) {
        System.out.print("Ingrese ID del jugador que desea ver: ");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.println(router.ejecutarAccion("jugadores", "verJugadorID", id));
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

            // Encabezado con título decorado
            System.out.println("\n╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                     🏆 Lista de Jugadores                ║");
            System.out.println("╠═════╦" + "═".repeat(maxNombreLength + 2) + "╦════════╦══════════════╣");
            System.out.printf("║ %-3s ║ %-" + maxNombreLength + "s ║ %-6s ║ %-12s ║\n", "ID", "Nombre", "Nivel", "Puntuación");
            System.out.println("╠═════╬" + "═".repeat(maxNombreLength + 2) + "╬════════╬══════════════╣");

            // Recorre e imprime cada jugador
            for (Jugador jugador : jugadores) {
                String nombre = jugador.getNombre() != null ? jugador.getNombre() : "Desconocido";
                int nivel = jugador.getNivel();
                int puntuacion = jugador.getPuntuacion();

                System.out.printf(
                        "║ %-3d ║ %-" + maxNombreLength + "s ║ %-6d ║ %-12d ║\n",
                        jugador.getId(),
                        nombre,
                        nivel,
                        puntuacion
                );
            }

            // Línea final de la tabla
            System.out.println("╚═════╩" + "═".repeat(maxNombreLength + 2) + "╩════════╩══════════════╝");
        } else {
            System.out.println("\n⚠️  No se encontraron jugadores.");
        }
    }

    private void agregarVideojuego(Scanner scanner) {
        System.out.print("Ingrese título del videojuego: ");
        String titulo = obtenerTextoValido(scanner);
        System.out.print("Ingrese género del videojuego: ");
        String genero = obtenerTextoValido(scanner);
        System.out.print("Ingrese precio del videojuego: ");
        double precio = obtenerNumeroDecimalValido(scanner);

        System.out.println(router.ejecutarAccion("videojuegos", "agregarVideojuego", titulo, genero, precio));
    }

    private void actualizarVideojuego(Scanner scanner) {
        String titulo = "";
        String genero = "";
        double precio = 0.0;
        System.out.println("ID del videojuego: :");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese el nuevo título del videojuego: ");
        titulo = obtenerTextoValido(scanner);
        System.out.print("Ingrese el nuevo género del videojuego: ");
        genero = obtenerTextoValido(scanner);
        System.out.print("Ingrese el nuevo precio del videojuego: ");
        precio = obtenerNumeroDecimalValido(scanner);
        System.out.println(router.ejecutarAccion("videojuegos", "actualizarVideojuego", id, titulo, genero, precio));
    }

    private void eliminarVideojuego(Scanner scanner) {
        System.out.print("Ingrese ID del videojuego que desea eliminar: ");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.println(router.ejecutarAccion("videojuegos", "eliminarVideojuego", id));
    }

    private void verVideojuegoID(Scanner scanner) {
        System.out.print("Ingrese ID del videojuego que desea ver: ");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.println(router.ejecutarAccion("videojuegos", "getVideojuego", id));
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
        int idJugador = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese ID del videojuego: ");
        int idVideojuego = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese duración de la partida (en horas): ");
        int duracion = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese fecha de la partida (formato yyyy-mm-dd): ");
        String fechaStr = obtenerTextoValido(scanner);
        System.out.println("Ingrese el numero de puntos conseguidos");
        int puntos = obtenerNumeroEnteroValido(scanner);
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
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese el nuevo ID del jugador: ");
        idJugador = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese el nuevo ID del videojuego: ");
        idVideojuego = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese la nueva duración de la partida (int): ");
        duracion = obtenerNumeroEnteroValido(scanner);
        System.out.print("Ingrese la nueva fecha de la partida (formato yyyy-mm-dd): ");
        String fechaStr = obtenerTextoValido(scanner);
        System.out.print("Ingrese la puntuacion: ");
        puntuacion = obtenerNumeroEnteroValido(scanner);

        fecha = LocalDate.parse(fechaStr);
        Jugador jugador = (Jugador) router.ejecutarAccion("jugadores", "verJugadorID", idJugador);
        Videojuego videojuego = (Videojuego) router.ejecutarAccion("videojuegos", "getVideojuego", idVideojuego);
        Partida partida = new Partida(id, jugador, videojuego, duracion, puntuacion, fecha);
        System.out.println(router.ejecutarAccion("partidas", "actualizarPartida", partida));
    }

    private void eliminarPartida(Scanner scanner) {
        System.out.print("Ingrese ID de la partida que desea eliminar: ");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.println(router.ejecutarAccion("partidas", "eliminarPartida", id));
    }

    private void verPartidaID(Scanner scanner) {
        System.out.print("Ingrese ID de la partida que desea ver: ");
        int id = obtenerNumeroEnteroValido(scanner);
        System.out.println(router.ejecutarAccion("partidas", "getPartida", id));
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

            // Encabezado estilizado con título
            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                               🎮 Historial de Partidas                                   ║");
            System.out.println("╠═════╦" + "═".repeat(maxTituloVideojuegoLength + 2) + "╦" + "═".repeat(maxNombreJugadorLength + 2) + "╦════════════╦════════════╦══════════════╣");
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

                String fechaPartida = partida.getFechaPartida() != null ? partida.getFechaPartida().toString() : "N/A";
                int horasJugadas = partida.getHorasJugadas() != null ? partida.getHorasJugadas() : 0;
                int puntosObtenidos = partida.getPuntosObtenidos() != null ? partida.getPuntosObtenidos() : 0;

                System.out.printf(
                        "║ %-3d ║ %-" + maxTituloVideojuegoLength + "s ║ %-" + maxNombreJugadorLength + "s ║ %-10s ║ %-10d ║ %-12d ║\n",
                        partida.getId(),
                        videojuegoTitulo,
                        jugadorNombre,
                        fechaPartida,
                        horasJugadas,
                        puntosObtenidos
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
        @SuppressWarnings("unchecked")
        List<Partida> partidas = (List<Partida>) router.ejecutarAccion("partidas", "verEstadisticasHoras");

        if (partidas == null || partidas.isEmpty()) {
            System.out.println("\n⚠️  No se encontraron datos para las partidas más largas.");
            return;
        }

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
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║               ⏳ Top 10 Partidas Más Largas                   ║");
        System.out.println("╠═════╦" + "═".repeat(maxTituloVideojuegoLength + 2) + "╦" + "═".repeat(maxNombreJugadorLength + 2) + "╦════════════╦════════════╦══════════════╗");
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
    }


    private void verEstadisticasPuntuacion() {
        @SuppressWarnings("unchecked")
        List<Jugador> jugadores = (List<Jugador>) router.ejecutarAccion("jugadores", "verEstadisticasPuntuacion");

        if (jugadores == null || jugadores.isEmpty()) {
            System.out.println("\n⚠️  No se encontraron datos para la clasificación de puntuaciones.");
            return;
        }

        // Calcula el ancho dinámico para los nombres de jugadores
        int maxNombreLength = jugadores.stream()
                .map(jugador -> jugador.getNombre() != null ? jugador.getNombre().length() : 0)
                .max(Integer::compare)
                .orElse(10); // Mínimo 10 caracteres

        // Encabezado estilizado
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║               🏆 Top 10 Jugadores por Puntuación          ║");
        System.out.println("╠═════╦" + "═".repeat(maxNombreLength + 2) + "╦════════════╦════════════╗");
        System.out.printf("║ %-3s ║ %-" + maxNombreLength + "s ║ %-10s ║ %-10s ║\n",
                "ID", "Jugador", "Nivel", "Puntuación");
        System.out.println("╠═════╬" + "═".repeat(maxNombreLength + 2) + "╬════════════╬════════════╣");

        // Recorre e imprime cada jugador
        for (Jugador jugador : jugadores) {
            String nombre = jugador.getNombre() != null ? jugador.getNombre() : "Desconocido";
            System.out.printf(
                    "║ %-3d ║ %-" + maxNombreLength + "s ║ %-10d ║ %-10d ║\n",
                    jugador.getId(),
                    nombre,
                    jugador.getNivel(),
                    jugador.getPuntuacion()
            );
        }

        // Línea inferior de la tabla
        System.out.println("╚═════╩" + "═".repeat(maxNombreLength + 2) + "╩════════════╩════════════╝");
    }


    private void verEstadisticasExperiencia() {
        @SuppressWarnings("unchecked")
        List<Jugador> jugadores = (List<Jugador>) router.ejecutarAccion("jugadores", "verEstadisticasExperiencia");

        if (jugadores == null || jugadores.isEmpty()) {
            System.out.println("\n⚠️  No se encontraron datos para la clasificación de experiencia.");
            return;
        }

        // Calcula el ancho dinámico para los nombres de jugadores
        int maxNombreLength = jugadores.stream()
                .map(jugador -> jugador.getNombre() != null ? jugador.getNombre().length() : 0)
                .max(Integer::compare)
                .orElse(10); // Mínimo 10 caracteres

        // Encabezado estilizado
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║               🏅 Top 10 Jugadores por Experiencia          ║");
        System.out.println("╠═════╦" + "═".repeat(maxNombreLength + 2) + "╦════════════╦════════════╗");
        System.out.printf("║ %-3s ║ %-" + maxNombreLength + "s ║ %-10s ║ %-10s ║\n",
                "ID", "Jugador", "Nivel", "Puntuación");
        System.out.println("╠═════╬" + "═".repeat(maxNombreLength + 2) + "╬════════════╬════════════╣");

        // Recorre e imprime cada jugador
        for (Jugador jugador : jugadores) {
            String nombre = jugador.getNombre() != null ? jugador.getNombre() : "Desconocido";
            System.out.printf(
                    "║ %-3d ║ %-" + maxNombreLength + "s ║ %-10d ║ %-10d ║\n",
                    jugador.getId(),
                    nombre,
                    jugador.getNivel(),
                    jugador.getPuntuacion()
            );
        }

        // Línea inferior de la tabla
        System.out.println("╚═════╩" + "═".repeat(maxNombreLength + 2) + "╩════════════╩════════════╝");
    }


    private void mostrarClasificacionVideojuegos() {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> clasificacion = (List<Map<String, Object>>) router.ejecutarAccion("partidas", "obtenerClasificacionVideojuegos");

        if (clasificacion == null || clasificacion.isEmpty()) {
            System.out.println("\n⚠️  No se encontraron datos para la clasificación de videojuegos.");
            return;
        }

        // Calcula el ancho dinámico para los nombres de videojuegos
        int maxTituloVideojuegoLength = clasificacion.stream()
                .map(item -> item.get("nombre_videojuego") != null ? item.get("nombre_videojuego").toString().length() : 0)
                .max(Integer::compare)
                .orElse(20); // Mínimo 20 caracteres

        // Encabezado estilizado
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║               🏆 Clasificación de Videojuegos              ║");
        System.out.println("╠═════╦" + "═".repeat(maxTituloVideojuegoLength + 2) + "╦════════════╦════════════╗");
        System.out.printf("║ %-3s ║ %-" + maxTituloVideojuegoLength + "s ║ %-10s ║ %-10s ║\n",
                "ID", "Videojuego", "Jugadores", "Horas");
        System.out.println("╠═════╬" + "═".repeat(maxTituloVideojuegoLength + 2) + "╬════════════╬════════════╣");

        // Contador para ID (solo numérico, ya que no hay ID en la clasificación)
        int id = 1;

        // Recorre e imprime cada videojuego clasificado
        for (Map<String, Object> item : clasificacion) {
            // Obtener los valores con claves correctas
            String titulo = item.get("nombre_videojuego") != null ? item.get("nombre_videojuego").toString() : "Desconocido";
            int jugadores = item.get("total_jugadores") instanceof Integer ? (Integer) item.get("total_jugadores") : 0;
            int horas = item.get("total_horas") instanceof Integer ? (Integer) item.get("total_horas") : 0;

            // Imprimir la fila
            System.out.printf(
                    "║ %-3d ║ %-" + maxTituloVideojuegoLength + "s ║ %-10d ║ %-10d ║\n",
                    id++, titulo, jugadores, horas
            );
        }

        // Línea inferior de la tabla
        System.out.println("╚═════╩" + "═".repeat(maxTituloVideojuegoLength + 2) + "╩════════════╩════════════╝");
    }

    // Método para obtener solo texto (sin números ni caracteres especiales)
    public static String obtenerTextoValido(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();

            // Verificamos que el input solo contenga letras y espacios
            if (input.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ\\s]+")) {
                return input; // Retorna el texto válido
            } else {
                System.out.println("Por favor, ingresa solo texto válido (letras y espacios).");
            }
        }
    }

    // Método para obtener un número entero válido
    public static int obtenerNumeroEnteroValido(Scanner scanner) {
        int numero;
        while (true) {
            // Intentamos capturar un número entero
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                return numero; // Retorna el número entero
            } else {
                System.out.println("Por favor, ingresa un número entero válido.");
                scanner.next(); // Limpiar el buffer
            }
        }
    }

    // Método para obtener un número decimal (double) válido
    public static double obtenerNumeroDecimalValido(Scanner scanner) {
        double numero;
        while (true) {
            // Intentamos capturar un número decimal
            if (scanner.hasNextDouble()) {
                numero = scanner.nextDouble();
                return numero; // Retorna el número decimal
            } else {
                System.out.println("Por favor, ingresa un número decimal válido.");
                scanner.next(); // Limpiar el buffer
            }
        }
    }
}

