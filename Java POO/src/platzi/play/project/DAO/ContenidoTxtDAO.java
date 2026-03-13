package platzi.play.project.DAO;

import platzi.play.project.contenido.Contenido;
import platzi.play.project.contenido.Documental;
import platzi.play.project.contenido.Genero;
import platzi.play.project.contenido.Pelicula;
import platzi.play.project.util.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

public class ContenidoTxtDAO implements ContenidoDAO {

    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String ARCHIVO_ID = "metadata.conf";
    public static final String SEPARADOR = "|";

    @Override
    public List<Contenido> cargarContenido() {
        return leerContenido();  // Pasar la logíca de FilesUtils aquí y borrar esa clase
    }

    @Override
    public void agregarContenido(Contenido contenido) {
        contenido.setIdContenido(obtenerSiguienteID());
        escribirContenido(contenido);
    }

    @Override
    public void eliminarContenido(int id) {
        List<Contenido> contenidos = cargarContenido();

        for (int i = 0; i < contenidos.size(); i++) {
            if(contenidos.get(i).getId() == id) {
                contenidos.remove(i);
                break;
            }
        }

        sobreescribirArchivoTxt(contenidos);
    }

    @Override
    public void actualizarContenido(Contenido contenidoEditado) {

        //lista de contenidos sin actualizar
        List<Contenido> contenidos = cargarContenido();
        boolean encontrado = false;

        for (int i = 0; i < contenidos.size(); i++) {
            if (contenidos.get(i).getId() == contenidoEditado.getId()) {
                contenidos.set(i, contenidoEditado);
                encontrado = true;
            }
        }
        if (encontrado) {
            sobreescribirArchivoTxt(contenidos);
        }
    }


    // lógica de bajo nivel //

    private Contenido buscarPorId(int id) {
        List<Contenido> contenidos = cargarContenido();
        for (Contenido contenido : contenidos) {
            if (contenido.getId() == id) {
                return contenido;
            }
        }
        return null;
    }

    private int obtenerSiguienteID() {
        Path rutaArchivo = Paths.get("Java POO/" + ARCHIVO_ID);
        int ultimoID = 0;
        try {
            if (Files.exists(rutaArchivo)) { //Si el archivo en la ruta especificada existe:
                String contenido = Files.readString(rutaArchivo).trim(); //Va a guardar en la variable contenido el valor que esté ahí
                ultimoID = Integer.parseInt(contenido); // hace el parseo de string a int para guardar el ultimo ID
            }
            int siguienteID = ultimoID + 1;
            Files.writeString(rutaArchivo, String.valueOf(siguienteID), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            return siguienteID;

        } catch (IOException | NumberFormatException e) {
            out.println("Error al escribir el id del contenido" + e.getMessage());
            return -1;
        }
    }


    private void sobreescribirArchivoTxt(List<Contenido> contenidos) {

        //lista de contenidos vacía
        List<String> contenidosTexto = new ArrayList<>();

        contenidos.forEach(contenido -> contenidosTexto.add(crearLineaDefinitiva(contenido)));

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
                String.valueOf(contenido.getId()),
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

                int id = Integer.parseInt(datos[1]);
                String titulo = datos[2];
                int duracion = Integer.parseInt(datos[3]);
                Genero genero = Genero.valueOf(datos[4]);
                double calificacion = datos[5].isBlank() ? 0 : Double.parseDouble(datos[5]); // si datos en la posición 4 está en blanco retorne 0, si no convierta a double y guarde en la variable+
                LocalDate fechaEstreno = LocalDate.parse(datos[6]);


                if (datos.length == 7 && Objects.equals(datos[0], "PELICULA")) {   //Objects.equals(a,b)  compara contenido NO dirección en memoria, y Evita que el programa falle si alguna de las variables es nula.
                    Contenido peli = new Pelicula(titulo, " ", duracion, genero); //la variable peli de tipo Contenido puede inicializar como tipo Película, eso es polimorfismo
                    peli.setFechaEstreno(fechaEstreno);
                    peli.calificar(calificacion);
                    contenidoCargado.add(peli);
                    peli.setIdContenido(id);

                } else if (datos.length == 8 && Objects.equals(datos[0], "DOCUMENTAL")) {
                    String narrador = datos[7];
                    Documental documental = new Documental(titulo, "", duracion, genero, narrador);
                    documental.setFechaEstreno(fechaEstreno);
                    documental.calificar(calificacion);
                    contenidoCargado.add(documental);
                    documental.setIdContenido(id);
                }
            });

        } catch (IOException e) { // este error se atrapará cuando el contenido.txt no existe
            out.println("Ocurrió  un error leyendo el archivo, " + e.getMessage());
        }
        return contenidoCargado;
    }


}

//Toca que Plataforma cree un objeto dao de tipo ContenidoDAO, inicializarlo en el constructor y ejecutar cada método según lo que se quiera
