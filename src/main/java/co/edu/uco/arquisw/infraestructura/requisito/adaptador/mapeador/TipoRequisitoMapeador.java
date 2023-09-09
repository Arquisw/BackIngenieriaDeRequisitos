package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.dto.TipoRequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoTipoRequisitoEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.TipoRequisitoEntidad;
import org.springframework.stereotype.Component;

@Component
public class TipoRequisitoMapeador {
    public RequisitoTipoRequisitoEntidad construirEntidad(TipoRequisito tipoRequisito) {
        return new RequisitoTipoRequisitoEntidad(0L, new TipoRequisitoEntidad(obtenerTipoRequisitoID(tipoRequisito.getNombre()), tipoRequisito.getNombre()));
    }

    public TipoRequisitoDTO construirDTO(RequisitoTipoRequisitoEntidad tipoRequisito) {
        return new TipoRequisitoDTO(tipoRequisito.getTipoRequisito().getNombre());
    }

    private Long obtenerTipoRequisitoID(String nombre) {
        return switch (nombre) {
            case TextoConstante.TIPO_REQUISITO_FUNCIONAL -> 1L;
            case TextoConstante.TIPO_REQUISITO_NO_FUNCIONAL -> 2L;
            default -> 0L;
        };
    }
}