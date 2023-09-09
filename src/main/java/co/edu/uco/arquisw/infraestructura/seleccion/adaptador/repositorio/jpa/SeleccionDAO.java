package co.edu.uco.arquisw.infraestructura.seleccion.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.seleccion.adaptador.entidad.SeleccionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeleccionDAO extends JpaRepository<SeleccionEntidad, Long> {
}