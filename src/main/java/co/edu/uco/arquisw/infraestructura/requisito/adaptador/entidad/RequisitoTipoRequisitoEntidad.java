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
@Table(name = "requisito_tipo_requisito")
public class RequisitoTipoRequisitoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="requisito_tipo_requisito_code_seq")
    @SequenceGenerator(name="requisito_tipo_requisito_code_seq", sequenceName="requisito_tipo_requisito_code_seq", allocationSize=1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tipo_requisito")
    private TipoRequisitoEntidad tipoRequisito;
}