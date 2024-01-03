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
@Table(name = "requisito_final")
public class RequisitoFinalEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "requisito_final_code_seq")
    @SequenceGenerator(name = "requisito_final_code_seq", sequenceName = "requisito_final_code_seq", allocationSize = 1)
    private Long id;
    private Long proyectoId;
    @Column(length = 50)
    private String nombre;
    @Column(length = 3000)
    private String descripcion;
    @Column(length = 12)
    private String tipoRequisito;
}