package platzi.play.project;

import platzi.play.project.contenido.*;
import platzi.play.project.excepcion.PeliculaExistenteException;
import platzi.play.project.plataforma.Plataforma;
import platzi.play.project.util.ScannerUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        plataforma.cargarPeliculas();
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

                    int tipoDeContenido = ScannerUtils.capturarEntero("""
                            ¿Desea ingresa una película o un documental?
                                1. Película
                                2. Documental""");

                    String titulo = ScannerUtils.capturarTexto("Por favor escriba el título del contenido a agregar");
                    String descripcion = ScannerUtils.capturarTexto("Por favor escriba la descripción del contenido");
                    int duracion = ScannerUtils.capturarEntero("Por favor escriba la duración del contenido");
                    Genero genero = ScannerUtils.capturarEnum("Por favor escriba el género del contenido", Genero.class);
                    double calificacion = ScannerUtils.capturarDecimal("Por favor escriba la calificacion del contenido");

                    try{
                        if(tipoDeContenido == 1){
                            Pelicula pelicula = new Pelicula(titulo, descripcion, duracion, genero, calificacion);
                            plataforma.agregarContenido(pelicula);
                        } else{
                            String narrador = ScannerUtils.capturarTexto("Por favor escriba el narrador del contenido");
                            Documental documental = new Documental(titulo, descripcion, duracion, genero,narrador, calificacion);
                            plataforma.agregarContenido(documental);
                        }
                    }catch (PeliculaExistenteException e){
                        out.println(e.getMessage());
                    }

                }

                case MOSTRAR_TODO -> plataforma.pelisResumidas().forEach(resumenes -> out.println(resumenes. getResumen()));

                case BUSCAR_POR_TITULO -> {

                    String titulo = ScannerUtils.capturarTexto("Por favor ingrese el nombre de la película a buscar");
                    Contenido peli = plataforma.buscarPorTitulo(titulo);
                    if(peli != null){
                        while(true){
                            out.printf("¿Qué desea hacer con la película %s?%n", peli.getTitulo());
                            int opcion = ScannerUtils.capturarEntero("""
                            1. Ver película
                            2. Pausar película
                            3. Ver ficha técnica
                            4. Calificar película
                            5. Establecer idiomas
                            6. Ver idiomas de la contenido
                            """); // no sé si falta salir de búsqueda xd

                            switch (opcion){
                                case 1 -> out.println(plataforma.reproducir(peli));
                                case 2 -> out.println(peli.pausar());
                                case 3 -> out.println(peli.obtenerFichaTecnica());
                                case 4 -> {
                                    peli.calificar(ScannerUtils.capturarDecimal("Ingrese la calificación de la peli del 1 al 5"));
                                    out.printf("Calificación ingresada para película %s: %.1f/5 %n", peli.getTitulo(), peli.getCalificacion());
                                }
                                case 5 -> {
                                    out.println("Idiomas soportados en la plataforma:");
                                    plataforma.getIdiomas().forEach(s -> out.printf("Idioma: %s%n",s));
                                    List<Idioma> idiomasLista = new ArrayList<>();

                                    while (true){
                                        Idioma idiomaNuevo = ScannerUtils.capturarEnum("Por favor inrese el neuvo idioma de la peli", Idioma.class);
                                        idiomasLista.add(idiomaNuevo);

                                        int eleccion = ScannerUtils.capturarEntero("""
                                            Desea ingresar otro idioma a esta película?
                                                1. SI
                                                2. NO""");
                                        while (true){
                                            if(eleccion==2 || eleccion == 1){
                                                break;
                                            }else{
                                                out.println("Ingrese una opción válida: ");
                                                eleccion = ScannerUtils.capturarEntero("""
                                                        Desea ingresar otro idioma a esta película?
                                                            1. SI
                                                            2. NO""");
                                            }
                                        }
                                        if (eleccion == 2){break;}
                                    }
                                    peli.establecerIdiomas(idiomasLista);
                                }
                                case 6 -> {
                                    out.printf("La película %s tiene los siguientes idiomas:%n", peli.getTitulo());
                                    peli.obtenerIdiomas().stream().map(Idioma::name).forEach(s -> out.printf("idioma: %s%n", s.toLowerCase()));
                                }
                            }
                            break;
                        }
                    }else{
                        out.println("Oops! Lamentamos decirte que la película a buscar aún no existe en " + NOMBRE_PLATAFORMA);
                    }
                }

                case BUSCAR_POR_GENERO -> {
                    out.printf("Géneros disponibles: \n");
                    plataforma.getGeneros().forEach(genero -> out.printf("Género %-10s%n", genero));

                    Genero genero = ScannerUtils.capturarEnum("Por favor ingrese el género que desea ver",  Genero.class);
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
//    private static Idioma convertirIdiomas(String idiomaS){
//        String actual = idiomaS;
//        while(true){
//            try{
//                return Idioma.valueOf(actual.toUpperCase().trim());
//            } catch (IllegalArgumentException e) {
//                out.println("Por favor ingrese un idioma válido");
//                actual = ScannerUtils.capturarTexto("");
//            }
//        }
//    }


}
//Encapsulameinto: es ocultar o proteger los detalles internos de una clase para que solo se pueda acceder a la info de esa clase de una forma controlada
