package platzi.play.project;

import platzi.play.project.contenido.Genero;
import platzi.play.project.contenido.Pelicula;
import platzi.play.project.plataforma.Plataforma;
import platzi.play.project.plataforma.Rol;
import platzi.play.project.plataforma.Usuario;
import platzi.play.project.util.ScannerUtils;
import java.time.format.DateTimeFormatter;

import static java.lang.System.out;


public class Main {
    public static final String VERSION = "1.0.0"; // contants deberían ser mayúsculas, la palabra finales para contantes
    public static final String NOMBRE_PLATAFORMA = "EStreaming B"; // Son ideales para represnetar datos como configuraciones, límites y demás valores que no cambiarán nunca

    public static final int TOP = 5;

    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int VER_MAS_POPULARES = 6;
    public static final int OBTENER_MAS_LARGA = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        cargarPeliculas(plataforma);
        out.println(NOMBRE_PLATAFORMA+" v"+VERSION);
        out.printf("Más de %d minutos de contenido!!", plataforma.getDuracionTotal());

        //int contador = 0;

        while(true){ // 4 comillas para escribir varias líneas de string
            int opcionElegida = ScannerUtils.capturarEntero("""
                    Por favor igrese el número de la opción que desea escoger:
                        1. Agregar película
                        2. Mostrar todo
                        3. Buscar por título
                        4. Buscar por género
                        5. Ver populares
                        6. Con calificación mayor a 4
                        7. Buscar película más larga
                        8. Eliminar película
                        9. Salir de la plataforma
                        """);

            out.printf("Opción elegida: %d%n", opcionElegida);

            switch (opcionElegida){
                case AGREGAR -> { // la flecha es operación lambda

                    String tituloPeli = ScannerUtils.capturarTexto("Por favor escriba el título de la película a agregar");
                    String descripcionPeli = ScannerUtils.capturarTexto("Por favor escriba la descripción de la película");
                    int duracionPeli = ScannerUtils.capturarEntero("Por favor escriba la duración de la película");
                    Genero generoPeli = Genero.valueOf(ScannerUtils.capturarTexto("Por favor escriba el género de la película"));

                    Pelicula pelicula = new Pelicula(tituloPeli, descripcionPeli, duracionPeli, generoPeli);

                    plataforma.agregarPeli(pelicula);
                }
                case MOSTRAR_TODO -> {

                    for(String peli : plataforma.listarPelis()){
                        out.printf("Película %-10s%n", peli);
                    }
                }
                case BUSCAR_POR_TITULO -> {

                    String titulo = ScannerUtils.capturarTexto("Por favor ingrese el nombre de la película a buscar");
                    Pelicula peli = plataforma.buscarPorTitulo(titulo);
                    if(peli != null){
                        while(true){
                            out.printf("¿Qué desea hacer con la película %s?%n", peli.getTitulo());
                            int opcion = ScannerUtils.capturarEntero("""
                            1. Ver película
                            2. Pausar película
                            3. Ver ficha técnica
                            4. Calificar película
                            """); // no sé si falta salir de búsqueda xd

                            switch (opcion){
                                case 1 -> out.println(peli.reproducir());
                                case 2 -> out.println(peli.pausar());
                                case 3 -> out.println(peli.obtenerFichaTecnica());
                                case 4 -> {
                                    peli.calificar(ScannerUtils.capturarDecimal("Ingrese la calificación de la peli del 1 al 5"));
                                    out.printf("Calificación ingresada para película %s: %.1f/5 %n", peli.getTitulo(), peli.getCalificacion());
                                }
                            }
                            break;
                        }
                    }else{
                        out.println("Oops! Lamentamos decirte que la película a buscar aún no existe en " + NOMBRE_PLATAFORMA);
                    }
                }

                case BUSCAR_POR_GENERO -> {
                    out.printf("Géneros dsiponibles: \n");
                    plataforma.getGeneros().forEach(genero -> out.printf("Género %-10s%n", genero));

                    Genero genero = ScannerUtils.capturarGenero("Por favor ingrese el género que desea ver");
                    plataforma.buscarPorGenero(genero).forEach(s -> out.printf("Película %-10s%n", s));
                }

                case VER_POPULARES -> {

                    out.printf("Top %d películas más populares de %s%n", TOP, NOMBRE_PLATAFORMA);
                    plataforma.getPopulares(TOP).forEach(peli -> out.printf("Película %-10s%n", peli));
                }
                case VER_MAS_POPULARES -> {
                    out.println("Películas más populares de la plataforma");
                    plataforma.getMasPopulares().forEach(peli -> out.printf("Película %-10s%n", peli));
                }
                case OBTENER_MAS_LARGA ->  {
                    out.printf("La película %-10s%n ", plataforma.obtenerMasLarga());
                }
                case ELIMINAR -> {
                    //si elimina pero también borra el id, está bien porque el id es unico para cada película

                    int idPelicula = ScannerUtils.capturarEntero("Ingrese el ID de la película a eliminar");
                    if(plataforma.eliminarPeliPorId(idPelicula)){
                        out.printf("Película eliminada con éxito! %n");
                    }else {
                        out.println("Id de película no encontrado, por favor ingrese un id válido");
                    }
                }
                case SALIR -> System.exit(0); //Detiene el programa
            }
        }


    }
    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregarPeli(new Pelicula("Shrek"," ", 90, Genero.ANIMADA));
        plataforma.agregarPeli(new Pelicula("Inception"," ", 148, Genero.CIENCIA_FICCION));
        plataforma.agregarPeli(new Pelicula("Titanic", " ",195, Genero.DRAMA));
        plataforma.agregarPeli(new Pelicula("John Wick", " ",101, Genero.ACCION));
        plataforma.agregarPeli(new Pelicula("El Conjuro", " ",112, Genero.TERROR));
        plataforma.agregarPeli(new Pelicula("Coco", " ",105, Genero.ANIMADA));
        plataforma.agregarPeli(new Pelicula("Interstellar", " ",169, Genero.CIENCIA_FICCION));
        plataforma.agregarPeli(new Pelicula("Joker", " ",122, Genero.DRAMA));
        plataforma.agregarPeli(new Pelicula("Toy Story", " ",81, Genero.ANIMADA));
        plataforma.agregarPeli(new Pelicula("Avengers: Endgame", " ",181, Genero.ACCION));
    }
}
//Encapsulameinto: es ocultar o proteger los detalles internos de una clase para que solo se pueda acceder a la info de esa clase de una forma controlada