package co.edu.uco.arquisw.dominio.usuario.puerto.consulta;

import co.edu.uco.arquisw.dominio.usuario.modelo.Rol;

public interface PersonaRepositorioComando {
    void eliminarRol(Rol rol, Long id);
}
