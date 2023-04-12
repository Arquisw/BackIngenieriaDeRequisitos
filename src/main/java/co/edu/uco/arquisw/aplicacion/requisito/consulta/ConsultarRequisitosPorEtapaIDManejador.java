package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarRequisitosPorEtapaID;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarRequisitosPorEtapaIDManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<List<RequisitoDTO>>> {
    private final ServicioConsultarRequisitosPorEtapaID servicioConsultarRequisitosPorEtapaID;

    public ConsultarRequisitosPorEtapaIDManejador(ServicioConsultarRequisitosPorEtapaID servicioConsultarRequisitosPorEtapaID) {
        this.servicioConsultarRequisitosPorEtapaID = servicioConsultarRequisitosPorEtapaID;
    }

    @Override
    public ComandoRespuesta<List<RequisitoDTO>> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioConsultarRequisitosPorEtapaID.ejecutar(comando));
    }
}