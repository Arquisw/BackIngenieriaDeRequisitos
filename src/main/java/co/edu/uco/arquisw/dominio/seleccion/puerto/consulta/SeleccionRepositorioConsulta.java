package co.edu.uco.arquisw.dominio.seleccion.puerto.consulta;

import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;

import java.util.List;

public interface SeleccionRepositorioConsulta {
    List<SeleccionDTO> consultarSeleccionadosPorProyecto(Long proyectoID);
}