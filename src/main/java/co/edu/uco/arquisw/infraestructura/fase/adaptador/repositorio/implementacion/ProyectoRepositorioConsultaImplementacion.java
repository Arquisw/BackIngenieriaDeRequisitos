package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.proyecto.ProyectoMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.proyecto.ProyectoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProyectoRepositorioConsultaImplementacion implements ProyectoRepositorioConsulta {
    @Autowired
    ProyectoDAO proyectoDAO;
    @Autowired
    ProyectoMapeador proyectoMapeador;

    @Override
    public ProyectoDTO consultarProyectoPorID(Long proyectoID) {
        var entidad = this.proyectoDAO.findById(proyectoID).orElse(null);

        if (ValidarObjeto.esNulo(entidad)) {
            return null;
        }

        return this.proyectoMapeador.construirDTO(entidad);
    }
}