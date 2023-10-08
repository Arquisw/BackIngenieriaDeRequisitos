package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;

import java.util.List;

public class ServicioConstruirNuevaFase {
    public Fase ejecutar(String nombreFase, String descripcionFase, String nombreEtapa, String descripcionEtapa) {
        return Fase.crear(nombreFase, descripcionFase, construirNuevasEtapas(nombreEtapa, descripcionEtapa));
    }

    private List<Etapa> construirNuevasEtapas(String nombreEtapa, String descripcionEtapa) {
        var nuevaEtapa = Etapa.crear(nombreEtapa, descripcionEtapa, LogicoConstante.ESTADO_ETAPA_POR_DEFECTO);

        return List.of(nuevaEtapa);
    }

    public Fase construirFaseActualizada(Etapa etapaAnterior) {
        return Fase.crear(TextoConstante.FASE_MONITOREO_Y_CONTROL_NOMBRE, TextoConstante.FASE_MONITOREO_Y_CONTROL_DESCRIPCION, construirEtapasActualizadas(etapaAnterior));
    }

    private List<Etapa> construirEtapasActualizadas(Etapa etapaAnterior) {
        var etapaVerificacion = Etapa.crear(etapaAnterior.getNombre(), etapaAnterior.getDescripcion(), etapaAnterior.isCompletada());
        var etapaValidacion = Etapa.crear(TextoConstante.ETAPA_VALIDACION_NOMBRE, TextoConstante.ETAPA_VALIDACION_DESCRIPCION, LogicoConstante.ESTADO_ETAPA_POR_DEFECTO);

        return List.of(etapaVerificacion, etapaValidacion);
    }

    public Fase construirFaseFinal() {
        return Fase.crear(TextoConstante.FASE_CIERRE_NOMBRE, TextoConstante.FASE_CIERRE_DESCRIPCION, construirEtapasFinales());
    }

    private List<Etapa> construirEtapasFinales() {
        var etapaDefinitiva = Etapa.crear(TextoConstante.ETAPA_DEFINITIVA_NOMBRE, TextoConstante.ETAPA_DEFINITIVA_DESCRIPCION, LogicoConstante.ESTADO_ETAPA_COMPLETADA);

        return List.of(etapaDefinitiva);
    }

    public Etapa construirEtapaAprobada(EtapaDTO etapa) {
        return Etapa.crear(etapa.getNombre(), etapa.getDescripcion(), LogicoConstante.ESTADO_ETAPA_COMPLETADA);
    }
}