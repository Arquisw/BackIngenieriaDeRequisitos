package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarVersionPorID;
import org.springframework.stereotype.Component;

@Component
public class ConsultarVersionPorIDManejador implements ManejadorComandoRespuesta<Long, VersionDTO> {
    private final ServicioConsultarVersionPorID servicioConsultarVersionPorID;

    public ConsultarVersionPorIDManejador(ServicioConsultarVersionPorID servicioConsultarVersionPorID) {
        this.servicioConsultarVersionPorID = servicioConsultarVersionPorID;
    }

    @Override
    public VersionDTO ejecutar(Long comando) {
        return this.servicioConsultarVersionPorID.ejecutar(comando);
    }
}