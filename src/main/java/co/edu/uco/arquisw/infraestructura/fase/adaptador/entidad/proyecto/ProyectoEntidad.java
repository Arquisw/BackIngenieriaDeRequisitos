package co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.proyecto;

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
@Table(name = "proyecto")
public class ProyectoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "proyecto_code_seq")
    @SequenceGenerator(name = "proyecto_code_seq", sequenceName = "proyecto_code_seq", allocationSize = 1)
    private Long id;
    @Column(length = 100)
    private String nombre;
    @Column(length = 5000)
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "estado")
    private EstadoProyectoEntidad estado;
    @OneToOne
    @JoinColumn(name = "aprobacion_proyecto")
    private AprobacionProyectoEntidad aprobacionProyecto;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "proyecto")
    private List<TipoConsultoriaProyectoEntidad> tiposConsultoria;
}