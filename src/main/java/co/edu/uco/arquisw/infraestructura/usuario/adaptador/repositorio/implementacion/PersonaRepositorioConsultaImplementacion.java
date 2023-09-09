package co.edu.uco.arquisw.infraestructura.usuario.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.dominio.usuario.dto.PersonaDTO;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.mapeador.PersonaMapeador;
import co.edu.uco.arquisw.infraestructura.usuario.adaptador.repositorio.jpa.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepositorioConsultaImplementacion implements PersonaRepositorioConsulta {
    @Autowired
    PersonaDAO personaDAO;
    @Autowired
    PersonaMapeador personaMapeador;

    @Override
    public PersonaDTO consultarPorId(Long id) {
        var entidad = this.personaDAO.findById(id).orElse(null);

        if (ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.personaMapeador.construirDTO(entidad);
    }
}
