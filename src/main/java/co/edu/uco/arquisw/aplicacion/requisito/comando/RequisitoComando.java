package co.edu.uco.arquisw.aplicacion.requisito.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoComando {
    private String nombre;
    private String descripcion;
    private TipoRequisitoComando tipoRequisito;
}