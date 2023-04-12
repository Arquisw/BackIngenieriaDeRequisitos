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
@Table(name = "requisitotiporequisito")
public class RequisitoTipoRequisitoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="requisitotiporequisito_code_seq")
    @SequenceGenerator(name="requisitotiporequisito_code_seq", sequenceName="requisitotiporequisito_code_seq", allocationSize=1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tiporequisito")
    private TipoRequisitoEntidad tipoRequisito;
}