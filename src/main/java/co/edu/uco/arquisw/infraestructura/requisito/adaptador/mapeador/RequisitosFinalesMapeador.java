package co.edu.uco.arquisw.infraestructura.requisito.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitosFinalesDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.RequisitosFinales;
import co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad.RequisitosFinalesEntidad;
import org.springframework.stereotype.Component;

@Component
public class RequisitosFinalesMapeador {
    public RequisitosFinalesEntidad construir(RequisitosFinales requisitosFinales, Long faseId) {
        return new RequisitosFinalesEntidad(0L, requisitosFinales.getRutaArchivo(), faseId);
    }

    public RequisitosFinalesDTO construirDTO(RequisitosFinalesEntidad requisitosFinales) {
        return new RequisitosFinalesDTO(requisitosFinales.getId(), requisitosFinales.getRutaArchivo());
    }
}
