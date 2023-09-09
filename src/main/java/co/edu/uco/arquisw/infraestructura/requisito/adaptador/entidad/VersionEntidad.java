package co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "version")
public class VersionEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "version_code_seq")
    @SequenceGenerator(name = "version_code_seq", sequenceName = "version_code_seq", allocationSize = 1)
    private Long id;
    private boolean esFinal;
    @Column(length = 10)
    private String fecha;
    private Long etapa;
}