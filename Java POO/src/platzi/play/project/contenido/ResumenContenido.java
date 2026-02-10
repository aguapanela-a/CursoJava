package platzi.play.project.contenido;

public record ResumenContenido(        // clase inmutable
        String titulo,
        int duracion,
        Genero genero,
        int idPeli) {

    public String getResumen(){
        //StringBuilder sb = new StringBuilder(); // sb es un objeto de tipo StringBuilder para hacermesto: sb.append("nombre: ").append(titulo)...
        return "Título: "+ titulo + "\n"+
                "Duración: " + duracion + "\n"+
                "Género: " + genero.name().toLowerCase() + "\n"+
                "ID: " + idPeli + "\n";
    }
}
