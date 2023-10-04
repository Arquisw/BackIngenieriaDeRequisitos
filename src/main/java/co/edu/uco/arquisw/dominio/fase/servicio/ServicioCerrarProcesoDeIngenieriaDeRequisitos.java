package co.edu.uco.arquisw.dominio.fase.servicio;

import co.edu.uco.arquisw.dominio.fase.modelo.proyecto.EstadoProyecto;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.ProyectoRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioActualizarToken;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.usuario.modelo.Rol;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioComando;

import java.util.List;

public class ServicioCerrarProcesoDeIngenieriaDeRequisitos {
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final ProyectoRepositorioComando proyectoRepositorioComando;
    private final PersonaRepositorioComando personaRepositorioComando;
    private final ServicioActualizarToken servicioActualizarToken;

    public ServicioCerrarProcesoDeIngenieriaDeRequisitos(ProyectoRepositorioConsulta proyectoRepositorioConsulta, ProyectoRepositorioComando proyectoRepositorioComando, PersonaRepositorioComando personaRepositorioComando, ServicioActualizarToken servicioActualizarToken) {
        this.proyectoRepositorioConsulta = proyectoRepositorioConsulta;
        this.proyectoRepositorioComando = proyectoRepositorioComando;
        this.personaRepositorioComando = personaRepositorioComando;
        this.servicioActualizarToken = servicioActualizarToken;
    }

    public void ejecutar(List<RequisitoDTO> requisitosUltimaVersion, Long proyectoID, List<SeleccionDTO> seleccionesDelProyecto) {
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoID);

        if(proyecto.getTiposConsultoria().size() == 1 && proyecto.getTiposConsultoria().stream().anyMatch(tipoConsultoria -> tipoConsultoria.getNombre().equals(TextoConstante.INGENIERIA_DE_REQUISITOS))) {
            this.proyectoRepositorioComando.actualizarEstadoProyecto(EstadoProyecto.crear(TextoConstante.ESTADO_FINALIZADO), proyectoID);

            eliminarRolesDelProyectoALosUsuariosSeleccionados(seleccionesDelProyecto);
        } else if(proyecto.getTiposConsultoria().stream().anyMatch(tipoConsultoria -> tipoConsultoria.getNombre().equals(TextoConstante.SQA) || tipoConsultoria.getNombre().equals(TextoConstante.SQC))) {
            enviarRequisitosFinalesAlSistemaDeSQAYSQC(requisitosUltimaVersion, proyectoID);
        }
    }

    private void eliminarRolesDelProyectoALosUsuariosSeleccionados(List<SeleccionDTO> seleccionesDelProyecto) {
        seleccionesDelProyecto.forEach(seleccion -> {
            this.personaRepositorioComando.eliminarRol(Rol.crear(TextoConstante.ROL_SELECCIONADO), seleccion.getUsuarioID());

            seleccion.getRoles().forEach(rol -> {
                this.personaRepositorioComando.eliminarRol(Rol.crear(rol), seleccion.getUsuarioID());
            });
        });

        this.servicioActualizarToken.ejecutar();
    }

    private void enviarRequisitosFinalesAlSistemaDeSQAYSQC(List<RequisitoDTO> requisitosUltimaVersion, Long proyectoID) {
        // Crear aquí el flujo
    }
}
