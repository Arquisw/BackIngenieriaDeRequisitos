package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarRequisitosPorVersionID;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarRequisitosPorVersionIDManejador implements ManejadorComandoRespuesta<Long, List<RequisitoDTO>> {
    private final ServicioConsultarRequisitosPorVersionID servicioConsultarRequisitosPorVersionID;

    public ConsultarRequisitosPorVersionIDManejador(ServicioConsultarRequisitosPorVersionID servicioConsultarRequisitosPorVersionID) {
        this.servicioConsultarRequisitosPorVersionID = servicioConsultarRequisitosPorVersionID;
    }

    @Override
    public List<RequisitoDTO> ejecutar(Long comando) {
        return this.servicioConsultarRequisitosPorVersionID.ejecutar(comando);
    }
}