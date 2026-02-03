package platzi.play.project.plataforma;

import platzi.play.project.contenido.Genero;
import platzi.play.project.contenido.Pelicula;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Plataforma {
    private int contadorID = 1;
    private String nombre;
    private List<Pelicula> contenido; // contenido es una lista vacía que guardará objetos de tipo Película  // Agregación porque la lista d ePelículas pueden existir icluso fuera de la platforma
    private List<Usuario> listaUser;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();  // OPara cualquier atributo de arriba, si no lo incicializo en el contructor, a la hora de crwear una instancia de esta clase, NO se inicializará ese atributo
        this.listaUser = new ArrayList<>(); // inicializo la lista de usuarios cuando creo la plataforma
    }

    public void registrarUsuario(String nombreUser, String correoUser,Rol rolUser){
        this.listaUser.add(new Usuario(nombreUser, correoUser, rolUser));
    }

    public void agregarPeli(Pelicula pelicula){
        this.contenido.add(pelicula);  //métodfo add es similar al .append de python, agrega un elemento a la lista creada previamente
        pelicula.setIdPeli(this.contadorID++);
    }

//    public List<String> listarPelis(){
//        int i; //inicializo el entero i
//        List<String> titulos = new ArrayList<>();  //Creo la lista de Strings titulos y la inicializo como neuvo arraylist
//        //para i = 0 hasta que i < el tamaño de la lista contenido -> sume 1 a i
//        for (i = 0; i < contenido.size(); i++)
//            titulos.add(i + 1 + "- " + contenido.get(i).getTitulo());    //nombre_lista.get(i) es equivalente  a nombre_lista[i] de python
//        return titulos;
//    }

    public List<String> listarPelis(){
        return contenido.stream().map(pelicula -> pelicula.getIdPeli() + ". " + pelicula.getTitulo()).toList();
    }

    public int getDuracionTotal(){
        return contenido.stream().mapToInt(Pelicula::getDuracion).sum();  //agarra cada duración de cada Pelicula de contenido y las suma
    }

    public List<String> getPopulares(int limite){
        List<Pelicula> listaPelisPupis =  contenido.stream()                        //En esta lista iniciamos el stream()
                .sorted(Comparator.comparingDouble(Pelicula::getCalificacion)       //aquí usamos.sorted( ¿que acomodaremos? ), agarrará las calificaciones de cada película y ordenará las pelis según eso, comparando las calificaciones
                        .reversed())                                                //y reversed para que los ponga del mayor al menor
                .limit(limite)                                                      //en la nueva lista unicamente se guardarán la cantidad de elementos ordenados que limit() diga
                .toList();                                                          // y toList opara que lo vuelva lista
        return listaPelisPupis.stream().map(pelicula -> pelicula.getTitulo() + ". Calificación: " + pelicula.getCalificacion()).toList();
    }

    public List<String> getMasPopulares(){
        return contenido.stream().filter(pelicula -> pelicula.getCalificacion() >= 4.0).map(pelicula -> pelicula.getTitulo() +". Calificación:" + pelicula.getCalificacion()).toList();
    }

    public String obtenerMasLarga() {
        return contenido.stream()
                // 1. Buscamos directamente el OBJETO película con mayor duración
                .max(Comparator.comparingInt(Pelicula::getDuracion))
                // 2. Transformamos ese objeto encontrado en el String que quieres
                .map(p -> p.getTitulo() + " tiene una duración de " + p.getDuracion())
                // 3. Si la lista está vacía, damos un mensaje de respaldo
                .orElse("No hay películas en la lista");
    }

    public Pelicula buscarPorTitulo(String titulo){
        return contenido.stream().filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null); //tomará de contenido unicamente la primer peli que  la condición y si no existe ese primero retorna null
    }

    public List<String> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(p -> p.getGenero().equals(genero)) // Filtramos por género
                .map(p -> p.getIdPeli() + ". " + p.getTitulo())     // Transformamos la Pelicula en el String requerido
                .toList();                                          // Creamos la lista final directamente
    }

    public boolean eliminarPeliPorId(int idPelicula){
        // de la lista contenido eliminar el objeto de tipo película si el id es igual al dado
        return contenido.removeIf(peli -> peli.getIdPeli() == idPelicula);
    }

    public List<String> getGeneros(){

        return Arrays               //Arrays es una clase de java que tiene métodos estáticos para trabajar con arreglos, en este caso usamos el método stream() para crear un stream a partir del array devuelto por Genero.values()
        .stream(Genero.values())    //values() es un método estático que devuelve un array con todos los valores del enum Genero
        .map(Genero::name)          //map transforma cada valor del enum en su representación en String usando el método name()
        .toList();                  //toList() convierte el stream resultante en una lista de Strings y la devuelve
    }

    public String getNombre() {
        return nombre;
    }
}
