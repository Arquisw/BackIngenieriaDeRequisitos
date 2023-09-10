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
@Table(name = "motivo_rechazo_version")
public class MotivoRechazoVersionEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "motivo_rechazo_version_code_seq")
    @SequenceGenerator(name = "motivo_rechazo_version_code_seq", sequenceName = "motivo_rechazo_version_code_seq", allocationSize = 1)
    private Long id;
    @Column(length = 3000)
    private String motivo;
    private Long version;
}