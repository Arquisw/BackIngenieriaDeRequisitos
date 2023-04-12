package co.edu.uco.arquisw.aplicacion.fase.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasePorID;
import org.springframework.stereotype.Component;

@Component
public class ConsultarFasePorIDManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<FaseDTO>> {
    private final ServicioConsultarFasePorID servicioConsultarFasePorID;

    public ConsultarFasePorIDManejador(ServicioConsultarFasePorID servicioConsultarFasePorID) {
        this.servicioConsultarFasePorID = servicioConsultarFasePorID;
    }

    @Override
    public ComandoRespuesta<FaseDTO> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioConsultarFasePorID.ejecutar(comando));
    }
}