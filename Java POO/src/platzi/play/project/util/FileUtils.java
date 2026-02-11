package platzi.play.project.util;

import platzi.play.project.contenido.Contenido;
import platzi.play.project.contenido.Documental;
import platzi.play.project.contenido.Genero;
import platzi.play.project.contenido.Pelicula;

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


    public static void escribirPelicula(Contenido contenido){
        String dato;
        String narradorDoc = "";

        if(contenido.getClass() == Pelicula.class){
            dato = "PELICULA";

        }else{
            dato = "DOCUMENTAL";
            Documental documental = (Documental) contenido; // Le ordeno al compilador que trate a la variable contenido (que es de tipo general Contenido) como un objeto específico de tipo Documental. Esto se hace para poder acceder a los métodos y atributos exclusivos de la clase Documental que la clase padre no tiene.
            narradorDoc = documental.getNarrador();
        }

        String linea = String.join(SEPARADOR,  //.join lo que hace es que mete el separador entre cada uno de los siquientes strings
                dato,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                String.valueOf(contenido.getGenero()),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString());

        linea = "DOCUMENTAL".equals(dato) ? linea + SEPARADOR + narradorDoc : ""; // significa ¿"DOCUMENTAL" es igual a dato? si: concatene a la linea un separador y el nomrbe del anrrador, si no pues no haga nada

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


    public static List<Contenido> leerPeliculas() {

        List<Contenido> contenidoCargado = new ArrayList<>();

        try{
            List<String> lineas = Files.readAllLines(Paths.get("Java POO/"+NOMBRE_ARCHIVO)); //La clase Files tiene un método readAllLines para leer TODAS las lineas de un archivo como lista de strings que se encuentra en un path/camino/ruta con el nombre
            lineas.forEach(linea ->{
                String[] datos = linea.split("\\"+SEPARADOR); //cada línea la seperará por "|" y guardará cada pedazo en el arreglo datos

                String titulo = datos[1];
                int duracion = Integer.parseInt(datos[2]);
                Genero genero = Genero.valueOf(datos[3]);
                double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]); // si datos en la posición 4 está en blanco retorne 0, si no convierta a double y guarde en la variable+
                LocalDate fechaEstreno = LocalDate.parse(datos[5]);

                if(datos.length ==  6){
                    Pelicula peli = new Pelicula(titulo, " ", duracion, genero, calificacion);
                    peli.setFechaEstreno(fechaEstreno);
                    contenidoCargado.add(peli);

                } else if (datos.length == 7) {
                    String narrador = datos[6];
                    Documental documental = new Documental(titulo,"",duracion, genero,narrador,calificacion);
                    contenidoCargado.add(documental);
                }
            });

        }catch (IOException e){ // este error se atrapará cuando el contenido.txt no existe
            out.println("Ocurrió  un error leyendo el archivo, " + e.getMessage());
        }
        return contenidoCargado;
    }

}

