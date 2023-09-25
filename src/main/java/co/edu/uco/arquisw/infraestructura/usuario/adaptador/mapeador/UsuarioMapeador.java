package co.edu.uco.arquisw.infraestructura.usuario.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.usuario.dto.UsuarioDTO;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.entidad.UsuarioEntidad;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapeador {
    private final RolMapeador rolMapeador;

    public UsuarioMapeador(RolMapeador rolMapeador) {
        this.rolMapeador = rolMapeador;
    }

    public UsuarioDTO construirDTO(UsuarioEntidad usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getCorreo(), this.rolMapeador.construirDTOs(usuario.getRoles()));
    }
}
