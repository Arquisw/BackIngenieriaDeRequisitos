package co.edu.uco.arquisw.dominio.fase.puerto.comando;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;

public interface FaseRepositorioComando {
    Long guardar(Fase fase, Long proyectoID);
    Long actualizar(Fase fase, Long faseID);
    void actualizarEtapa(Etapa etapa, Long etapaID);
}