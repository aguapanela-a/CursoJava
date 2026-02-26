package platzi.play.project.util;

import platzi.play.project.contenido.Genero;

import java.util.Arrays;
import java.util.Scanner;

/**
 * [ScannerUtils]
 * <p>Clase que permitirá entradas desde consola.
 * Al hacer que sus métodos sean estáticos no es necesario crear una isntancia</p>
 */
public class ScannerUtils {

    //será estático pues el objeto SCANNER no cambia durante sus usos nunca
    public static final Scanner SCANNER = new Scanner(System.in);  // Static nos dice que este atributo no depende de un objeto/instancia sino de la clase en general

    public static String capturarTexto(String mensaje){
        System.out.println(mensaje + ":");
        return SCANNER.nextLine();
    }

    public static int capturarEntero(String mensaje){
        System.out.println(mensaje + ":");

        while (!SCANNER.hasNextInt()){
            System.out.printf("Dato no válido, ingrese un entero %n%s:", mensaje);
            SCANNER.next(); //Borra el valor que ingresó el usuario hasta que sea válido
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return  dato;
    }

    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ":");

        while(!SCANNER.hasNextDouble()){
            System.out.printf("Dato no válido, por favor ingrese un décimal%n%s:", mensaje);
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    /**
     *
     * @param mensaje
     * @param enumClase
     * @return
     * @param <T>
     */

    public static <T extends Enum<T>> T capturarEnum(String mensaje, Class<T> enumClase){    // <T extends Enum<T>> T: Define que el método trabajará con un tipo "T" genérico que debe ser obligatoriamente un Enum.
        while(true){
            String entrada = capturarTexto(mensaje);        // captura un string con capturarTexto;
            try{
                return Enum.valueOf(enumClase,entrada.toUpperCase()); // la clase Enum usa valueOf de forma genérica, entonces requiere una clase Enum creada y el string para convertirlo a una cte de esa clase
            }catch (IllegalArgumentException e){
                System.out.println("El dato ingresado no es aceptado, observe los datos aceptados");
                //.getEnumConstants() es lo equivalente de .values() pero el primero  es para referencial a objetos (enumClase) que representan una clase de tipo Enum genérica y el segundo es un método estático que solo se puede llamar desed Enums ya creados, no genéricos
                Arrays.stream(enumClase.getEnumConstants()).map(Enum::name).forEach(System.out::println);  //lama la clase Arrays y el método Stream con las constantes del Enum como parámetro, y luego mapearlas (convertirlas) a un string con cierto formato e imprimirlas
            }

        }
    }
}


//la clase anterior permite usarse como un input(mensaje)
//al hacer que los métodos sean estáticos, NO es necesario crear un objeto de tipo ScannerUtils, sino que llamándo directamente la calse ya podré accader a él
//así: int edad = ScannerUtils.capturarEntero("Ingresa tu edad");
//utiles para llevar conteos o validaciones o clases utilitarias como esta