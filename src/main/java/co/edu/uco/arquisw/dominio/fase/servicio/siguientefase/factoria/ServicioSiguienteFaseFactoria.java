package co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.factoria;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFaseCierre;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFaseEjecucion;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFaseMonitoreoYControl;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFasePlanificacion;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ServicioSiguienteFaseFactoria {
    private final ServicioSiguienteFasePlanificacion servicioSiguienteFasePlanificacion;
    private final ServicioSiguienteFaseEjecucion servicioSiguienteFaseEjecucion;
    private final ServicioSiguienteFaseMonitoreoYControl servicioSiguienteFaseMonitoreoYControl;
    private final ServicioSiguienteFaseCierre servicioSiguienteFaseCierre;

    public Long obtenerEtapaIdDeSiguienteFase(Etapa etapaAnterior, Long etapaID, Long proyectoId, List<RequisitoDTO> requisitosUltimaVersion, List<SeleccionDTO> seleccionesDelProyecto) {
        switch (etapaAnterior.getNombre()) {
            case (TextoConstante.ETAPA_DECLARACION_NOMBRE) -> {
                return this.servicioSiguienteFasePlanificacion.construirSiguienteFase(proyectoId, requisitosUltimaVersion);
            }
            case (TextoConstante.ETAPA_ANALISIS_NOMBRE) -> {
                return this.servicioSiguienteFaseEjecucion.construirSiguienteFase(proyectoId, requisitosUltimaVersion);
            }
            case (TextoConstante.ETAPA_NEGOCIACION_NOMBRE) -> {
                return this.servicioSiguienteFaseMonitoreoYControl.construirSiguienteFase(proyectoId, requisitosUltimaVersion);
            }
            case (TextoConstante.ETAPA_VERIFICACION_NOMBRE) -> {
                return this.servicioSiguienteFaseMonitoreoYControl.construirSiguienteEtapa(etapaAnterior, etapaID, requisitosUltimaVersion);
            }
            case (TextoConstante.ETAPA_VALIDACION_NOMBRE) -> {
                return this.servicioSiguienteFaseCierre.construirFaseCierre(proyectoId, requisitosUltimaVersion, seleccionesDelProyecto);
            }
            default -> {
                return NumeroConstante.CERO;
            }
        }
    }
}