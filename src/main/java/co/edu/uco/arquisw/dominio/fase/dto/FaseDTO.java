package co.edu.uco.arquisw.dominio.fase.dto;

import co.edu.uco.arquisw.dominio.etapa.dto.EtapaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaseDTO {
    private String nombre;
    private String descripcion;
    private boolean completado;
    private List<EtapaDTO> etapa;
}
