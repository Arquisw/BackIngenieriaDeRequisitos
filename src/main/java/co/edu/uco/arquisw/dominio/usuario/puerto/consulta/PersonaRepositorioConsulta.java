package co.edu.uco.arquisw.dominio.usuario.puerto.consulta;

import co.edu.uco.arquisw.dominio.usuario.dto.PersonaDTO;

public interface PersonaRepositorioConsulta {
    PersonaDTO consultarPorId(Long id);
}