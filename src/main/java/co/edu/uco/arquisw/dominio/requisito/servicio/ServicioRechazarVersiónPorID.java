package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;

import javax.mail.MessagingException;

public class ServicioRechazarVersiónPorID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final PersonaRepositorioConsulta personaRepositorioConsulta;
    private final ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico;

    public ServicioRechazarVersiónPorID(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, PersonaRepositorioConsulta personaRepositorioConsulta, ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico) {
        this.requisitoRepositorioComando = requisitoRepositorioComando;
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
        this.faseRepositorioConsulta = faseRepositorioConsulta;
        this.seleccionRepositorioConsulta = seleccionRepositorioConsulta;
        this.proyectoRepositorioConsulta = proyectoRepositorioConsulta;
        this.personaRepositorioConsulta = personaRepositorioConsulta;
        this.servicioEnviarCorreoElectronico = servicioEnviarCorreoElectronico;
    }

    public Long ejecutar(Long versionId) {
        validarSiExisteVersionConID(versionId);

        var versionDTO = this.requisitoRepositorioConsulta.consultarVersionPorID(versionId);
        var etapaID = versionDTO.getEtapaID();
        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaID);
        var fase = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID);
        var proyectoId = fase.getProyectoID();
        var seleccionesDelProyecto = this.seleccionRepositorioConsulta.consultarSeleccionadosPorProyecto(proyectoId);
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoId);

        this.requisitoRepositorioComando.actualizarVersion(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, versionId);

        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO);

        var respuestaId = this.requisitoRepositorioComando.guardarVersion(nuevaVersion, etapaID);

        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            try {
                var correo = this.personaRepositorioConsulta.consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
                var asunto = Mensajes.PRIMERA_VERSION_DE_LA_ETAPA_INICIADA;
                var cuerpo = Mensajes.LA_VERSION + versionId + Mensajes.DE_LA_ETAPA + etapa.getNombre() + Mensajes.DE_LA_FASE + fase.getNombre() + Mensajes.EN_EL_PROYECTO + proyecto.getNombre() + Mensajes.HA_SIDO_RECHAZADA_POR_EL_LIDER_DE_EQUIPO;


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
}