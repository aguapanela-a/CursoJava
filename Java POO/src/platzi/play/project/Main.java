package platzi.play.project;

import platzi.play.project.contenido.Pelicula;
import platzi.play.project.plataforma.Plataforma;
import platzi.play.project.plataforma.Rol;
import platzi.play.project.plataforma.Usuario;
import platzi.play.project.util.ScannerUtils;
import java.time.format.DateTimeFormatter;


public class Main {
    public static final String VERSION = "1.0.0"; // contants deberían ser mayúsculas, la palabra finales para contantes
    public static final String NOMBRE_PLATAFORMA = "EStreaming B"; // Son ideales para represnetar datos como configuraciones, límites y demás valores que no cambiarán nunca
    public static final int SALIR = 5;

    public static void main(String[] args) {
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA+" v"+VERSION);

        cargarPeliculas(plataforma);

        //int contador = 0;

        while(true){ // 4 comillas para escribir varias líneas de string
            int opcionElegida = ScannerUtils.capturarEntero("""
                    Por favor igrese el número de la opción que desea escoger:
                        1. Agregar película
                        2. Mostrar todo
                        3. Buscar por título
                        4. Eliminar película
                        5. Salir de la plataforma
                        """);

            System.out.printf("Opción elegida: %d%n", opcionElegida);

            switch (opcionElegida){
                case 1 -> { // la flecha es operación lambda

                    //contador++;

                    String tituloPeli = ScannerUtils.capturarTexto("Por favor escriba el título de la película a agregar");
                    String descripcionPeli = ScannerUtils.capturarTexto("Por favor escriba la descripción de la película");
                    int duracionPeli = ScannerUtils.capturarEntero("Por favor escriba la duración de la película");
                    String generoPeli = ScannerUtils.capturarTexto("Por favor escriba el género de la película");

                    Pelicula pelicula = new Pelicula(tituloPeli, descripcionPeli, duracionPeli, generoPeli);
                    //pelicula.setIdPeli(contador);
                    plataforma.agregarPeli(pelicula);
                }
                case 2 -> {

                    for(String peli : plataforma.listarPelis()){
                        System.out.printf("Película %-10s%n", peli);

                    }
                }
                case 3 -> {

                    String titulo = ScannerUtils.capturarTexto("Por favor ingrese el nombre de la película a buscar");
                    Pelicula peli = plataforma.buscarPorTitulo(titulo);
                    if(peli != null){
                        while(true){
                            System.out.printf("¿Qué desea hacer con la película %s?%n", peli.getTitulo());
                            int opcion = ScannerUtils.capturarEntero("""
                            1. Ver película
                            2. Pausar película
                            3. Ver ficha técica
                            4. Calificar película
                            """); // no sé si falta salir de búsqueda xd

                            switch (opcion){
                                case 1 -> System.out.println(peli.reproducir());
                                case 2 -> System.out.println(peli.pausar());
                                case 3 -> System.out.println(peli.obtenerFichaTecnica());
                                case 4 -> {
                                    peli.calificar(ScannerUtils.capturarDecimal("Ingrese la calificación de la peli del 1 al 5"));
                                    System.out.printf("Calificación ingresada para película %s: %.1f/5 %n", peli.getTitulo(), peli.getCalificacion());
                                }
                            }
                            break;
                        }
                    }else{
                        System.out.println("Oops! Lamentamos decirte que la película a buscar aún no existe en " + NOMBRE_PLATAFORMA);
                    }
                }
                case 4 -> { //si elimina pero también borra el id, está bien porque el id es unico para cada película

                    int idPelicula = ScannerUtils.capturarEntero("Ingrese el ID de la película ");
                    if(plataforma.eliminarPeliPorId(idPelicula)){
                        System.out.printf("Película eliminada con éxito! %n");
                    }else {
                        System.out.println("Id de película no encontrado, por favor ingrese un id válido");
                    }
                }
                case SALIR -> System.exit(0); //Detiene el programa
            }
        }


    }
    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregarPeli(new Pelicula("Shrek"," ", 90, "Animada"));
        plataforma.agregarPeli(new Pelicula("Inception"," ", 148, "Ciencia Ficción"));
        plataforma.agregarPeli(new Pelicula("Titanic", " ",195, "Drama"));
        plataforma.agregarPeli(new Pelicula("John Wick", " ",101, "Acción"));
        plataforma.agregarPeli(new Pelicula("El Conjuro", " ",112, "Terror"));
        plataforma.agregarPeli(new Pelicula("Coco", " ",105, "Animada"));
        plataforma.agregarPeli(new Pelicula("Interstellar", " ",169, "Ciencia Ficción"));
        plataforma.agregarPeli(new Pelicula("Joker", " ",122, "Drama"));
        plataforma.agregarPeli(new Pelicula("Toy Story", " ",81, "Animada"));
        plataforma.agregarPeli(new Pelicula("Avengers: Endgame", " ",181, "Acción"));
    }
}
//Encapsulameinto: es ocultar o proteger los detalles internos de una clase para que solo se pueda acceder a la info de esa clase de una forma controlada