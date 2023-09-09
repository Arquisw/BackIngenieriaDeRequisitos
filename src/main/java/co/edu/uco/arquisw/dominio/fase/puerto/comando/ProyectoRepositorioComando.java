package co.edu.uco.arquisw.dominio.fase.puerto.comando;

import co.edu.uco.arquisw.dominio.fase.modelo.EstadoProyecto;

public interface ProyectoRepositorioComando {
    void actualizarEstadoProyecto(EstadoProyecto estadoProyecto, Long proyectoID);
}