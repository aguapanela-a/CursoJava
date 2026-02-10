package platzi.play.project.util;

import platzi.play.project.contenido.Genero;
import platzi.play.project.contenido.Pelicula;
import platzi.play.project.plataforma.Plataforma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class FileUtils {

    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String  SEPARADOR = "|";


    public static void escribirPelicula(Pelicula pelicula){
        String linea = String.join(SEPARADOR,  //.join lo que hace es que mete el separador entre cada uno se los siquientes strings
                "PELICULA"
                ,pelicula.getTitulo(),
                String.valueOf( pelicula.getDuracion()),
                String.valueOf( pelicula.getGenero()),
                String.valueOf( pelicula.getCalificacion()),
                pelicula.getFechaEstreno().toString());

        try{
            Files.writeString(                     //Llama la clase Files y el método .writeString(), pues se quiere escribir un string en el archivo,  que recibe
                    Paths.get("Java POO/" + NOMBRE_ARCHIVO),              // una dirección con Paths.get(/aqui/si),
                    linea + System.lineSeparator(),                            //luego la linea que va a ingresaer concatenado de un salto de linea System.lineSeparator()
                    StandardOpenOption.CREATE,                                 //luego las opciones StandardOpenOption puese ser .CREATE par que cree ela rchivo si este no existe
                    StandardOpenOption.APPEND );                               //y .APPEND para que si ya existe el archivo pues agrege al final la lueva linea
        }catch (IOException e){
            out.println("Ha ocurrido un error al escribir el archivo, intente nuevamente. " + e.getMessage());
        }
    }

    public static List<Pelicula> leerPeliculas() {

        List<Pelicula> peliculasCargadas = new ArrayList<>();

        try{
            List<String> lineas = Files.readAllLines(Paths.get("Java POO/"+NOMBRE_ARCHIVO)); //La clase Files tiene un método readAllLines para leer TODAS las lineas de un archivo como lista de strings que se encuentra en un path/camino/ruta con el nombre
            lineas.forEach(linea ->{
                String[] datos = linea.split("\\"+SEPARADOR); //cada línea la seperará por "|" y guardará cada pedazo en el arreglo datos
                if(datos.length ==  6){
                    String titulo = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    Genero genero = Genero.valueOf(datos[3]);
                    double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]); // si datos en la posición 3 está en blanco retorne 0, si no convierta a double y guarde en la variable+
                    LocalDate fechaEstreno = LocalDate.parse(datos[5]);

                    Pelicula pelicula = new Pelicula(titulo, " ", duracion, genero, true, calificacion);
                    pelicula.setFechaEstreno(fechaEstreno);

                    peliculasCargadas.add(pelicula);
                }
            });

        }catch (IOException e){ // este error se atrapará cuando el contenido.txt no existe
            out.println("Ocurrió  un error leyendo el archivo, " + e.getMessage());
        }
        return peliculasCargadas;
    }
}

