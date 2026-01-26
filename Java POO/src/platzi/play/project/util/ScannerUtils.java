package platzi.play.project.util;

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

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return  dato;
    }

    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ":");
        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return  dato;
    }
}

//la clase anterior permite que al crear un objeto de tipo ScannerUtils que pueda usarse como un input(mensaje)  SIN LOS MÉTODOS ESTÁTICOS
//al hacer que los métodos dean estáticos, NO es necesario crar un objeto de tipo ScannerUtils, sino que llamándo directamente la calse ya podré accader a él
//así: int edad = ScannerUtils.capturarEntero("Ingresa tu edad");
//utiles para llevar ocnteos o validaciones o clases utilitarias como esta