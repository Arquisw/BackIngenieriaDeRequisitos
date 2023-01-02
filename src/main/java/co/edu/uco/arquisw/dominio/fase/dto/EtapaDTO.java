package co.edu.uco.arquisw.dominio.fase.dto;

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
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean completada;
    private List<VersionDTO> versiones;
}
