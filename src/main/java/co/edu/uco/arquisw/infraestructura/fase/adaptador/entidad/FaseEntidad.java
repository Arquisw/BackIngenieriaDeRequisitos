package co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fase")
public class FaseEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="fase_code_seq")
    @SequenceGenerator(name="fase_code_seq", sequenceName="fase_code_seq", allocationSize=1)
    private Long id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fase")
    private List<EtapaEntidad> etapas;
    private Long proyecto;
}
