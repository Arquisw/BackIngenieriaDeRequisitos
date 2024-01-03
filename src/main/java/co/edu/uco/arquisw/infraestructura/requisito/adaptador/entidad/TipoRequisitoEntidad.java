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
@Table(name = "tipo_requisito")
public class TipoRequisitoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tipo_requisito_code_seq")
    @SequenceGenerator(name = "tipo_requisito_code_seq", sequenceName = "tipo_requisito_code_seq", allocationSize = 1)
    private Long id;
    @Column(length = 12)
    private String nombre;
}