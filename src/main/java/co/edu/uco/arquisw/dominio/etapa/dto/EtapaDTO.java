package co.edu.uco.arquisw.dominio.etapa.dto;

import co.edu.uco.arquisw.dominio.version.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.version.modelo.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtapaDTO {
    private String nombre;
    private String descripcion;
    private boolean completado;
    private List<VersionDTO> version;
}
