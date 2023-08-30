package co.edu.uco.arquisw.aplicacion.fase.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarEtapaPorID;
import org.springframework.stereotype.Component;

@Component
public class ConsultarEtapaPorIDManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<EtapaDTO>> {
    private final ServicioConsultarEtapaPorID servicioConsultarEtapaPorID;

    public ConsultarEtapaPorIDManejador(ServicioConsultarEtapaPorID servicioConsultarEtapaPorID) {
        this.servicioConsultarEtapaPorID = servicioConsultarEtapaPorID;
    }

    @Override
    public ComandoRespuesta<EtapaDTO> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioConsultarEtapaPorID.ejecutar(comando));
    }
}