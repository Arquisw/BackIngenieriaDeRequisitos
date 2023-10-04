package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AutorizacionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
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
    private final ServicioActualizarFase servicioActualizarFase;
    private final ServicioConstruirNuevaFase servicioConstruirNuevaFase;

    public ServicioAprobarEtapa(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, PersonaRepositorioConsulta personaRepositorioConsulta, ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, RequisitoRepositorioConsulta requisitoRepositorioConsulta, ServicioActualizarFase servicioActualizarFase, ServicioConstruirNuevaFase servicioConstruirNuevaFase) {
        this.faseRepositorioComando = faseRepositorioComando;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
        this.seleccionRepositorioConsulta = seleccionRepositorioConsulta;
        this.proyectoRepositorioConsulta = proyectoRepositorioConsulta;
        this.personaRepositorioConsulta = personaRepositorioConsulta;
        this.servicioEnviarCorreoElectronico = servicioEnviarCorreoElectronico;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.servicioActualizarFase = servicioActualizarFase;
        this.servicioConstruirNuevaFase = servicioConstruirNuevaFase;
    }

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

        notificarAprobacionDeLaEtapa(seleccionesDelProyecto, fase, etapa, proyecto);

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

    private void notificarAprobacionDeLaEtapa(List<SeleccionDTO> seleccionesDelProyecto, FaseDTO fase, EtapaDTO etapa, ProyectoDTO proyecto) {
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
    }
}