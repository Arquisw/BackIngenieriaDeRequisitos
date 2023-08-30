package co.edu.uco.arquisw.aplicacion.requisito.comando.manejador;

import co.edu.uco.arquisw.aplicacion.transversal.ComandoRespuesta;
import co.edu.uco.arquisw.aplicacion.transversal.manejador.ManejadorComandoRespuesta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarVersionInicial;
import org.springframework.stereotype.Component;

@Component
public class GuardarVersionInicialManejador implements ManejadorComandoRespuesta<Long, ComandoRespuesta<Long>> {
    private final ServicioGuardarVersionInicial servicioGuardarVersionInicial;

    public GuardarVersionInicialManejador(ServicioGuardarVersionInicial servicioGuardarVersionInicial) {
        this.servicioGuardarVersionInicial = servicioGuardarVersionInicial;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(Long comando) {
        return new ComandoRespuesta<>(this.servicioGuardarVersionInicial.ejecutar(comando));
    }
}