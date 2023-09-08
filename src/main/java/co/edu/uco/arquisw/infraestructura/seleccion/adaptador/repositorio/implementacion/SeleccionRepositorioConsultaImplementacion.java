package co.edu.uco.arquisw.infraestructura.seleccion.adaptador.repositorio.implementacion;

import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.infraestructura.seleccion.adaptador.mapeador.SeleccionMapeador;
import co.edu.uco.arquisw.infraestructura.seleccion.adaptador.repositorio.jpa.SeleccionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeleccionRepositorioConsultaImplementacion implements SeleccionRepositorioConsulta {
    @Autowired
    SeleccionDAO seleccionDAO;
    @Autowired
    SeleccionMapeador seleccionMapeador;

    @Override
    public List<SeleccionDTO> consultarSeleccionadosPorProyecto(Long proyectoID) {
        var entidades = this.seleccionDAO.findAll();

        var selecciones = entidades.stream().filter(entidad -> entidad.getProyecto().equals(proyectoID)).toList();

        return this.seleccionMapeador.construirDTOs(selecciones);
    }
}
