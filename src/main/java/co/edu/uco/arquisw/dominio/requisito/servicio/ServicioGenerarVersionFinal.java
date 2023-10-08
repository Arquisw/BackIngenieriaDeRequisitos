package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria.ServicioEnviarNotificacionFactoria;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import lombok.AllArgsConstructor;

import static co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.enumerador.TipoNotificacion.VERSION_FINAL_GENERADA;

@AllArgsConstructor
public class ServicioGenerarVersionFinal {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final ServicioObtenerRequisitosEtapaAnterior servicioObtenerRequisitosEtapaAnterior;
    private final ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria;

    public Long ejecutar(Long versionId) {
        validarSiExisteVersionConID(versionId);

        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_FINAL, LogicoConstante.NO_ESTA_RECHAZADA);

        var etapaID = this.requisitoRepositorioConsulta.consultarVersionPorID(versionId).getEtapaID();
        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaID);
        var fase = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID);
        var proyectoId = fase.getProyectoID();

        this.servicioObtenerRequisitosEtapaAnterior.ejecutar(
                etapa,
                versionId,
                proyectoId,
                Mensajes.NO_HAY_REQUISITOS_PARA_LA_VERSION_ACTUAL,
                Mensajes.LA_VERSION_NO_HA_SUFRIDO_NINGUN_CAMBIO_CON_RESPECTO_A_LA_ETAPA_ANTERIOR
        );

        var seleccionesDelProyecto = this.seleccionRepositorioConsulta.consultarSeleccionadosPorProyecto(proyectoId);
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoId);

        var respuestaId = this.requisitoRepositorioComando.actualizarVersion(nuevaVersion, versionId);

        this.servicioEnviarNotificacionFactoria.orquestarNotificacion(seleccionesDelProyecto, etapa, fase, proyecto, versionId, TextoConstante.VACIO, VERSION_FINAL_GENERADA);

        return respuestaId;
    }

    private void validarSiExisteVersionConID(Long versionId) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionId))) {
            throw new NullPointerException(Mensajes.obtenerNoExiteVersionConId(versionId));
        }
    }
}