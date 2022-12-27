package co.edu.uco.arquisw.dominio.version.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VersionDTO {
    private boolean esFinal;
    private LocalDate fecha;
}
