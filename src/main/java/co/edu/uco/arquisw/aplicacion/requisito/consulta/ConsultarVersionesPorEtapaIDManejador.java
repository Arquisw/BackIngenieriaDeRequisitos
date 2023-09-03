package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarVersionesPorEtapaID;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarVersionesPorEtapaIDManejador implements ManejadorComandoRespuesta<Long, List<VersionDTO>> {
    private final ServicioConsultarVersionesPorEtapaID servicioConsultarVersionesPorEtapaID;

    public ConsultarVersionesPorEtapaIDManejador(ServicioConsultarVersionesPorEtapaID servicioConsultarVersionesPorEtapaID) {
        this.servicioConsultarVersionesPorEtapaID = servicioConsultarVersionesPorEtapaID;
    }

    @Override
    public List<VersionDTO> ejecutar(Long comando) {
        return this.servicioConsultarVersionesPorEtapaID.ejecutar(comando);
    }
}