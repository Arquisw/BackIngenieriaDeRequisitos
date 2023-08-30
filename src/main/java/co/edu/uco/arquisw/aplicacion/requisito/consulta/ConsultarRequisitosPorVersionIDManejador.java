package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarRequisitosPorVersionID;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ConsultarRequisitosPorVersionIDManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<List<RequisitoDTO>>> {
    private final ServicioConsultarRequisitosPorVersionID servicioConsultarRequisitosPorVersionID;

    public ConsultarRequisitosPorVersionIDManejador(ServicioConsultarRequisitosPorVersionID servicioConsultarRequisitosPorVersionID) {
        this.servicioConsultarRequisitosPorVersionID = servicioConsultarRequisitosPorVersionID;
    }

    @Override
    public ComandoRespuesta<List<RequisitoDTO>> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioConsultarRequisitosPorVersionID.ejecutar(comando));
    }
}