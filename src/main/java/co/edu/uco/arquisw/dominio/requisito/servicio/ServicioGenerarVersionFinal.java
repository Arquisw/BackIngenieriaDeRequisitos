package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;
import lombok.AllArgsConstructor;

import javax.mail.MessagingException;
import java.util.List;

@AllArgsConstructor
public class ServicioGenerarVersionFinal {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final PersonaRepositorioConsulta personaRepositorioConsulta;
    private final ServicioObtenerRequisitosEtapaAnterior servicioObtenerRequisitosEtapaAnterior;
    private final ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico;

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

        notificar(seleccionesDelProyecto, versionId, etapa, fase, proyecto);

        return respuestaId;
    }

    private void validarSiExisteVersionConID(Long versionId) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_VERSION_CON_EL_ID + versionId);
        }
    }

    private void notificar(List<SeleccionDTO> seleccionesDelProyecto, Long versionId, EtapaDTO etapa, FaseDTO fase, ProyectoDTO proyecto) {
        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            try {
                if (seleccionDelProyecto.getRoles().contains(TextoConstante.ROL_LIDER_DEL_EQUIPO)) {
                    var correo = this.personaRepositorioConsulta.consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
                    var asunto = Mensajes.VERSION_FINAL_GENERADA_Y_LISTA_PARA_REVISION;
                    var cuerpo = Mensajes.LA_VERSION + versionId + Mensajes.DE_LA_ETAPA + etapa.getNombre() + Mensajes.DE_LA_FASE + fase.getNombre() + Mensajes.EN_EL_PROYECTO + proyecto.getNombre() + Mensajes.HA_SIDO_ESTABLECIDA_COMO_VERSION_FINAL_POR_LO_TANTO_ESTA_LISTA_PARA_REVISION;

                    this.servicioEnviarCorreoElectronico.enviarCorreo(correo, asunto, cuerpo);
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}