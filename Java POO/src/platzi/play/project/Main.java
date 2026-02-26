package platzi.play.project;

import platzi.play.project.contenido.*;
import platzi.play.project.excepcion.CalificacionException;
import platzi.play.project.excepcion.ContenidoYaCalificadoException;
import platzi.play.project.excepcion.PeliculaExistenteException;
import platzi.play.project.plataforma.Plataforma;
import platzi.play.project.util.ScannerUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * <h1>EStreaming B</h1>
 * <p>Es un programa que permite visualizar y gestionar películas y documentales,</p>
 *
 * @author Erick Buitrago
 * @version 1.0.0
 * @since 2026
 *
 */

public class Main {
    public static final String VERSION = "1.0.0"; // contants deberían ser mayúsculas, la palabra finales para contantes
    public static final String NOMBRE_PLATAFORMA = "EStreaming B"; // Son ideales para represnetar datos como configuraciones, límites y demás valores que no cambiarán nunca

    public static final int TOP = 5;

    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int FILTRAR_POR_TIPO = 5;
    public static final int VER_POPULARES = 6;
    public static final int VER_MAS_POPULARES = 7;
    public static final int OBTENER_MAS_LARGA = 8;
    public static final int ELIMINAR = 9;
    public static final int SALIR = 10;

    public static void main(String[] args) {
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        plataforma.cargarPeliculas();
        out.println(NOMBRE_PLATAFORMA+" v"+VERSION);
        out.printf("Más de %d minutos de contenido!!", plataforma.getDuracionTotal());

        plataforma.getContenidoPromocionable().forEach(promocionable -> out.println(promocionable.promocionar()));

        //int contador = 0;

        while(true){ // 4 comillas para escribir varias líneas de string
            int opcionElegida = ScannerUtils.capturarEntero("""
                    Por favor ingrese el número de la opción que desea escoger:
                        1. Agregar película
                        2. Mostrar todo
                        3. Buscar por título
                        4. Buscar por género
                        5. Filtrar por tipo
                        6. Ver populares
                        7. Con calificación mayor a 4
                        8. Buscar película más larga
                        9. Eliminar película
                        10. Salir de la plataforma
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

                    try{
                        if(tipoDeContenido == 1){
                            Pelicula pelicula = new Pelicula(titulo, descripcion, duracion, genero);
                            plataforma.agregarContenido(pelicula);
                        } else{
                            String narrador = ScannerUtils.capturarTexto("Por favor escriba el narrador del contenido");
                            Documental documental = new Documental(titulo, descripcion, duracion, genero,narrador);
                            plataforma.agregarContenido(documental);
                        }
                    }catch (PeliculaExistenteException e){
                        out.println(e.getMessage());
                    }

                }

                case MOSTRAR_TODO -> plataforma.contenidoResumido().forEach(resumenes -> out.println(resumenes.getResumen()));

                case BUSCAR_POR_TITULO -> {
                    String titulo = ScannerUtils.capturarTexto("Por favor ingrese el nombre de la película a buscar");

                    Contenido contenido = plataforma.buscarPorTitulo(titulo); //Buscar por titulo va a retorna un objeto ya sea de  tipo Pelicula o Documental que son hijas de Contenido...
                    if(contenido != null){
                        while(true){
                            out.printf("¿Qué desea hacer con el contenido %s?%n", contenido.getTitulo());
                            int opcion = ScannerUtils.capturarEntero("""
                            1. Ver contenido
                            2. Pausar contenido
                            3. Ver ficha técnica
                            4. Calificar contenido
                            5. Establecer idiomas
                            6. Ver idiomas del contenido
                            """); // no sé si falta salir de búsqueda xd

                            switch (opcion){
                                case 1 -> out.println(plataforma.reproducir(contenido)); //... Por eso al ejecutar el método abstracto reproducir() puede recibir tanto Pelicula como Documental
                                case 2 -> out.println(contenido.pausar());
                                case 3 -> out.println(contenido.obtenerFichaTecnica());
                                case 4 -> {
                                        try{
                                            intentarCalificar(contenido); //método que me valida que la calificación sea de 0 a 5 únicamente
                                        }catch (ContenidoYaCalificadoException e) {
                                            out.println("Error: " + e.getMessage());
                                            int decision;
                                            do {
                                                decision = ScannerUtils.capturarEntero("""
                                                        ¿Deseas cambiar la calificación del contenido elegido?
                                                        1. SI
                                                        2. NO
                                                        """);
                                                switch (decision) {
                                                    case 2 -> {
                                                        break;
                                                    }
                                                    case 1 -> {
                                                        contenido.setCalificacion(0);
                                                        intentarCalificar(contenido);
                                                    }
                                                    default -> out.println("ingrese una opción válida");
                                                }
                                            } while (decision != 1 && decision != 2);
                                        }
                                }
                                case 5 -> {
                                    out.println("Idiomas soportados en la plataforma:");
                                    plataforma.getIdiomas().forEach(s -> out.printf("Idioma: %s%n",s));
                                    List<Idioma> idiomasLista = new ArrayList<>();

                                    while (true){
                                        Idioma idiomaNuevo = ScannerUtils.capturarEnum("Por favor inrese el neuvo idioma del contenido", Idioma.class);
                                        idiomasLista.add(idiomaNuevo);

                                        int eleccion = ScannerUtils.capturarEntero("""
                                            Desea ingresar otro idioma a este contenido?
                                                1. SI
                                                2. NO""");
                                        while (true){
                                            if(eleccion==2 || eleccion == 1){
                                                break;
                                            }else{
                                                out.println("Ingrese una opción válida: ");
                                                eleccion = ScannerUtils.capturarEntero("""
                                                        Desea ingresar otro idioma a este contenido?
                                                            1. SI
                                                            2. NO""");
                                            }
                                        }
                                        if (eleccion == 2){break;}
                                    }
                                    contenido.establecerIdiomas(idiomasLista);
                                }
                                case 6 -> {
                                    out.printf("El contenido %s tiene los siguientes idiomas:%n", contenido.getTitulo());
                                    contenido.obtenerIdiomas().stream().map(Idioma::name).forEach(s -> out.printf("idioma: %s%n", s.toLowerCase()));
                                }
                            }
                            break;
                        }
                    }else{
                        out.println("Oops! Lamentamos decirte que el contenido "+titulo+" a buscar aún no existe en " + NOMBRE_PLATAFORMA);
                    }
                }

                case BUSCAR_POR_GENERO -> {
                    out.printf("Géneros disponibles: %n");
                    plataforma.getGeneros().forEach(genero -> out.printf("Género %-10s%n", genero));

                    Genero genero = ScannerUtils.capturarEnum("Por favor ingrese el género que desea ver",  Genero.class);
                    plataforma.buscarPorGenero(genero).forEach(s -> out.printf("Película %-10s%n", s));
                }
                case FILTRAR_POR_TIPO -> {
                    while (true){
                        int opcion = ScannerUtils.capturarEntero("""
                            Por favor seleccione que tipo de filtro desea poner:
                                1. Películas
                                2. Documentales
                                """);

                        if(opcion==1){
                            out.println("Usted ha seleccionado películas:"+System.lineSeparator());
                            plataforma.getPelis().stream().map(peli ->  peli.getTitulo()+"%n").forEach(out::println);
                            break;
                        }else{
                            out.println("Usted ha seleccionado Documentales:"+System.lineSeparator());
                            plataforma.getDocumentales().stream().map(doc -> doc.getTitulo() + " de " +doc.getNarrador()).forEach(out::println);
                            break;
                        }
                    }

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

    private static void intentarCalificar(Contenido contenido) {
        while(true){
            try{
                contenido.calificar(ScannerUtils.capturarDecimal("Ingrese el valor de la calificación (entre 0 y 5)"));
                out.printf("✅ Calificación para %s: %.1f/5 %n", contenido.getTitulo(), contenido.getCalificacion());
                break;
            }catch(CalificacionException e){
                out.println("Error: "+e.getMessage());
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
