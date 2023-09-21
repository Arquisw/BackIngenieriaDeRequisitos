package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.MotivoRechazoVersion;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AutorizacionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;

import javax.mail.MessagingException;
import java.util.List;

public class ServicioRechazarVersionPorID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final PersonaRepositorioConsulta personaRepositorioConsulta;
    private final ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico;

    public ServicioRechazarVersionPorID(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, PersonaRepositorioConsulta personaRepositorioConsulta, ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
        this.seleccionRepositorioConsulta = seleccionRepositorioConsulta;
        this.proyectoRepositorioConsulta = proyectoRepositorioConsulta;
        this.personaRepositorioConsulta = personaRepositorioConsulta;
        this.servicioEnviarCorreoElectronico = servicioEnviarCorreoElectronico;
    }

    public Long ejecutar(MotivoRechazoVersion motivoRechazoVersion, Long versionId) {
        validarSiExisteVersionConID(versionId);

        var versionDTO = this.requisitoRepositorioConsulta.consultarVersionPorID(versionId);
        var etapaID = versionDTO.getEtapaID();
        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaID);
        var fase = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID);
        var proyectoId = fase.getProyectoID();

        obtenerRequisitosEtapaAnteriorParaValidarSiRequisitosSonIgualesALosDeLaEtapaAnterior(etapa, versionId, proyectoId);

        var seleccionesDelProyecto = this.seleccionRepositorioConsulta.consultarSeleccionadosPorProyecto(proyectoId);
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoId);

        this.requisitoRepositorioComando.guardarMotivoRechazoVersion(motivoRechazoVersion, versionId);
        this.requisitoRepositorioComando.actualizarVersion(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, LogicoConstante.ESTA_RECHAZADA, versionId);

        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, LogicoConstante.NO_ESTA_RECHAZADA);

        var respuestaId = this.requisitoRepositorioComando.guardarVersion(nuevaVersion, etapaID);

        guardarRequisitos(etapa, respuestaId, proyectoId);

        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            try {
                var correo = this.personaRepositorioConsulta.consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
                var asunto = Mensajes.PRIMERA_VERSION_DE_LA_ETAPA_INICIADA;
                var cuerpo = Mensajes.LA_VERSION + versionId + Mensajes.DE_LA_ETAPA + etapa.getNombre() + Mensajes.DE_LA_FASE + fase.getNombre() + Mensajes.EN_EL_PROYECTO + proyecto.getNombre() + Mensajes.HA_SIDO_RECHAZADA_POR_EL_LIDER_DE_EQUIPO_CUYO_MOTIVO_ES + motivoRechazoVersion.getMotivo();

                this.servicioEnviarCorreoElectronico.enviarCorreo(correo, asunto, cuerpo);
            } catch (MessagingException e) {
                throw new RuntimeException(e.getMessage());
            }
        });

        return respuestaId;
    }

    private void validarSiExisteVersionConID(Long versionId) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_VERSION_CON_EL_ID + versionId);
        }
    }

    private void obtenerRequisitosEtapaAnteriorParaValidarSiRequisitosSonIgualesALosDeLaEtapaAnterior(EtapaDTO etapa, Long versionId, Long proyectoId) {
        var fases = this.faseRepositorioConsulta.consultarFasesPorProyectoID(proyectoId);
        var requisitosVersionActual = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(versionId);

        switch (etapa.getNombre()) {
            case TextoConstante.ETAPA_ANALISIS_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_DECLARACION_NOMBRE, requisitosVersionActual);
            case TextoConstante.ETAPA_NEGOCIACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_ANALISIS_NOMBRE, requisitosVersionActual);
            case TextoConstante.ETAPA_VERIFICACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_NEGOCIACION_NOMBRE, requisitosVersionActual);
            case TextoConstante.ETAPA_VALIDACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_VERIFICACION_NOMBRE, requisitosVersionActual);
            default -> {
                if(requisitosVersionActual.isEmpty()) {
                    throw new AutorizacionExcepcion(Mensajes.NO_HAY_REQUISITOS_PARA_LA_VERSION_ACTUAL_NO_PUEDE_RECHAZAR);
                }
            }
        }
    }

    private void obtenerRequisitosEtapaAnterior(List<FaseDTO> fases, String nombreEtapaAnterior, List<RequisitoDTO> requisitosVersionActual) {
        fases.forEach(fase -> fase.getEtapas().forEach(etapa -> {
            if (etapa.getNombre().equals(nombreEtapaAnterior)) {
                validarSiRequisitosSonIgualesALosDeLaEtapaAnterior(etapa.getId(), requisitosVersionActual);
            }
        }));
    }

    private void validarSiRequisitosSonIgualesALosDeLaEtapaAnterior(Long etapaId, List<RequisitoDTO> requisitosVersionActual) {
        var ultimaVersionEtapa = this.requisitoRepositorioConsulta.consultarUltimaVersionPorEtapaID(etapaId);
        var requisitosUltimaVersion = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(ultimaVersionEtapa.getId());

        if (requisitosUltimaVersion.size() == requisitosVersionActual.size() && tienenElementosComunes(requisitosUltimaVersion, requisitosVersionActual)) {
            throw new AutorizacionExcepcion(Mensajes.LA_VERSION_NO_HA_SUFRIDO_NINGUN_CAMBIO_CON_RESPECTO_A_LA_ETAPA_ANTERIOR_NO_PUEDE_RECHAZAR);
        }
    }

    public static boolean tienenElementosComunes(List<RequisitoDTO> lista1, List<RequisitoDTO> lista2) {
        for (RequisitoDTO requisito1 : lista1) {
            for (RequisitoDTO requisito2 : lista2) {
                if (sonIguales(requisito1, requisito2)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean sonIguales(RequisitoDTO requisito1, RequisitoDTO requisito2) {
        return requisito1.getNombre().equals(requisito2.getNombre()) &&
                requisito1.getDescripcion().equals(requisito2.getDescripcion()) &&
                requisito1.getTipoRequisito().getNombre().equals(requisito2.getTipoRequisito().getNombre());
    }

    private void guardarRequisitos(EtapaDTO etapa, Long versionId, Long proyectoId) {
        var fases = this.faseRepositorioConsulta.consultarFasesPorProyectoID(proyectoId);

        switch (etapa.getNombre()) {
            case TextoConstante.ETAPA_ANALISIS_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_DECLARACION_NOMBRE, versionId);
            case TextoConstante.ETAPA_NEGOCIACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_ANALISIS_NOMBRE, versionId);
            case TextoConstante.ETAPA_VERIFICACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_NEGOCIACION_NOMBRE, versionId);
            case TextoConstante.ETAPA_VALIDACION_NOMBRE ->
                    obtenerRequisitosEtapaAnterior(fases, TextoConstante.ETAPA_VERIFICACION_NOMBRE, versionId);
        }
    }

    private void obtenerRequisitosEtapaAnterior(List<FaseDTO> fases, String nombreEtapaAnterior, Long versionId) {
        fases.forEach(fase -> fase.getEtapas().forEach(etapa -> {
            if (etapa.getNombre().equals(nombreEtapaAnterior)) {
                guardarRequisitosPorEtapaYVersion(etapa.getId(), versionId);
            }
        }));
    }

    private void guardarRequisitosPorEtapaYVersion(Long etapaId, Long versionId) {
        var ultimaVersionEtapa = this.requisitoRepositorioConsulta.consultarUltimaVersionPorEtapaID(etapaId);
        var requisitosUltimaVersion = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(ultimaVersionEtapa.getId());

        requisitosUltimaVersion.forEach(requisitoDTO -> {
            var tipoRequisito = TipoRequisito.crear(requisitoDTO.getTipoRequisito().getNombre());
            var requisito = Requisito.crear(requisitoDTO.getNombre(), requisitoDTO.getDescripcion(), tipoRequisito);

            this.requisitoRepositorioComando.guardar(requisito, versionId);
        });
    }
}