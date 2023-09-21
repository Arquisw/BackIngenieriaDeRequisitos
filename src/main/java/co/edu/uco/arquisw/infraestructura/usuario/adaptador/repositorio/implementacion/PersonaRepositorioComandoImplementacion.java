package co.edu.uco.arquisw.infraestructura.usuario.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.usuario.modelo.Rol;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioComando;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.repositorio.jpa.RolUsuarioDAO;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.repositorio.jpa.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepositorioComandoImplementacion implements PersonaRepositorioComando {
    @Autowired
    UsuarioDAO usuarioDAO;
    @Autowired
    RolUsuarioDAO rolUsuarioDAO;

    @Override
    public void eliminarRol(Rol rol, Long id) {
        var entidad = this.usuarioDAO.findById(id).orElse(null);

        assert entidad != null;
        var roles = entidad.getRoles();
        var rolParaEliminar = roles.stream().filter(rolUsuario -> rolUsuario.getRol().getNombre().equals(rol.getNombre())).findFirst().orElse(null);

        assert rolParaEliminar != null;
        roles.remove(rolParaEliminar);
        entidad.setRoles(roles);

        this.rolUsuarioDAO.deleteById(rolParaEliminar.getId());
        this.usuarioDAO.save(entidad);
    }
}
