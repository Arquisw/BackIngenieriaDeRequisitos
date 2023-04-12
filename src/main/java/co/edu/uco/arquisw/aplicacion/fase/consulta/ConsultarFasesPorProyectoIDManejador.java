package co.edu.uco.arquisw.aplicacion.fase.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasesPorProyectoID;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ConsultarFasesPorProyectoIDManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<List<FaseDTO>>> {
    private final ServicioConsultarFasesPorProyectoID servicioConsultarFasesPorProyectoID;

    public ConsultarFasesPorProyectoIDManejador(ServicioConsultarFasesPorProyectoID servicioConsultarFasesPorProyectoID) {
        this.servicioConsultarFasesPorProyectoID = servicioConsultarFasesPorProyectoID;
    }

    @Override
    public ComandoRespuesta<List<FaseDTO>> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioConsultarFasesPorProyectoID.ejecutar(comando));
    }
}