package platzi.play.project.plataforma;

import platzi.play.project.contenido.Pelicula;

import java.util.ArrayList;
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
//        List<String> titulos = new ArrayList<>();  //Creo la lista de Strings titulos y la uinciializo como neuvo arraylist
//        //para i = 0 hasta que i < el tamaño de la lista contenido -> sume 1 a i
//        for (i = 0; i < contenido.size(); i++)
//            titulos.add(i + 1 + "- " + contenido.get(i).getTitulo());    //nombre_lista.get(i) es equivalente  a nombre_lista[i] de python
//        return titulos;
//    }

    public List<String> listarPelis(){
        List<String> titulos = new ArrayList<>();
        contenido.forEach(pelicula -> titulos.add(pelicula.getIdPeli()+ ". " + pelicula.getTitulo()));
        return titulos;
    }

    public Pelicula buscarPorTitulo(String titulo){
        for(Pelicula pelicula : contenido){
            if(pelicula.getTitulo().equalsIgnoreCase(titulo)){ //compara el título ingresado ignorando mayúsculas y minúsculas
                return pelicula;
            }
        }
        return null;
    }

    public boolean eliminarPeliPorId(int idPelicula){
        // de la lista contenido eliminar el objeto de tipo película si el id es igual al dado
        return contenido.removeIf(peli -> peli.getIdPeli() == idPelicula);
    }
}
