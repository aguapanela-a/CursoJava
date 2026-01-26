package platzi.play.project;

import platzi.play.project.contenido.Pelicula;
import platzi.play.project.plataforma.Usuario;
import platzi.play.project.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Scanner entrada = new Scanner(System.in);
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");

        //Utilizxo la calse de utilidad que hice para emular un input()
        String titulo = ScannerUtils.capturarTexto("Ingresa el titulo de la peli");
        String descripcion = ScannerUtils.capturarTexto("Ingresa la descripción de la peli");
        String genero = ScannerUtils.capturarTexto("Ingresa el generode la peli");
        int duracion = ScannerUtils.capturarEntero("Ingresa la duración de la peli");

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = titulo;
        pelicula.descripcion = descripcion;
        pelicula.genero = genero;
        pelicula.fechaEstreno = LocalDate.of(2004, 4, 22);
        pelicula.duracion = duracion;


        Usuario usuario1 = new Usuario();

        usuario1.nombre = "Erik";
        usuario1.fechaRegistro = LocalDateTime.of(2024, 6, 4, 17, 43, 4);

        System.out.println(usuario1.ver(pelicula));
        System.out.println(usuario1.pausar(pelicula));

        double calificacion = -1;
        while (calificacion < 0 || calificacion > 5) {
            System.out.printf("Por favor ingrese la calificaión de la película %s del 0 al 5", pelicula.titulo);
            if (ScannerUtils.scanner.hasNextDouble()) { //Si  lo escrito es un numero devuelve true y sigue
                calificacion = ScannerUtils.capturarDecimal("");

                if (calificacion < 0 || calificacion > 5) {
                    System.out.println("Por favor ingrese una calificaión entre 0 y 5");
                }
            } else { //si el has es falso, toca usar entrada.next() para "limpiar" esa palabra errónea de la memoria del Scanner
                System.out.println("Eso no es un número xd");
                ScannerUtils.scanner.next();
            }
        }
        pelicula.calificar(calificacion);



        System.out.printf("Señor %s usted se ha registrado  en la fecha %s", usuario1.nombre, usuario1.fechaRegistro.format(formatoFechas));


    }
}
