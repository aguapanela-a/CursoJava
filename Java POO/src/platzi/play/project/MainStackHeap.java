package platzi.play.project;

import platzi.play.project.contenido.Pelicula;

public class MainStackHeap {
    public static void main(String[] args) {
        Pelicula reyLeon = new Pelicula("Rey León", "describción rey león", 234, "Aventura");
        Pelicula harryPottter = new Pelicula("Harry Potter", "descripción ahrry", 234, "Terror");

        // ahora voy a hacer que reyLeon deje de apuntar al objeto Pelicula del Rey León y ahora punte al mismo de harri pote
        reyLeon = harryPottter;

        reyLeon.titulo = "Ahora ambos tendrán este título";

        System.out.println("Título del objeto al que apunta reyLeon: " + reyLeon.titulo);
        System.out.println("Título del objeto al que apunta harryPottter: " + harryPottter.titulo);

        //Lo que sucede es que ahora ambas variables apuntarán al mismo objeto, entonces al modificar ese objeto
        //desde cualquiera de esas variables, pues se modificará ese objeto, y el objeto al que antes apuntaba reyLeon
        //será eliminado por el garbageCollector en segundo plano, pues no se tiene un acceso a él porque nadie lo apunta/referencia
    }
}