package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoPaginacion;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioConsultarRequisitosPorVersionID;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarRequisitosPorVersionIDManejador implements ManejadorComandoPaginacion<Long,Integer,Integer, Page<RequisitoDTO>> {
    private final ServicioConsultarRequisitosPorVersionID servicioConsultarRequisitosPorVersionID;

    public ConsultarRequisitosPorVersionIDManejador(ServicioConsultarRequisitosPorVersionID servicioConsultarRequisitosPorVersionID) {
        this.servicioConsultarRequisitosPorVersionID = servicioConsultarRequisitosPorVersionID;
    }

    @Override
    public Page<RequisitoDTO> ejecutar(Long comando, Integer pagina, Integer tamano) {
        return this.servicioConsultarRequisitosPorVersionID.ejecutar(comando, pagina, tamano);
    }
}