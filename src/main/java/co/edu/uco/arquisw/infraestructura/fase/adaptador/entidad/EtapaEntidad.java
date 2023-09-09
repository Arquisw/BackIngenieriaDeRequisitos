package co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad;

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
@Table(name = "etapa")
public class EtapaEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "etapa_code_seq")
    @SequenceGenerator(name = "etapa_code_seq", sequenceName = "etapa_code_seq", allocationSize = 1)
    private long id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    private boolean completada;
}