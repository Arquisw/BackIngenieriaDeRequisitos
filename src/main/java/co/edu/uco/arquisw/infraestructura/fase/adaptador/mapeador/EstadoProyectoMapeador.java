package co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.fase.dto.EstadoProyectoDTO;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.EstadoProyectoEntidad;
import org.springframework.stereotype.Component;

@Component
public class EstadoProyectoMapeador {
    public EstadoProyectoDTO construirDTO(EstadoProyectoEntidad estado) {
        return new EstadoProyectoDTO(estado.getEstado().getNombre());
    }
}