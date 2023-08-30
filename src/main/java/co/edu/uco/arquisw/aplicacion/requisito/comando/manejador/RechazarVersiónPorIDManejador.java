package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioRechazarVersiónPorID;
import org.springframework.stereotype.Component;

@Component
public class RechazarVersiónPorIDManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {
    private final ServicioRechazarVersiónPorID servicioRechazarVersiónPorID;

    public RechazarVersiónPorIDManejador(ServicioRechazarVersiónPorID servicioRechazarVersiónPorID) {
        this.servicioRechazarVersiónPorID = servicioRechazarVersiónPorID;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioRechazarVersiónPorID.ejecutar(comando));
    }
}