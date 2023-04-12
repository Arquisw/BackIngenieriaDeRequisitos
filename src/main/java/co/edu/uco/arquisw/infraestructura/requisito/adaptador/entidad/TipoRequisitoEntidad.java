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
@Table(name = "tiporequisito")
public class TipoRequisitoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="tiporequisito_code_seq")
    @SequenceGenerator(name="tiporequisito_code_seq", sequenceName="tiporequisito_code_seq", allocationSize=1)
    private Long id;
    @Column(length = 50)
    private String nombre;
}