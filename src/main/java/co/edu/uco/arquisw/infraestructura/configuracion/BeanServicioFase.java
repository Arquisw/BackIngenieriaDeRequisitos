package co.edu.uco.arquisw.infraestructura.configuracion;

import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.ProyectoRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.*;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFaseCierre;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFaseEjecucion;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFaseMonitoreoYControl;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.ServicioSiguienteFasePlanificacion;
import co.edu.uco.arquisw.dominio.fase.servicio.siguientefase.factoria.ServicioSiguienteFaseFactoria;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.servicio.ServicioGuardarRequisitos;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioActualizarToken;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria.ServicioEnviarNotificacionFactoria;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioComando;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioFase {
    @Bean
    public ServicioActualizarFase servicioActualizarFase(FaseRepositorioConsulta faseRepositorioConsulta, RequisitoRepositorioConsulta requisitoRepositorioConsulta, ServicioSiguienteFaseFactoria servicioSiguienteFaseFactoria) {
        return new ServicioActualizarFase(faseRepositorioConsulta, requisitoRepositorioConsulta, servicioSiguienteFaseFactoria);
    }

    @Bean
    public ServicioAprobarEtapa servicioAprobarEtapa(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, RequisitoRepositorioConsulta requisitoRepositorioConsulta, ServicioActualizarFase servicioActualizarFase, ServicioConstruirNuevaFase servicioConstruirNuevaFase, ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria) {
        return new ServicioAprobarEtapa(faseRepositorioComando, faseRepositorioConsulta, seleccionRepositorioConsulta, proyectoRepositorioConsulta, requisitoRepositorioConsulta, servicioActualizarFase, servicioConstruirNuevaFase, servicioEnviarNotificacionFactoria);
    }

    @Bean
    public ServicioCerrarProcesoDeIngenieriaDeRequisitos servicioCerrarProcesoDeIngenieriaDeRequisitos(ProyectoRepositorioConsulta proyectoRepositorioConsulta, ProyectoRepositorioComando proyectoRepositorioComando, PersonaRepositorioComando personaRepositorioComando, ServicioActualizarToken servicioActualizarToken) {
        return new ServicioCerrarProcesoDeIngenieriaDeRequisitos(proyectoRepositorioConsulta, proyectoRepositorioComando, personaRepositorioComando, servicioActualizarToken);
    }

    @Bean
    public ServicioConstruirNuevaFase servicioConstruirNuevaFase() {
        return new ServicioConstruirNuevaFase();
    }

    @Bean
    public ServicioSiguienteFaseCierre servicioSiguienteFaseCierre(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos, ServicioCerrarProcesoDeIngenieriaDeRequisitos servicioCerrarProcesoDeIngenieriaDeRequisitos) {
        return new ServicioSiguienteFaseCierre(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos, servicioCerrarProcesoDeIngenieriaDeRequisitos);
    }

    @Bean
    public ServicioSiguienteFaseEjecucion servicioSiguienteFaseEjecucion(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos) {
        return new ServicioSiguienteFaseEjecucion(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos);
    }

    @Bean
    public ServicioSiguienteFaseFactoria servicioSiguienteFaseFactoria(ServicioSiguienteFasePlanificacion servicioSiguienteFasePlanificacion, ServicioSiguienteFaseEjecucion servicioSiguienteFaseEjecucion, ServicioSiguienteFaseMonitoreoYControl servicioSiguienteFaseMonitoreoYControl, ServicioSiguienteFaseCierre servicioSiguienteFaseCierre) {
        return new ServicioSiguienteFaseFactoria(servicioSiguienteFasePlanificacion, servicioSiguienteFaseEjecucion, servicioSiguienteFaseMonitoreoYControl, servicioSiguienteFaseCierre);
    }

    @Bean
    public ServicioSiguienteFaseMonitoreoYControl servicioSiguienteFaseMonitoreoYControl(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos) {
        return new ServicioSiguienteFaseMonitoreoYControl(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos);
    }

    @Bean
    public ServicioSiguienteFasePlanificacion servicioSiguienteFasePlanificacion(RequisitoRepositorioComando requisitoRepositorioComando, ServicioConstruirNuevaFase servicioConstruirNuevaFase, FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ServicioGuardarRequisitos servicioGuardarRequisitos) {
        return new ServicioSiguienteFasePlanificacion(requisitoRepositorioComando, servicioConstruirNuevaFase, faseRepositorioComando, faseRepositorioConsulta, servicioGuardarRequisitos);
    }

    @Bean
    public ServicioConsultarEtapaPorID servicioConsultarFasePorID(FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioConsultarEtapaPorID(faseRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarFasesPorProyectoID servicioConsultarFasesPorProyectoID(FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioConsultarFasesPorProyectoID(faseRepositorioConsulta);
    }

    @Bean
    public ServicioGuardarFase servicioGuardarFase(FaseRepositorioComando faseRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta) {
        return new ServicioGuardarFase(faseRepositorioComando, faseRepositorioConsulta, proyectoRepositorioConsulta);
    }
}