package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AutorizacionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria.ServicioEnviarNotificacionFactoria;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import lombok.AllArgsConstructor;

import static co.edu.uco.arquisw.dominio.transversal.TipoNotificacion.APROBACION_ETAPA;

@AllArgsConstructor
public class ServicioAprobarEtapa {
    private final FaseRepositorioComando faseRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final ServicioActualizarFase servicioActualizarFase;
    private final ServicioConstruirNuevaFase servicioConstruirNuevaFase;
    private final ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria;

    public Long ejecutar(Long etapaID) {
        validarSiExisteEtapaConID(etapaID);

        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaID);

        validarSiEtapaNoEstaCompletada(etapa);
        validarSiVersionFinalFueGenerada(etapaID);

        var fase = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID);
        var proyectoId = fase.getProyectoID();
        var seleccionesDelProyecto = this.seleccionRepositorioConsulta.consultarSeleccionadosPorProyecto(proyectoId);
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoId);
        var etapaActualizada = this.servicioConstruirNuevaFase.construirEtapaAprobada(etapa);

        this.faseRepositorioComando.actualizarEtapa(etapaActualizada, etapaID);

        var id = this.servicioActualizarFase.ejecutar(etapaActualizada, etapaID, seleccionesDelProyecto);

        this.servicioEnviarNotificacionFactoria.orquestarNotificacion(seleccionesDelProyecto, etapa, fase, proyecto, 0L, TextoConstante.VACIO, APROBACION_ETAPA);

        return id;
    }

    private void validarSiExisteEtapaConID(Long etapaID) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaID))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + etapaID);
        }
    }

    private void validarSiEtapaNoEstaCompletada(EtapaDTO etapa) {
        if (etapa.isCompletada()) {
            throw new AccionExcepcion(Mensajes.ETAPA_CON_ID + etapa.getId() + Mensajes.ETAPA_YA_ESTA_COMPLETADA);
        }
    }

    private void validarSiVersionFinalFueGenerada(Long etapaID) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarUltimaVersionPorEtapaID(etapaID))) {
            throw new AutorizacionExcepcion(Mensajes.NO_SE_HA_GENERADO_UNA_VERSION_FINAL_PARA_LA_ETAPA_ACTUAL);
        }
    }
}