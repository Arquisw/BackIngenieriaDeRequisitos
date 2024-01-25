package co.edu.uco.arquisw.aplicacion.requisito.consulta;

import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioTerminoProcesoIngenieriaRequisitosPorProyectoID;
import org.springframework.stereotype.Component;

@Component
public class TerminoProcesoIngenieriaRequisitosPorProyectoIDManejador implements ManejadorComandoRespuesta<Long, Boolean> {
    private final ServicioTerminoProcesoIngenieriaRequisitosPorProyectoID servicioTerminoProcesoIngenieriaRequisitosPorProyectoID;

    public TerminoProcesoIngenieriaRequisitosPorProyectoIDManejador(ServicioTerminoProcesoIngenieriaRequisitosPorProyectoID servicioTerminoProcesoIngenieriaRequisitosPorProyectoID) {
        this.servicioTerminoProcesoIngenieriaRequisitosPorProyectoID = servicioTerminoProcesoIngenieriaRequisitosPorProyectoID;
    }

    @Override
    public Boolean ejecutar(Long comando) {
        return this.servicioTerminoProcesoIngenieriaRequisitosPorProyectoID.ejecutar(comando);
    }
}