package platzi.play.project.util;

import java.util.Scanner;

public class ScannerUtils {
    public static Scanner scanner = new Scanner(System.in);  // Static nos dice que este atributo no depende de un objeto/instancia sino de la clase en general

    public static String capturarTexto(String mensaje){
        System.out.println(mensaje + ":");
        return scanner.nextLine();
    }

    public static int capturarEntero(String mensaje){
        System.out.println(mensaje + ":");

        int dato = scanner.nextInt();
        scanner.nextLine();
        return  dato;
    }

    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ":");
        double dato = scanner.nextDouble();
        scanner.nextLine();
        return  dato;
    }
}

//la clase anterior permite que al crear un objeto de tipo ScannerUtils que pueda usarse como un input(mensaje)  SIN LOS MÉTODOS ESTÁTICOS
//al hacer que los métodos dean estáticos, NO es necesario crar un objeto de tipo ScannerUtils, sino que llamándo directamente la calse ya podré accader a él
//así: int edad = ScannerUtils.capturarEntero("Ingresa tu edad");
