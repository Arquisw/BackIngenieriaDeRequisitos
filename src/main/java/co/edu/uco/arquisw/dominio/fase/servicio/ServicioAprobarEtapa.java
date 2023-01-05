package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import java.util.List;

public class ServicioAprobarEtapa {
    private final FaseRepositorioComando faseRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;

    public ServicioAprobarEtapa(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta) {
        this.faseRepositorioComando = faseRepositorioComando;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
    }

    public Long ejecutar(Long etapaID) {
        validarSiExisteEtapaConID(etapaID);

        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaID);

        validarSiEtapaNoEstaCompletada(etapa);

        var etapaActualizada = construirEtapaAprobada(etapa);

        this.faseRepositorioComando.actualizarEtapa(etapaActualizada, etapa.getId());

        return actualizarFase(etapaActualizada, etapaID);
    }

    private void validarSiExisteEtapaConID(Long etapaID) {
        if(ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaID);
        }
    }

    private void validarSiEtapaNoEstaCompletada(EtapaDTO etapa) {
        if(etapa.isCompletada()) {
            throw new AccionExcepcion(Mensajes.ETAPA_CON_ID + etapa.getId() + Mensajes.ETAPA_YA_ESTA_COMPLETADA);
        }
    }

    private Etapa construirEtapaAprobada(EtapaDTO etapa) {
        return Etapa.crear(etapa.getNombre(), etapa.getDescripcion(), LogicoConstante.ESTADO_ETAPA_COMPLETADA);
    }

    private Long actualizarFase(Etapa etapaAnterior, Long etapaID) {
        Long id;
        var proyectoID = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID).getProyectoID();

        switch (etapaAnterior.getNombre()) {
            case (TextoConstante.ETAPA_DECLARACION_NOMBRE) -> {
                var nombreSiguienteFase = TextoConstante.FASE_PLANIFICACION_NOMBRE;
                var descripcionSiguienteFase = TextoConstante.FASE_PLANIFICACION_DESCRIPCION;
                var nombreSiguienteEtapa = TextoConstante.ETAPA_ANALISIS_NOMBRE;
                var descripcionSiguienteEtapa = TextoConstante.ETAPA_ANALISIS_DESCRIPCION;

                var fase = construirNuevaFase(nombreSiguienteFase, descripcionSiguienteFase, nombreSiguienteEtapa, descripcionSiguienteEtapa);

                id = this.faseRepositorioComando.guardar(fase, proyectoID);
            }
            case (TextoConstante.ETAPA_ANALISIS_NOMBRE) -> {
                var nombreSiguienteFase = TextoConstante.FASE_EJECUCION_NOMBRE;
                var descripcionSiguienteFase = TextoConstante.FASE_EJECUCION_DESCRIPCION;
                var nombreSiguienteEtapa = TextoConstante.ETAPA_NEGOCIACION_NOMBRE;
                var descripcionSiguienteEtapa = TextoConstante.ETAPA_NEGOCIACION_DESCRIPCION;
                var fase = construirNuevaFase(nombreSiguienteFase, descripcionSiguienteFase, nombreSiguienteEtapa, descripcionSiguienteEtapa);

                id = this.faseRepositorioComando.guardar(fase, proyectoID);
            }
            case (TextoConstante.ETAPA_NEGOCIACION_NOMBRE) -> {
                var nombreSiguienteFase = TextoConstante.FASE_MONITOREO_Y_CONTROL_NOMBRE;
                var descripcionSiguienteFase = TextoConstante.FASE_MONITOREO_Y_CONTROL_DESCRIPCION;
                var nombreSiguienteEtapa = TextoConstante.ETAPA_VERIFICACION_NOMBRE;
                var descripcionSiguienteEtapa = TextoConstante.ETAPA_VERIFICACION_DESCRIPCION;
                var fase = construirNuevaFase(nombreSiguienteFase, descripcionSiguienteFase, nombreSiguienteEtapa, descripcionSiguienteEtapa);

                id = this.faseRepositorioComando.guardar(fase, proyectoID);
            }
            case (TextoConstante.ETAPA_VERIFICACION_NOMBRE) -> {
                var fase = construirFaseActualizada(etapaAnterior);
                var faseID = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID).getId();

                id = this.faseRepositorioComando.actualizar(fase, faseID);
            }
            case (TextoConstante.ETAPA_VALIDACION_NOMBRE) -> {
                var fase = construirFaseFinal();

                id = this.faseRepositorioComando.guardar(fase, proyectoID);
            }
            default -> id = 0L;
        }

        return id;
    }

    private Fase construirNuevaFase(String nombreFase, String descripcionFase, String nombreEtapa, String descripcionEtapa) {
        return Fase.crear(nombreFase, descripcionFase, construirNuevasEtapas(nombreEtapa, descripcionEtapa));
    }
    private List<Etapa> construirNuevasEtapas(String nombreEtapa, String descripcionEtapa) {
        var nuevaEtapa = Etapa.crear(nombreEtapa, descripcionEtapa, LogicoConstante.ESTADO_ETAPA_POR_DEFECTO);

        return List.of(nuevaEtapa);
    }

    private Fase construirFaseActualizada(Etapa etapaAnterior) {
        return Fase.crear(TextoConstante.FASE_MONITOREO_Y_CONTROL_NOMBRE, TextoConstante.FASE_MONITOREO_Y_CONTROL_DESCRIPCION, construirEtapasActualizadas(etapaAnterior));
    }
    private List<Etapa> construirEtapasActualizadas(Etapa etapaAnterior) {
        var etapaVerificacion = Etapa.crear(etapaAnterior.getNombre(), etapaAnterior.getDescripcion(), etapaAnterior.isCompletada());
        var etapaValidacion = Etapa.crear(TextoConstante.ETAPA_VALIDACION_NOMBRE, TextoConstante.ETAPA_VALIDACION_DESCRIPCION, LogicoConstante.ESTADO_ETAPA_POR_DEFECTO);

        return List.of(etapaVerificacion, etapaValidacion);
    }

    private Fase construirFaseFinal() {
        return Fase.crear(TextoConstante.FASE_CIERRE_NOMBRE, TextoConstante.FASE_CIERRE_DESCRIPCION, construirEtapasFinales());
    }
    private List<Etapa> construirEtapasFinales() {
        var etapaVacia = Etapa.crear(TextoConstante.VACIO, TextoConstante.VACIO, LogicoConstante.ESTADO_ETAPA_POR_DEFECTO);

        return List.of(etapaVacia);
    }
}