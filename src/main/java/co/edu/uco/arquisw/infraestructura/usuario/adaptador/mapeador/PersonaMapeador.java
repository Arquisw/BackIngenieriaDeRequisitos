package co.edu.uco.arquisw.infraestructura.usuario.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.usuario.dto.PersonaDTO;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.entidad.PersonaEntidad;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapeador {
    public PersonaDTO construirDTO(PersonaEntidad persona) {
        return new PersonaDTO(persona.getId(), persona.getNombre(), persona.getApellidos(), persona.getCorreo());
    }
}