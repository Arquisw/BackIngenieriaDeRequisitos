package co.edu.uco.arquisw.infraestructura.requisito.adaptador.entidad;

import co.edu.uco.arquisw.dominio.requisito.dto.TipoRequisitoDTO;
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
@Table(name = "requisito")
public class RequisitoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="requisito_code_seq")
    @SequenceGenerator(name="requisito_code_seq", sequenceName="requisito_code_seq", allocationSize=1)
    private Long id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "tipo_requisito")
    private RequisitoTipoRequisitoEntidad tipoRequisito;
    private Long versionID;
    private Long etapaID;
}