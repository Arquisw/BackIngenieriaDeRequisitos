package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.dto.TipoRequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitoTipoRequisitoEntidad;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.TipoRequisitoEntidad;
import org.springframework.stereotype.Component;

@Component
public class TipoRequisitoMapeador {
    public RequisitoTipoRequisitoEntidad construirEntidad(TipoRequisito tipoRequisito) {
        return new RequisitoTipoRequisitoEntidad(NumeroConstante.CERO, new TipoRequisitoEntidad(obtenerTipoRequisitoID(tipoRequisito.getNombre()), tipoRequisito.getNombre()));
    }

    public TipoRequisitoDTO construirDTO(RequisitoTipoRequisitoEntidad tipoRequisito) {
        return new TipoRequisitoDTO(tipoRequisito.getTipoRequisito().getNombre());
    }

    public void actualizarTipoRequisito(RequisitoTipoRequisitoEntidad entidad, TipoRequisito tipoRequisito) {
        var tipoRequisitoEntidad = new TipoRequisitoEntidad(obtenerTipoRequisitoID(tipoRequisito.getNombre()), tipoRequisito.getNombre());
        entidad.setTipoRequisito(tipoRequisitoEntidad);
    }

    private Long obtenerTipoRequisitoID(String nombre) {
        return switch (nombre) {
            case TextoConstante.TIPO_REQUISITO_FUNCIONAL -> NumeroConstante.UNO;
            case TextoConstante.TIPO_REQUISITO_NO_FUNCIONAL -> NumeroConstante.DOS;
            default -> NumeroConstante.CERO;
        };
    }
}