package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria.ServicioEnviarNotificacionFactoria;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import lombok.AllArgsConstructor;

import static co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.enumerador.TipoNotificacion.VERSION_INICIAL_GUARDADA;

@AllArgsConstructor
public class ServicioGuardarVersionInicial {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria;


    public Long ejecutar(Long etapaId) {
        validarSiExisteEtapaConID(etapaId);

        var version = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, LogicoConstante.NO_ESTA_RECHAZADA);

        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaId);
        var fase = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaId);
        var proyectoId = fase.getProyectoID();
        var seleccionesDelProyecto = this.seleccionRepositorioConsulta.consultarSeleccionadosPorProyecto(proyectoId);
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoId);

        var versionId = this.requisitoRepositorioComando.guardarVersion(version, etapaId);

        this.servicioEnviarNotificacionFactoria.orquestarNotificacion(seleccionesDelProyecto, etapa, fase, proyecto, NumeroConstante.CERO, TextoConstante.VACIO, VERSION_INICIAL_GUARDADA);

        return versionId;
    }

    private void validarSiExisteEtapaConID(Long etapaId) {
        if (ValidarObjeto.esNulo(this.faseRepositorioConsulta.consultarEtapaPorID(etapaId))) {
            throw new NullPointerException(Mensajes.obtenerNoExiteEtapaConId(etapaId));
        }
    }
}