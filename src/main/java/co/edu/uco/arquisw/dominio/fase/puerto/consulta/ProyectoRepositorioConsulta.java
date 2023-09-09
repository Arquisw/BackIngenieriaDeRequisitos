package co.edu.uco.arquisw.dominio.fase.puerto.consulta;

import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;

public interface ProyectoRepositorioConsulta {
    ProyectoDTO consultarProyectoPorID(Long proyectoID);
}