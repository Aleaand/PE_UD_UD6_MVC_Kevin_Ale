# Proyecto de Gestión de Videojuegos y Jugadores

Este proyecto es una aplicación que permite gestionar videojuegos, jugadores y sus partidas en una base de datos. El sistema permite almacenar información sobre videojuegos, jugadores y registrar partidas jugadas por los jugadores, incluyendo detalles como las horas jugadas y los puntos obtenidos. Además de poder ver estadisticas como los mejores jugadores basados en su experencia o puntuación, jugadores que mas horas han jugado, videojuegos con más horas jugadas. 

---

## Funcionalidades

### **Gestión de Jugadores**
1. **Agregar Jugador**: Permite registrar un nuevo jugador en el sistema con su nombre, nivel y puntuación inicial.
2. **Actualizar Jugador**: Permite actualizar los datos de un jugador existente, como su nivel y puntuación.
3. **Eliminar Jugador**: Elimina un jugador del sistema a través de su ID.
4. **Ver Jugador por ID**: Permite consultar los detalles de un jugador utilizando su ID.
5. **Ver Todos los Jugadores**: Muestra la lista de todos los jugadores registrados en el sistema.

### **Gestión de Videojuegos**
1. **Agregar Videojuego**: Permite añadir un nuevo videojuego al sistema con su nombre, descripción, categoría, fecha de lanzamiento y horas jugadas.
2. **Actualizar Videojuego**: Permite modificar los detalles de un videojuego ya registrado.
3. **Eliminar Videojuego**: Elimina un videojuego del sistema a través de su ID.
4. **Ver Videojuego por ID**: Permite consultar los detalles de un videojuego utilizando su ID.
5. **Ver Todos los Videojuegos**: Muestra la lista de todos los videojuegos registrados en el sistema.

### **Gestión de Partidas**
1. **Agregar Partida**: Permite registrar una nueva partida, asociándola con un videojuego y un jugador, e incluyendo detalles como la fecha, horas jugadas y puntos obtenidos.
2. **Actualizar Partida**: Permite actualizar los detalles de una partida registrada.
3. **Eliminar Partida**: Elimina una partida del sistema mediante su ID.
4. **Ver Partida por ID**: Permite consultar los detalles de una partida a través de su ID.
5. **Ver Todas las Partidas**: Muestra la lista de todas las partidas registradas en el sistema.

### **Estadísticas**
1. **Ver Estadísticas de Horas de Juego**: Muestra un informe de las horas jugadas por cada jugador o videojuego.
2. **Ver Estadísticas de Puntuación**: Presenta un análisis de las puntuaciones obtenidas por los jugadores en sus partidas.
3. **Ver Estadísticas de Experiencia**: Proporciona un informe de la experiencia acumulada por los jugadores a lo largo de sus partidas.
4. **Ver Clasificación de Videojuegos**: Muestra una clasificación de los videojuegos basada en las horas jugadas y la cantidad de jugadores que los han jugado.

---

## Base de Datos

La base de datos está compuesta por tres tablas principales: `Videojuegos`, `Jugadores` y `Partidas`. A continuación se describe la estructura de cada tabla:

### Tabla `Videojuegos`

```sql
CREATE TABLE videojuegos (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    genero VARCHAR(50),
    precio NUMERIC
);

-- Restricciones
CONSTRAINT videojuegos_pkey PRIMARY KEY (id);

```

### Tabla `Jugadores`

```sql
CREATE TABLE jugadores (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nivel INTEGER DEFAULT 0,
    puntuacion INTEGER DEFAULT 0
);

-- Restricciones
CONSTRAINT jugadores_pkey PRIMARY KEY (id);

```

### Tabla `Partidas`

```sql
CREATE TABLE partidas (
    id SERIAL PRIMARY KEY,
    id_videojuego INTEGER,
    id_jugador INTEGER,
    fecha_partida DATE,
    horas_jugadas INTEGER,
    puntos_obtenidos INTEGER,
    FOREIGN KEY (id_videojuego) REFERENCES public.videojuegos (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (id_jugador) REFERENCES public.jugadores (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Restricciones
CONSTRAINT partidas_pkey PRIMARY KEY (id);

```
En el archivo `config.json`, puedes encontrar la configuración necesaria para la conexión con la base de datos PostgreSQL. Este archivo contiene la URL de la base de datos, el nombre de usuario y la contraseña necesarios para acceder a la base de datos.

### Conexión en `config.json`:

```json
{
  "url": "jdbc:postgresql://ep-cool-cherry-a88a60cq.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_oySvTleZ05bD&sslmode=require",
  "user": "neondb_owner",
  "password": "npg_oySvTleZ05bD"
}
```
1. **url**: La URL de conexión JDBC que define cómo se conecta la aplicación a la base de datos PostgreSQL. 

2. **user**: El nombre de usuario para la conexión a la base de datos.

3. **password**: La contraseña asociada al usuario para autenticar la conexión. 

---

## Vistas

En la clase principal de la aplicación (`App`), se gestiona la inicialización y el cambio entre las vistas de la aplicación. Las vistas son responsables de mostrar la interfaz de usuario, y en este caso, se cuenta con dos tipos de vistas que puedes configurar y alternar desde la clase `App`:

1. **Vista JavaFX (`VistaJavaFX`)**: Esta vista utiliza la tecnología JavaFX para crear interfaces gráficas. Si prefieres una experiencia visual más interactiva y moderna, esta es la vista adecuada. En el código, se registra con el nombre `"vistaJavaFX"` y se asocia con el controlador de la aplicación.

2. **Vista Consola (`VistaConsolaRouter`)**: Esta vista utiliza una interfaz basada en consola, donde el usuario interactúa con el sistema mediante texto. Es útil para quienes prefieren una interfaz más simple o cuando no se dispone de un entorno gráfico. En el código, se registra con el nombre `"vistaConsola"` y también se asocia con el controlador correspondiente.

### **Cómo configurar la vista**

El comportamiento de la vista que se inicializa se controla mediante el método `inicializarVista` del `RouterCV`. En el código, se inicializa **por defecto** la vista de consola con la siguiente línea:

```java
router.inicializarVista("vistaConsola");
```

Sin embargo, puedes cambiar fácilmente entre la vista gráfica y la consola modificando esta línea de código. Por ejemplo, si prefieres iniciar la aplicación con la vista JavaFX, basta con descomentar la línea correspondiente y comentar la de la vista de consola:

```java
router.inicializarVista("vistaJavaFX");
```

De esta manera, puedes alternar entre las vistas gráficas y de consola según lo necesites, facilitando la personalización del comportamiento de la aplicación en función de las preferencias del usuario o de la configuración del entorno.

---

## Autores

[- **Alejandra Andrade** ](https://github.com/Aleaand)

[- **Kevin Villarreal** ](https://github.com/ktr19)
