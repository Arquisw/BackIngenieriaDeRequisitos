package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.FaseEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaseDAO extends JpaRepository<FaseEntidad, Long> { }