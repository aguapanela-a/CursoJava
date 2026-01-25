package platzi.play.project;

import platzi.play.project.contenido.Pelicula;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Platzi Play ");
//
//        Scanner scanner = new Scanner(System.in); // esto creará un objeto scanner de tipo Scanner el cual tendrá el parámentro System.in para ingresar datosd esde consola
//
//        System.out.println("Cuál es tu nombre?");
//
//        String nombre = scanner.nextLine(); // Lo que hace es agarrar el objeto scanner de tipo Scanner y pide una entrada en la siguiente linea de ejecución, tomará toda linea, y eso lo guardará en la variable nombre
//
//        System.out.println("Hola " + nombre + " Aguapanela sin panela es agua");
//        System.out.printf("Hola %s aguapanela sin panela es awa %n", nombre);  //literalemente lo mismo de arriba, (%s para string, %d para enteros, %n como un \n)
//
//        System.out.printf("Ingresa tu edad %s", nombre);
//        int edad = scanner.nextInt(); //La próxima linea debería ser un enero, lo camptura con scanner y lo guarda en edad
//
//        System.out.printf("Entonces %s, tu edad es: %d", nombre, edad);
//
//        if (edad >= 18){
//            System.out.printf("%n Puedes ver contenido +18 %s", nombre);
//        }else{
//            System.out.printf("%n No puedes ver contenido +18");
//        }

        Scanner entrada = new Scanner(System.in);

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = "Hansel y grtel";
        pelicula.descripcion = "Hansel y Gretel: Cazadores de brujas (en inglés: Hansel & Gretel: Witch Hunters) es una película alemana-estadounidense de fantasía y acción de 2013, dirigida por Tommy Wirkola, distribuida por Paramount Pictures y basada en el clásico cuento de hadas alemán Hansel y Gretel escrito por los hermanos Grimm. El estreno se tenía previsto en Estados Unidos para el 12 de marzo de 2012, pero luego la fecha fue cambiada para el 11 de enero de 2013.[1]";
        pelicula.genero = "Acción";
        pelicula.anioEstreno = 1934;

        double calificacion = -1;
        while (calificacion < 0 || calificacion > 5){
            System.out.printf("Señor usuario, por favor ingrese la calificaión que le da a la película %s del 0 al 5", pelicula.titulo);
            if(entrada.hasNextDouble()){ //Si  lo escrito es un numero devuelve true y sigue
                calificacion = entrada.nextDouble();
                if(calificacion < 0 || calificacion > 5){
                    System.out.println("Por favor ingrese una calificaión entre 0 y 5");
                }
            }else{ //si el has es falso, toca usar entrada.next() para "limpiar" esa palabra errónea de la memoria del Scanner
                System.out.println("Eso no es un número xd");
                entrada.next();
            }
        }
        pelicula.calificar(calificacion);

        System.out.println(pelicula.obtenerFichaTecnica());

    }
}
