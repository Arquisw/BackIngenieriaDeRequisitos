package co.edu.uco.arquisw.infraestructura.fase.adaptador.mapeador;

import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.EtapaEntidad;
import co.edu.uco.arquisw.infraestructura.fase.adaptador.entidad.FaseEntidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FaseMapeador {
    private final EtapaMapeador etapaMapeador;

    public FaseMapeador(EtapaMapeador etapaMapeador) {
        this.etapaMapeador = etapaMapeador;
    }

    public FaseEntidad construirEntidad(Fase fase, Long proyectoID) {
        return new FaseEntidad(0L, fase.getNombre(), fase.getDescripcion(), this.etapaMapeador.construirEntidades(fase.getEtapas()), proyectoID);
    }

    public FaseDTO construirDTO(FaseEntidad fase) {
        return new FaseDTO(fase.getId(), fase.getNombre(), fase.getDescripcion(), this.etapaMapeador.construirDTOs(fase.getEtapas()), fase.getProyecto());
    }

    public List<FaseDTO> construirDTOs(List<FaseEntidad> fases) {
        return fases.stream().map(new FaseMapeador(etapaMapeador)::construirDTO).toList();
    }

    public void actualizarEntidad(FaseEntidad faseEntidad, Fase fase) {
        if (!fase.getNombre().equals(faseEntidad.getNombre())) {
            faseEntidad.setNombre(fase.getNombre());
        }

        if (!fase.getDescripcion().equals(faseEntidad.getDescripcion())) {
            faseEntidad.setDescripcion(fase.getDescripcion());
        }

        actualizarEtapas(faseEntidad, fase);
    }

    private void actualizarEtapas(FaseEntidad faseEntidad, Fase fase) {
        var etapasEntidad = faseEntidad.getEtapas();
        var etapas = fase.getEtapas();

        if (etapas.size() == 2 && etapasEntidad.size() == 1) {
            EtapaEntidad nuevaEtapa = null;

            for (var etapa : etapas) {
                boolean existe = existeActualizacion(etapa, etapasEntidad);

                if (!existe) {
                    nuevaEtapa = new EtapaEntidad(0L, etapa.getNombre(), etapa.getDescripcion(), etapa.isCompletada());

                    break;
                }
            }

            if (!ValidarObjeto.esNulo(nuevaEtapa)) {
                etapasEntidad.add(nuevaEtapa);
                faseEntidad.setEtapas(etapasEntidad);
            }
        }
    }

    private boolean existeActualizacion(Etapa etapa, List<EtapaEntidad> etapasEntidad) {
        var existe = false;

        for (EtapaEntidad etapaEntidad : etapasEntidad) {
            if (etapa.getNombre().equals(etapaEntidad.getNombre()) && etapa.getDescripcion().equals(etapaEntidad.getDescripcion()) && etapa.isCompletada() == etapaEntidad.isCompletada()) {
                existe = true;
                break;
            }
        }

        return existe;
    }
}