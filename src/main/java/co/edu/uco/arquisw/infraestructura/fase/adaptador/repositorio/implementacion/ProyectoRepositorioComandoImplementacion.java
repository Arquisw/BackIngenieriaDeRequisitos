package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.fase.modelo.proyecto.EstadoProyecto;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.ProyectoRepositorioComando;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador.proyecto.EstadoProyectoMapeador;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa.proyecto.ProyectoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProyectoRepositorioComandoImplementacion implements ProyectoRepositorioComando {
    @Autowired
    ProyectoDAO proyectoDAO;
    @Autowired
    EstadoProyectoMapeador estadoProyectoMapeador;

    @Override
    public void actualizarEstadoProyecto(EstadoProyecto estadoProyecto, Long proyectoID) {
        var entidad = this.proyectoDAO.findById(proyectoID).orElse(null);

        var estado = this.estadoProyectoMapeador.construirEntidad(estadoProyecto);

        assert entidad != null;
        entidad.getEstado().setEstado(estado.getEstado());

        this.proyectoDAO.save(entidad);
    }
}