package co.edu.uco.arquisw.dominio.requisito.dto;

import co.edu.uco.arquisw.dominio.tipoRequisito.modelo.TipoRequisito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RquisitoDTO {
    private String nombre;
    private String descripcion;
    private TipoRequisito tipoRequisito;
}
