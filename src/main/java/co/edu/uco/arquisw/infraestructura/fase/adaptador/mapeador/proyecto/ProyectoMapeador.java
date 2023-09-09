package co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.proyecto;

import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.proyecto.ProyectoEntidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProyectoMapeador {
    private final EstadoProyectoMapeador estadoProyectoMapeador;
    private final TipoConsultoriaMapeador tipoConsultoriaMapeador;

    public ProyectoMapeador(EstadoProyectoMapeador estadoProyectoMapeador, TipoConsultoriaMapeador tipoConsultoriaMapeador) {
        this.estadoProyectoMapeador = estadoProyectoMapeador;
        this.tipoConsultoriaMapeador = tipoConsultoriaMapeador;
    }

    public ProyectoDTO construirDTO(ProyectoEntidad proyecto) {
        return new ProyectoDTO(proyecto.getId(), proyecto.getNombre(), proyecto.getDescripcion(), this.estadoProyectoMapeador.construirDTO(proyecto.getEstado()), this.tipoConsultoriaMapeador.construirDTOs(proyecto.getTiposConsultoria()));
    }

    public List<ProyectoDTO> construirDTOs(List<ProyectoEntidad> proyectos) {
        return proyectos.stream().map(new ProyectoMapeador(estadoProyectoMapeador, tipoConsultoriaMapeador)::construirDTO).toList();
    }
}