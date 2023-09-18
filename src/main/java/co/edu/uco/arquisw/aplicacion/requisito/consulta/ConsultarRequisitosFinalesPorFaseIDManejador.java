package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitosFinalesDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarRequisitosFinalesPorFaseID;
import org.springframework.stereotype.Component;

@Component
public class ConsultarRequisitosFinalesPorFaseIDManejador implements ManejadorComandoRespuesta<Long, RequisitosFinalesDTO> {
    private final ServicioConsultarRequisitosFinalesPorFaseID servicioConsultarRequisitosFinalesPorFaseID;

    public ConsultarRequisitosFinalesPorFaseIDManejador(ServicioConsultarRequisitosFinalesPorFaseID servicioConsultarRequisitosFinalesPorFaseID) {
        this.servicioConsultarRequisitosFinalesPorFaseID = servicioConsultarRequisitosFinalesPorFaseID;
    }

    @Override
    public RequisitosFinalesDTO ejecutar(Long comando) {
        return this.servicioConsultarRequisitosFinalesPorFaseID.ejecutar(comando);
    }
}