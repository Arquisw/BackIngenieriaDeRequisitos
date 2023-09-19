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
@Table(name = "requisitos_finales")
public class RequisitosFinalesEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "requisitos_finales_code_seq")
    @SequenceGenerator(name = "requisitos_finales_code_seq", sequenceName = "requisitos_finales_code_seq", allocationSize = 1)
    private Long id;
    @Column(length = 3000)
    private String rutaArchivo;
    private Long etapa;
}