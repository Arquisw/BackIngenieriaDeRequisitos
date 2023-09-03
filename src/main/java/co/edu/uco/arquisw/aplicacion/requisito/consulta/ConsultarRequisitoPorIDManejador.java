package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarRequisitoPorID;
import org.springframework.stereotype.Component;

@Component
public class ConsultarRequisitoPorIDManejador implements ManejadorComandoRespuesta<Long, RequisitoDTO> {
    private final ServicioConsultarRequisitoPorID servicioConsultarRequisitoPorID;

    public ConsultarRequisitoPorIDManejador(ServicioConsultarRequisitoPorID servicioConsultarRequisitoPorID) {
        this.servicioConsultarRequisitoPorID = servicioConsultarRequisitoPorID;
    }

    @Override
    public RequisitoDTO ejecutar(Long comando) {
        return this.servicioConsultarRequisitoPorID.ejecutar(comando);
    }
}