package platzi.play.project.util;

import platzi.play.project.contenido.Genero;

import java.util.Scanner;

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

    public static Genero capturarGenero(String mensaje){
        while (true){
            String entrada = capturarTexto(mensaje);
            try {
                return Genero.valueOf(entrada.toUpperCase());  //intente retornar el string ingresado convertido a genero con valueOf(String n)
            }catch (IllegalArgumentException e){                //si no existe el género ingresado capturará el error IllegalArgumentException y, en lugar de terminar el programa, imprimirá lo expresado y se repetirá el while
                System.out.println("Dato no aceptado. "+mensaje+":");
            }
        }

    }
}

//la clase anterior permite que al crear un objeto de tipo ScannerUtils que pueda usarse como un input(mensaje)  SIN LOS MÉTODOS ESTÁTICOS
//al hacer que los métodos dean estáticos, NO es necesario crar un objeto de tipo ScannerUtils, sino que llamándo directamente la calse ya podré accader a él
//así: int edad = ScannerUtils.capturarEntero("Ingresa tu edad");
//utiles para llevar conteos o validaciones o clases utilitarias como esta