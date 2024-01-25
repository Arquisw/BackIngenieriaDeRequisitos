package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;

public class ServicioTerminoProcesoIngenieriaRequisitosPorProyectoID {
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;

    public ServicioTerminoProcesoIngenieriaRequisitosPorProyectoID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        this.requisitoRepositorioConsulta = requisitoRepositorioConsulta;
    }

    public Boolean ejecutar(Long proyectoId) {
        return this.requisitoRepositorioConsulta.terminoProcesoDeIngenieriaDeRequisitosPorProyectoID(proyectoId);
    }
}
