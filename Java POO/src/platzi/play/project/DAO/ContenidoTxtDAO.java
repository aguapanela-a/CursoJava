package platzi.play.project.DAO;

import platzi.play.project.contenido.Contenido;
import platzi.play.project.util.FileUtils;

import java.util.List;

public class ContenidoTxtDAO implements ContenidoDAO {

    @Override
    public List<Contenido> cargarContenido() {
        return FileUtils.leerContenido();  // Pasar la logíca de FilesUtils aquí y borrar esa clase
    }

    @Override
    public void agregarContenido(Contenido contenido) {
        FileUtils.escribirContenido(contenido);
    }

    @Override
    public void eliminarContenido(Contenido contenido) {
    }

    @Override
    public void actualizarContenido(Contenido contenido) {
    }
}

//Toca que Plataforma cree un objeto dao de tipo ContenidoDAO, inicializarlo en el constructor y ejecutar cada métodos egún lo que se quiera
