package co.edu.uco.arquisw.dominio.requisito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VersionDTO {
    private Long id;
    private boolean esFinal;
    private boolean estaRechazada;
    private String motivoRechazo;
    private String fecha;
    private Long etapaID;
}