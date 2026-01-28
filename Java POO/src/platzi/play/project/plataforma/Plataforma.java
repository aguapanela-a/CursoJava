package platzi.play.project.plataforma;

import platzi.play.project.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido; // contenido es una lista vacía que guardará objetos de tipo Película

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();  // OPara cualquier atributo de arriba, si no lo incicializo en el contructor, a la hora de crwear una instancia de esta clase, NO se inicializará ese atributo
    }

    public void agregarPeli(Pelicula pelicula){
        this.contenido.add(pelicula);  //métodfo add es similar al .append de python, agrega un elemento a la lista creada previamente
    }

//    public List<String> listarPelis(){
//        int i; //inicializo el entero i
//        List<String> titulos = new ArrayList<>();  //Creo la lista de Strings titulos y la uinciializo como neuvo arraylist
//        //para i = 0 hasta que i < el tamaño de la lista contenido -> sume 1 a i
//        for (i = 0; i < contenido.size(); i++)
//            titulos.add(i + 1 + "- " + contenido.get(i).getTitulo());    //nombre_lista.get(i) es equivalente  a nombre_lista[i] de python
//        return titulos;
//    }

    public List<String> listarPelis(){
        List<String> titulos = new ArrayList<>();
        for(Pelicula pelicula : contenido){
            titulos.add(pelicula.getTitulo());
        }
        return titulos;
    }

    public void eliminarPeli(Pelicula pelicula){
        this.contenido.remove(pelicula);
    }
}
