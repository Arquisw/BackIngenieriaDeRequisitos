package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AutorizacionExcepcion;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ServicioValidarSiRequisitosSonIguales {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public void ejecutar(List<FaseDTO> fases, String nombreEtapaAnterior, List<RequisitoDTO> requisitosVersionActual, String mensajeExcepcion) {
        fases.forEach(fase -> fase.getEtapas().forEach(etapa -> {
            if (etapa.getNombre().equals(nombreEtapaAnterior)) {
                validarSiRequisitosSonIgualesALosDeLaEtapaAnterior(etapa.getId(), requisitosVersionActual, mensajeExcepcion);
            }
        }));
    }

    private void validarSiRequisitosSonIgualesALosDeLaEtapaAnterior(Long etapaId, List<RequisitoDTO> requisitosVersionActual, String mensajeExcepcion) {
        var ultimaVersionEtapa = this.requisitoRepositorioConsulta.consultarUltimaVersionPorEtapaID(etapaId);
        var requisitosUltimaVersion = this.requisitoRepositorioConsulta.consultarRequisitosPorVersionID(ultimaVersionEtapa.getId());

        if (requisitosUltimaVersion.size() == requisitosVersionActual.size() && tienenElementosComunes(requisitosUltimaVersion, requisitosVersionActual)) {
            throw new AutorizacionExcepcion(mensajeExcepcion);
        }
    }

    public static boolean tienenElementosComunes(List<RequisitoDTO> lista1, List<RequisitoDTO> lista2) {
        for (int i = 0; i < lista1.size(); i++) {
            var requisito1 = lista1.get(i);
            var requisito2 = lista2.get(i);

            if (!sonIguales(requisito1, requisito2)) {
                return false;
            }
        }

        return true;
    }

    private static boolean sonIguales(RequisitoDTO requisito1, RequisitoDTO requisito2) {
        return requisito1.getNombre().equals(requisito2.getNombre()) &&
                requisito1.getDescripcion().equals(requisito2.getDescripcion()) &&
                requisito1.getTipoRequisito().getNombre().equals(requisito2.getTipoRequisito().getNombre());
    }
}