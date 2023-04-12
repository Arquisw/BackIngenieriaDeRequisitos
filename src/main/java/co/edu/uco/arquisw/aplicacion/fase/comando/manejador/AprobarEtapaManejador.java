package co.edu.uco.arquisw.aplicacion.fase.comando.manejador;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioAprobarEtapa;
import org.springframework.stereotype.Component;

@Component
public class AprobarEtapaManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {
    private final ServicioAprobarEtapa servicioAprobarEtapa;

    public AprobarEtapaManejador(ServicioAprobarEtapa servicioAprobarEtapa) {
        this.servicioAprobarEtapa = servicioAprobarEtapa;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioAprobarEtapa.ejecutar(comando));
    }
}