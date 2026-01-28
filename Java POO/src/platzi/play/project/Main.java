package platzi.play.project;

import platzi.play.project.contenido.Pelicula;
import platzi.play.project.plataforma.Plataforma;
import platzi.play.project.plataforma.Rol;
import platzi.play.project.plataforma.Usuario;
import platzi.play.project.util.ScannerUtils;
import java.time.format.DateTimeFormatter;


public class Main {
    public static final String VERSION = "1.0.0"; // contants deberían ser mayúsculas, la palabra finales para contantes
    public static final String NOMBRE_PLATAFORMA = "PLATFORMA STREAMING "; // Son ideales para represnetar datos como configuraciones, límites y demás valores que no cambiarán nunca

    public static void main(String[] args) {
        DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA+" v"+VERSION);


        //Utilizxo la calse de utilidad que hice para emular un input()
        String titulo = ScannerUtils.capturarTexto("Ingresa el titulo de la peli");
        String descripcion = ScannerUtils.capturarTexto("Ingresa la descripción de la peli");
        String genero = ScannerUtils.capturarTexto("Ingresa el genero de la peli");
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

        Rol admin = new Rol("Administrador"); // admin se guarda en el stack porque referencia un objeto, pero el objeto en sí se guarda en el heap
        Pelicula pelicula1 = new Pelicula(titulo,descripcion,duracion,genero, true,calificacion);
        Pelicula pelicula2 = new Pelicula("Anulo esas palabras", "culito", 234, "Comedia");
        Pelicula pelicula3 = new Pelicula("Auloas", "o", 34, "Codia");

        Usuario usuario1 = new Usuario("Erikc", "eric@gmail.com", admin);

        plataforma.agregarPeli(pelicula1);
        plataforma.agregarPeli(pelicula2);
        plataforma.agregarPeli(pelicula3);

        plataforma.eliminarPeli(pelicula2);

        System.out.println(plataforma.listarPelis()) ;

        System.out.printf("Señor %s usted se ha registrado  en la fecha %s %n", usuario1.nombre, usuario1.fechaRegistro.format(formatoFechas));
        System.out.println("Y la calificación es: " + pelicula1.getCalificacion());

        System.out.println(usuario1.verRol());


    }
}
//Encapsulameinto: es ocultar o proteger los detalles internos de una clase para que solo se pueda acceder a la info de esa clase de una forma controlada