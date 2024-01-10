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
@Table(name = "requisito_inicial")
public class RequisitoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "requisito_inicial_code_seq")
    @SequenceGenerator(name = "requisito_inicial_code_seq", sequenceName = "requisito_inicial_code_seq", allocationSize = 1)
    private Long id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 3000)
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "tipo_requisito")
    private RequisitoTipoRequisitoEntidad tipoRequisito;
    private Long version;
}