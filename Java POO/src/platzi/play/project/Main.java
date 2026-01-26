package platzi.play.project;

import platzi.play.project.contenido.Pelicula;
import platzi.play.project.plataforma.Usuario;
import platzi.play.project.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static final String VERSION = "1.0.0"; // contants deberían ser mayúsculas, la palabra finales para contantes
    public static final String NOMBRE_PLATAFORMA = "PLATFORMA STREAMING "; // Son ideales para represnetar datos como configuraciones, límites y demás valores que no cambiarán nunca

    public static void main(String[] args) {
        System.out.println(NOMBRE_PLATAFORMA+" v"+VERSION);

        //Scanner entrada = new Scanner(System.in);
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");

        //Utilizxo la calse de utilidad que hice para emular un input()
        String titulo = ScannerUtils.capturarTexto("Ingresa el titulo de la peli");
        String descripcion = ScannerUtils.capturarTexto("Ingresa la descripción de la peli");
        String genero = ScannerUtils.capturarTexto("Ingresa el generode la peli");
        int duracion = ScannerUtils.capturarEntero("Ingresa la duración de la peli");

        double calificacion = -1;
        while (calificacion < 0 || calificacion > 5) {
            System.out.printf("Por favor ingrese la calificaión de la película %s del 0 al 5", titulo);
            if (ScannerUtils.SCANNER.hasNextDouble()) { //Si  lo escrito es un numero devuelve true y sigue
                calificacion = ScannerUtils.capturarDecimal("");

                if (calificacion < 0 || calificacion > 5) {
                    System.out.println("Por favor ingrese una calificaión entre 0 y 5");
                }
            } else { //si el has es falso, toca usar entrada.next() para "limpiar" esa palabra errónea de la memoria del Scanner
                System.out.println("Eso no es un número xd");
                ScannerUtils.SCANNER.next();
            }
        }

        Pelicula pelicula = new Pelicula(titulo,descripcion,duracion,genero, true,calificacion);



        Usuario usuario1 = new Usuario("Erikc", "eric@gmail.com");


        System.out.println(usuario1.ver(pelicula));
        System.out.println(usuario1.pausar(pelicula));




        System.out.printf("Señor %s usted se ha registrado  en la fecha %s", usuario1.nombre, usuario1.fechaRegistro.format(formatoFechas));
        System.out.println("%nY la calificación es: " + pelicula.calificacion);

    }
}
