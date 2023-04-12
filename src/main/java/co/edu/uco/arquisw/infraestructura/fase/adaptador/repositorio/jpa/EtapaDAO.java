package co.edu.uco.arquisw.infraestructura.fase.adaptador.repositorio.jpa;

import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.EtapaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtapaDAO extends JpaRepository<EtapaEntidad, Long> { }