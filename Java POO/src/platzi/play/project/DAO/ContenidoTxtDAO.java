package platzi.play.project.DAO;

import platzi.play.project.contenido.Contenido;
import platzi.play.project.contenido.Documental;
import platzi.play.project.contenido.Genero;
import platzi.play.project.contenido.Pelicula;
import platzi.play.project.util.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

public class ContenidoTxtDAO implements ContenidoDAO {

    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String SEPARADOR = "|";

    @Override
    public List<Contenido> cargarContenido() {
        return leerContenido();  // Pasar la logíca de FilesUtils aquí y borrar esa clase
    }

    @Override
    public void agregarContenido(Contenido contenido) {
        escribirContenido(contenido);
    }

    @Override
    public void eliminarContenido(Contenido contenido) {
    }

    @Override
    public void actualizarContenido(Contenido contenidoEditado) {
        List<Contenido> contenidos = cargarContenido();
        boolean encontrado = false;

        for (int i = 0; i < contenidos.size(); i++) {
            //TODO: cambiar por getID cuando implemente el ID
            if (contenidos.get(i).getTitulo().equalsIgnoreCase(contenidoEditado.getTitulo())) {
                contenidos.set(i, contenidoEditado); //la lista contenidos en la posición i será reemplazado por el objeto contenidoEditado
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            sobreescribirArchivoTxt(contenidos);
        }
    }


    // lógica de bajo nivel //

    private void sobreescribirArchivoTxt(List<Contenido> contenidos) {

        List<String> contenidosTexto = new ArrayList<>();

        contenidos.forEach(contenido -> {
            contenidosTexto.add(crearLineaDefinitiva(contenido));
        });

        try {
            Files.write(
                    Paths.get("Java POO/" + NOMBRE_ARCHIVO),
                    contenidosTexto,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo" + e.getMessage());
        }
    }

    private String crearLineaDefinitiva(Contenido contenido) {
        String linea = String.join(SEPARADOR,  //.join lo que hace es que mete el separador entre cada uno de los siquientes strings
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                String.valueOf(contenido.getGenero()),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString());

        String lineaDefinitiva;

        if (contenido instanceof Pelicula) {  //la condición es exactamente igual a esto: if(contenido.getClass() == Pelicula.class){...
            lineaDefinitiva = "PELICULA" + SEPARADOR + linea;
        } else {
            Documental documental = (Documental) contenido; // Le ordeno al compilador que trate a la variable contenido (que es de tipo general Contenido) como un objeto específico de tipo Documental. Esto se hace para poder acceder a los métodos y atributos exclusivos de la clase Documental que la clase padre no tiene.
            lineaDefinitiva = "DOCUMENTAL" + SEPARADOR + linea + SEPARADOR + documental.getNarrador();
            ;
        }

        return lineaDefinitiva;
    }

    private void escribirContenido(Contenido contenido) {
        String lineaDefinitiva = crearLineaDefinitiva(contenido);

        try {
            Files.writeString(                     //Llama la clase Files y el método .writeString(), pues se quiere escribir un string en el archivo,  que recibe
                    Paths.get("Java POO/" + NOMBRE_ARCHIVO),              // una dirección con Paths.get(/aqui/si),
                    lineaDefinitiva + System.lineSeparator(),                            //luego la linea que va a ingresaer concatenado de un salto de linea System.lineSeparator()
                    StandardOpenOption.CREATE,                                 //luego las opciones StandardOpenOption puese ser .CREATE par que cree ela rchivo si este no existe
                    StandardOpenOption.APPEND);                               //y .APPEND para que si ya existe el archivo pues agrege al final la lueva linea
        } catch (IOException e) {
            out.println("Ha ocurrido un error al escribir el archivo, intente nuevamente. " + e.getMessage());
        }
    }

    private static List<Contenido> leerContenido() {

        List<Contenido> contenidoCargado = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get("Java POO/" + NOMBRE_ARCHIVO)); //La clase Files tiene un método readAllLines para leer TODAS las lineas de un archivo como lista de strings que se encuentra en un path/camino/ruta con el nombre
            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR); //cada línea la seperará por "|" y guardará cada pedazo en el arreglo datos

                String titulo = datos[1];
                int duracion = Integer.parseInt(datos[2]);
                Genero genero = Genero.valueOf(datos[3]);
                double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]); // si datos en la posición 4 está en blanco retorne 0, si no convierta a double y guarde en la variable+
                LocalDate fechaEstreno = LocalDate.parse(datos[5]);


                if (datos.length == 6 && Objects.equals(datos[0], "PELICULA")) {   //Objects.equals(a,b)  compara contenido NO dirección en memoria, y Evita que el programa falle si alguna de las variables es nula.
                    Contenido peli = new Pelicula(titulo, " ", duracion, genero); //la variable peli de tipo Contenido puede inicializar como tipo Película, eso es polimorfismo
                    peli.setFechaEstreno(fechaEstreno);
                    peli.calificar(calificacion);
                    contenidoCargado.add(peli);

                } else if (datos.length == 7 && Objects.equals(datos[0], "DOCUMENTAL")) {
                    String narrador = datos[6];
                    Documental documental = new Documental(titulo, "", duracion, genero, narrador);
                    documental.setFechaEstreno(fechaEstreno);
                    documental.calificar(calificacion);
                    contenidoCargado.add(documental);
                }
            });

        } catch (IOException e) { // este error se atrapará cuando el contenido.txt no existe
            out.println("Ocurrió  un error leyendo el archivo, " + e.getMessage());
        }
        return contenidoCargado;
    }


}

//Toca que Plataforma cree un objeto dao de tipo ContenidoDAO, inicializarlo en el constructor y ejecutar cada método según lo que se quiera
