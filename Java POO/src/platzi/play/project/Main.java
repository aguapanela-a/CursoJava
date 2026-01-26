package platzi.play.project;

import platzi.play.project.contenido.Pelicula;
import platzi.play.project.plataforma.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = "Hansel y grtel";
        pelicula.descripcion = "Hansel y Gretel: Cazadores de brujas (en inglés: Hansel & Gretel: Witch Hunters) es una película alemana-estadounidense de fantasía y acción de 2013, dirigida por Tommy Wirkola, distribuida por Paramount Pictures y basada en el clásico cuento de hadas alemán Hansel y Gretel escrito por los hermanos Grimm. El estreno se tenía previsto en Estados Unidos para el 12 de marzo de 2012, pero luego la fecha fue cambiada para el 11 de enero de 2013.[1]";
        pelicula.genero = "Acción";
        pelicula.fechaEstreno = LocalDate.of(2004, 4, 22);
        pelicula.duracion = 120;


        Usuario usuario1 = new Usuario();

        usuario1.nombre = "Erik";
        usuario1.fechaRegistro = LocalDateTime.of(2024, 6, 4, 17, 43, 4);

        System.out.println(usuario1.ver(pelicula));
        System.out.println(usuario1.pausar(pelicula));

        double calificacion = -1;
        while (calificacion < 0 || calificacion > 5) {
            System.out.printf("Señor usuario, por favor ingrese la calificaión que le da a la película %s del 0 al 5", pelicula.titulo);
            if (entrada.hasNextDouble()) { //Si  lo escrito es un numero devuelve true y sigue
                calificacion = entrada.nextDouble();
                if (calificacion < 0 || calificacion > 5) {
                    System.out.println("Por favor ingrese una calificaión entre 0 y 5");
                }
            } else { //si el has es falso, toca usar entrada.next() para "limpiar" esa palabra errónea de la memoria del Scanner
                System.out.println("Eso no es un número xd");
                entrada.next();
            }
        }
        pelicula.calificar(calificacion);

        long duracionLogn = pelicula.duracion; // casting implícito de entero a long
        int intCalificacion = (int) pelicula.calificacion; // casting explícito de dopuble a entero
        long numeroPremios = Long.parseLong("345"); //de string a long
        // pero si pasamos de un long muy grande a un entero
        int enteroPremios = (int) Long.parseLong("30000000000"); // sucede que al pasar de un numero grande a uno pequeño, trata de buscar su equivalente

        //System.out.println(pelicula.obtenerFichaTecnica());
        System.out.printf("Duración de la peli: "+ duracionLogn + "%n");
        System.out.println("Calificaión de la peli entero: " + intCalificacion +"/5");
        System.out.printf("numero de premios %d %n", numeroPremios);
        System.out.printf("numero de premios entero %d %n", enteroPremios);


        System.out.printf("Señor %s usted se ha registrado  en la fecha %s", usuario1.nombre, usuario1.fechaRegistro.format(formatoFechas));


    }
}
