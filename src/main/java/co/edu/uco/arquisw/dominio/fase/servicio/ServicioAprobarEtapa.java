package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AutorizacionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;

import javax.mail.MessagingException;
import java.util.List;

public class ServicioAprobarEtapa {
    private final FaseRepositorioComando faseRepositorioComando;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final PersonaRepositorioConsulta personaRepositorioConsulta;
    private final ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final RequisitoRepositorioComando requisitoRepositorioComando;

    public ServicioAprobarEtapa(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, PersonaRepositorioConsulta personaRepositorioConsulta, ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, RequisitoRepositorioConsulta requisitoRepositorioConsulta, RequisitoRepositorioComando requisitoRepositorioComando) {
        this.faseRepositorioComando = faseRepositorioComando;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
        this.seleccionRepositorioConsulta = seleccionRepositorioConsulta;
        this.proyectoRepositorioConsulta = proyectoRepositorioConsulta;
        this.personaRepositorioConsulta = personaRepositorioConsulta;
        this.servicioEnviarCorreoElectronico = servicioEnviarCorreoElectronico;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.requisitoRepositorioComando = requisitoRepositorioComando;
    }

    public Long ejecutar(Long etapaID) {
        validarSiExisteEtapaConID(etapaID);

        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaID);
        var fase = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID);
        var proyectoId = fase.getProyectoID();
        var seleccionesDelProyecto = this.seleccionRepositorioConsulta.consultarSeleccionadosPorProyecto(proyectoId);
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoId);

        validarSiEtapaNoEstaCompletada(etapa);
        validarSiVersionFinalFueGenerada(etapaID);

        var etapaActualizada = construirEtapaAprobada(etapa);

        this.faseRepositorioComando.actualizarEtapa(etapaActualizada, etapaID);

        var respuestId = actualizarFase(etapaActualizada, etapaID);

        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            try {
                var correo = this.personaRepositorioConsulta.consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
                var asunto = Mensajes.ETAPA_APROBADA_POR_EL_LIDER_DE_EQUIPO;
                var cuerpo = Mensajes.LA_ETAPA + etapa.getNombre() + Mensajes.DE_LA_FASE + fase.getNombre() + Mensajes.EN_EL_PROYECTO + proyecto.getNombre() + Mensajes.HA_SIDO_APROBADO_POR_EL_ROL_LIDER_DE_EQUIPO;

                this.servicioEnviarCorreoElectronico.enviarCorreo(correo, asunto, cuerpo);
            } catch (MessagingException e) {
                throw new RuntimeException(e.getMessage());
            }
        });

        return respuestId;
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

    private Etapa construirEtapaAprobada(EtapaDTO etapa) {
        return Etapa.crear(etapa.getNombre(), etapa.getDescripcion(), LogicoConstante.ESTADO_ETAPA_COMPLETADA);
    }

    private Long actualizarFase(Etapa etapaAnterior, Long etapaID) {
        Long id;
        var proyectoID = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID).getProyectoID();
        var ultimaVersionEtapa = this.requisitoRepositorioConsulta.consultarUltimaVersionPorEtapaID(etapaID);
        var requisitosUltimaVersion = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(ultimaVersionEtapa.getId());

        switch (etapaAnterior.getNombre()) {
            case (TextoConstante.ETAPA_DECLARACION_NOMBRE) -> {
                var nombreSiguienteFase = TextoConstante.FASE_PLANIFICACION_NOMBRE;
                var descripcionSiguienteFase = TextoConstante.FASE_PLANIFICACION_DESCRIPCION;
                var nombreSiguienteEtapa = TextoConstante.ETAPA_ANALISIS_NOMBRE;
                var descripcionSiguienteEtapa = TextoConstante.ETAPA_ANALISIS_DESCRIPCION;

                var fase = construirNuevaFase(nombreSiguienteFase, descripcionSiguienteFase, nombreSiguienteEtapa, descripcionSiguienteEtapa);

                var faseId = this.faseRepositorioComando.guardar(fase, proyectoID);

                var nuevaFase = this.faseRepositorioConsulta.consultarFasePorID(faseId);

                id = nuevaFase.getEtapas().get(0).getId();

                guardarRequisitos(requisitosUltimaVersion, id);
            }
            case (TextoConstante.ETAPA_ANALISIS_NOMBRE) -> {
                var nombreSiguienteFase = TextoConstante.FASE_EJECUCION_NOMBRE;
                var descripcionSiguienteFase = TextoConstante.FASE_EJECUCION_DESCRIPCION;
                var nombreSiguienteEtapa = TextoConstante.ETAPA_NEGOCIACION_NOMBRE;
                var descripcionSiguienteEtapa = TextoConstante.ETAPA_NEGOCIACION_DESCRIPCION;
                var fase = construirNuevaFase(nombreSiguienteFase, descripcionSiguienteFase, nombreSiguienteEtapa, descripcionSiguienteEtapa);

                var faseId = this.faseRepositorioComando.guardar(fase, proyectoID);

                var nuevaFase = this.faseRepositorioConsulta.consultarFasePorID(faseId);

                id = nuevaFase.getEtapas().get(0).getId();

                guardarRequisitos(requisitosUltimaVersion, id);
            }
            case (TextoConstante.ETAPA_NEGOCIACION_NOMBRE) -> {
                var nombreSiguienteFase = TextoConstante.FASE_MONITOREO_Y_CONTROL_NOMBRE;
                var descripcionSiguienteFase = TextoConstante.FASE_MONITOREO_Y_CONTROL_DESCRIPCION;
                var nombreSiguienteEtapa = TextoConstante.ETAPA_VERIFICACION_NOMBRE;
                var descripcionSiguienteEtapa = TextoConstante.ETAPA_VERIFICACION_DESCRIPCION;
                var fase = construirNuevaFase(nombreSiguienteFase, descripcionSiguienteFase, nombreSiguienteEtapa, descripcionSiguienteEtapa);

                var faseId = this.faseRepositorioComando.guardar(fase, proyectoID);

                var nuevaFase = this.faseRepositorioConsulta.consultarFasePorID(faseId);

                id = nuevaFase.getEtapas().get(0).getId();

                guardarRequisitos(requisitosUltimaVersion, id);
            }
            case (TextoConstante.ETAPA_VERIFICACION_NOMBRE) -> {
                var fase = construirFaseActualizada(etapaAnterior);
                var faseID = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID).getId();

                var faseId = this.faseRepositorioComando.actualizar(fase, faseID);

                var nuevaFase = this.faseRepositorioConsulta.consultarFasePorID(faseId);

                id = nuevaFase.getEtapas().get(1).getId();

                guardarRequisitos(requisitosUltimaVersion, id);
            }
            case (TextoConstante.ETAPA_VALIDACION_NOMBRE) -> {
                var fase = construirFaseFinal();

                var faseId = this.faseRepositorioComando.guardar(fase, proyectoID);

                var nuevaFase = this.faseRepositorioConsulta.consultarFasePorID(faseId);

                id = nuevaFase.getEtapas().get(0).getId();

                guardarRequisitos(requisitosUltimaVersion, id);

                enviarRequisitosFinalesAlSistemaDeSQAYSQC(requisitosUltimaVersion, proyectoID);
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
        var etapaDefinitiva = Etapa.crear(TextoConstante.ETAPA_DEFINITIVA_NOMBRE, TextoConstante.ETAPA_DEFINITIVA_DESCRIPCION, LogicoConstante.ESTADO_ETAPA_COMPLETADA);

        return List.of(etapaDefinitiva);
    }

    private void guardarRequisitos(List<RequisitoDTO> requisitosUltimaVersion, Long etapaId) {
        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, LogicoConstante.NO_ESTA_RECHAZADA);
        var versionId = this.requisitoRepositorioComando.guardarVersion(nuevaVersion, etapaId);

        requisitosUltimaVersion.forEach(requisitoDTO -> {
            var tipoRequisito = TipoRequisito.crear(requisitoDTO.getTipoRequisito().getNombre());
            var requisito = Requisito.crear(requisitoDTO.getNombre(), requisitoDTO.getDescripcion(), tipoRequisito);

            this.requisitoRepositorioComando.guardar(requisito, versionId);
        });
    }

    private void enviarRequisitosFinalesAlSistemaDeSQAYSQC(List<RequisitoDTO> requisitosUltimaVersion, Long proyectoID) {
        // Crear aquí el flujo
    }
}