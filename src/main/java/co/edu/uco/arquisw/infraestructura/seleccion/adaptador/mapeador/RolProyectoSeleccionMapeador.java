package co.edu.uco.arquisw.infraestructura.seleccion.adaptador.mapeador;

import co.edu.uco.arquisw.infraestructura.seleccion.adaptador.entidad.RolProyectoSeleccionEntidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RolProyectoSeleccionMapeador {
    public String construirDTO(RolProyectoSeleccionEntidad rol) {
        return rol.getRol().getNombre();
    }

    public List<String> construirDTOs(List<RolProyectoSeleccionEntidad> roles) {
        return roles.stream().map(new RolProyectoSeleccionMapeador()::construirDTO).toList();
    }
}