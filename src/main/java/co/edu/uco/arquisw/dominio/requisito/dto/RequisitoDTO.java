package co.edu.uco.arquisw.dominio.requisito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private TipoRequisitoDTO tipoRequisito;
}