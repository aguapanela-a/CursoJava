package platzi.play.project.DAO;

import platzi.play.project.contenido.Contenido;
import platzi.play.project.util.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
    public void actualizarContenido(Contenido contenidoEditado) {
        List<Contenido> contenidos = cargarContenido();
        boolean encontrado = false;

        for (int i = 0; i<contenidos.size();i++) {
            //TODO: cambiar por getID cuando implemente el ID
            if(contenidos.get(i).getTitulo().equalsIgnoreCase(contenidoEditado.getTitulo())) {
                contenidos.set(i, contenidoEditado); //la lista contenidos en la posición i será reemplazado por el objeto contenidoEditado
                encontrado = true;
                break;
            }
        }
        if(encontrado){
            sobreescribirArchivoTxt(contenidos);
        }
    }

    private void sobreescribirArchivoTxt(List<Contenido> contenidos) {

        List<String> contenidosTexto = new ArrayList<>();

        contenidos.forEach(contenido -> {
            contenidosTexto.add(FileUtils.crearLineaDefinitiva(contenido));
        });

        try{
            Files.write(
                    Paths.get("Java POO/" + FileUtils.NOMBRE_ARCHIVO),
                    contenidosTexto,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        }catch(IOException e){
            System.out.println("Error al actualizar el archivo" +  e.getMessage());
        }
    }
}

//Toca que Plataforma cree un objeto dao de tipo ContenidoDAO, inicializarlo en el constructor y ejecutar cada método según lo que se quiera
