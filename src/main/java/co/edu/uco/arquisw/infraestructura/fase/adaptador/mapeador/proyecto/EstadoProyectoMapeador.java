package co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.proyecto;

import co.edu.uco.arquisw.dominio.fase.dto.proyecto.EstadoProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.proyecto.EstadoProyecto;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.proyecto.EstadoEntidad;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.proyecto.EstadoProyectoEntidad;
import org.springframework.stereotype.Component;

@Component
public class EstadoProyectoMapeador {
    public EstadoProyectoEntidad construirEntidad(EstadoProyecto estado) {
        return new EstadoProyectoEntidad(NumeroConstante.CERO, new EstadoEntidad(obtenerRolID(estado.getNombre()), estado.getNombre()));
    }

    public EstadoProyectoDTO construirDTO(EstadoProyectoEntidad estado) {
        return new EstadoProyectoDTO(estado.getEstado().getNombre());
    }

    private Long obtenerRolID(String nombre) {
        return switch (nombre) {
            case TextoConstante.ESTADO_FINALIZADO -> NumeroConstante.SIETE;
            default -> NumeroConstante.CERO;
        };
    }
}