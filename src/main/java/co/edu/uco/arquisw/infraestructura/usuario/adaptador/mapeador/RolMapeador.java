package co.edu.uco.arquisw.infraestructura.usuario.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.usuario.dto.RolDTO;
import co.edu.uco.arquisw.dominio.usuario.modelo.Rol;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.entidad.RolEntidad;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.entidad.RolUsuarioEntidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RolMapeador {
    public RolDTO construirDTO(RolUsuarioEntidad rol) {
        return new RolDTO(rol.getId(), rol.getRol().getNombre(), rol.getRol().isLeer(), rol.getRol().isEscribir(), rol.getRol().isActualizar(), rol.getRol().isEliminar());
    }

    public RolDTO construirDTO(RolEntidad rol) {
        return new RolDTO(rol.getId(), rol.getNombre(), rol.isLeer(), rol.isEscribir(), rol.isActualizar(), rol.isEliminar());
    }

    public List<RolDTO> construirDTOs(List<RolUsuarioEntidad> roles) {
        return roles.stream().map(new RolMapeador()::construirDTO).toList();
    }

    public List<RolDTO> construirBaseDTOs(List<RolEntidad> roles) {
        return roles.stream().map(new RolMapeador()::construirDTO).toList();
    }

    public RolUsuarioEntidad construirEntidad(Rol rol) {
        return new RolUsuarioEntidad(null, new RolEntidad(obtenerRolID(rol.getNombre()), rol.getNombre(), obtenerPermisoLeer(rol.getNombre()), obtenerPermisoEscribir(rol.getNombre()), obtenerPermisoActualizar(rol.getNombre()), obtenerPermisoEliminar(rol.getNombre())));
    }

    public List<RolUsuarioEntidad> construirEntidades(List<Rol> roles) {
        return roles.stream().map(new RolMapeador()::construirEntidad).toList();
    }

    private Long obtenerRolID(String nombre) {
        return switch (nombre) {
            case TextoConstante.ROL_USUARIO -> 1L;
            case TextoConstante.ROL_ASOCIACION -> 2L;
            case TextoConstante.ROL_ADMINISTRADOR -> 3L;
            case TextoConstante.ROL_POSTULADO -> 4L;
            case TextoConstante.ROL_SELECCIONADO -> 5L;
            case TextoConstante.ROL_DIRECTOR_PROYECTO -> 6L;
            case TextoConstante.ROL_PARTE_INTERESADA -> 7L;
            case TextoConstante.ROL_EQUIPO_DESARROLLO -> 8L;
            case TextoConstante.ROL_INGENIERIA -> 9L;
            case TextoConstante.ROL_ARQUITECTURA -> 10L;
            case TextoConstante.ROL_ANALISTA -> 11L;
            case TextoConstante.ROL_LIDER_DEL_EQUIPO -> 12L;
            case TextoConstante.ROL_PATROCINADOR -> 13L;
            default -> 0L;
        };
    }

    private boolean obtenerPermisoLeer(String nombre) {
        return switch (nombre) {
            case TextoConstante.ROL_DIRECTOR_PROYECTO,
                    TextoConstante.ROL_PARTE_INTERESADA,
                    TextoConstante.ROL_EQUIPO_DESARROLLO,
                    TextoConstante.ROL_INGENIERIA,
                    TextoConstante.ROL_ARQUITECTURA,
                    TextoConstante.ROL_ANALISTA,
                    TextoConstante.ROL_LIDER_DEL_EQUIPO,
                    TextoConstante.ROL_PATROCINADOR -> true;
            default -> false;
        };
    }

    private boolean obtenerPermisoEscribir(String nombre) {
        return switch (nombre) {
            case TextoConstante.ROL_SELECCIONADO,
                    TextoConstante.ROL_EQUIPO_DESARROLLO,
                    TextoConstante.ROL_INGENIERIA,
                    TextoConstante.ROL_ARQUITECTURA,
                    TextoConstante.ROL_LIDER_DEL_EQUIPO -> true;
            default -> false;
        };
    }

    private boolean obtenerPermisoActualizar(String nombre) {
        return switch (nombre) {
            case TextoConstante.ROL_SELECCIONADO,
                    TextoConstante.ROL_EQUIPO_DESARROLLO,
                    TextoConstante.ROL_INGENIERIA,
                    TextoConstante.ROL_ARQUITECTURA,
                    TextoConstante.ROL_LIDER_DEL_EQUIPO -> true;
            default -> false;
        };
    }

    private boolean obtenerPermisoEliminar(String nombre) {
        return switch (nombre) {
            case TextoConstante.ROL_SELECCIONADO,
                    TextoConstante.ROL_EQUIPO_DESARROLLO,
                    TextoConstante.ROL_INGENIERIA,
                    TextoConstante.ROL_ARQUITECTURA,
                    TextoConstante.ROL_LIDER_DEL_EQUIPO -> true;
            default -> false;
        };
    }
}