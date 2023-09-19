package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitosFinalesDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarRequisitosFinalesPorEtapaID;
import org.springframework.stereotype.Component;

@Component
public class ConsultarRequisitosFinalesPorEtapaIDManejador implements ManejadorComandoRespuesta<Long, RequisitosFinalesDTO> {
    private final ServicioConsultarRequisitosFinalesPorEtapaID servicioConsultarRequisitosFinalesPorEtapaID;

    public ConsultarRequisitosFinalesPorEtapaIDManejador(ServicioConsultarRequisitosFinalesPorEtapaID servicioConsultarRequisitosFinalesPorEtapaID) {
        this.servicioConsultarRequisitosFinalesPorEtapaID = servicioConsultarRequisitosFinalesPorEtapaID;
    }

    @Override
    public RequisitosFinalesDTO ejecutar(Long comando) {
        return this.servicioConsultarRequisitosFinalesPorEtapaID.ejecutar(comando);
    }
}