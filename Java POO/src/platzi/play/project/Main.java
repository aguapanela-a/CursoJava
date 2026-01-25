package platzi.play.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Platzi Play ");

        Scanner scanner = new Scanner(System.in); // esto creará un objeto scanner de tipo Scanner el cual tendrá el parámentro System.in para ingresar datosd esde consola

        System.out.println("Cuál es tu nombre?");

        String nombre = scanner.nextLine(); // Lo que hace es agarrar el objeto scanner de tipo Scanner y pide una entrada en la siguiente linea de ejecución, tomará toda linea, y eso lo guardará en la variable nombre

        System.out.println("Hola " + nombre + " Aguapanela sin panela es agua");
        System.out.printf("Hola %s aguapanela sin panela es awa %n", nombre);  //literalemente lo mismo de arriba, (%s para string, %d para enteros, %n como un \n)

        System.out.printf("Ingresa tu edad %s", nombre);
        int edad = scanner.nextInt(); //La próxima linea debería ser un enero, lo camptura con scanner y lo guarda en edad

        System.out.printf("Entonces %s, tu edad es: %d", nombre, edad);

        if (edad >= 18){
            System.out.printf("%n Puedes ver contenido +18 %s", nombre);
        }else{
            System.out.printf("%n No puedes ver contenido +18");
        }

    }
}
