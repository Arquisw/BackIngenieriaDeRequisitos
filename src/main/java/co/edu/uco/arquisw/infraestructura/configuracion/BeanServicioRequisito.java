package co.edu.uco.arquisw.infraestructura.configuracion;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.servicio.*;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria.ServicioEnviarNotificacionFactoria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioRequisito {
    @Bean
    public ServicioActualizarRequisito servicioActualizarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioActualizarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarVersionesPorEtapaID servicioConsultarVersionesPorEtapaID(RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioConsultarVersionesPorEtapaID(requisitoRepositorioConsulta, faseRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarVersionPorID servicioConsultarVersionPorID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioConsultarVersionPorID(requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioEliminarRequisitoPorID servicioEliminarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioEliminarRequisitoPorID(requisitoRepositorioComando, requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioGenerarVersionFinal servicioGenerarVersionFinal(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, ServicioObtenerRequisitosEtapaAnterior servicioObtenerRequisitosEtapaAnterior, ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria) {
        return new ServicioGenerarVersionFinal(requisitoRepositorioComando, requisitoRepositorioConsulta, faseRepositorioConsulta, seleccionRepositorioConsulta, proyectoRepositorioConsulta, servicioObtenerRequisitosEtapaAnterior, servicioEnviarNotificacionFactoria);
    }

    @Bean
    public ServicioGuardarRequisito servicioGuardarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioGuardarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioGuardarRequisitos servicioGuardarRequisitos(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioGuardarRequisitos(requisitoRepositorioComando, requisitoRepositorioConsulta, faseRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarRequisitosPorVersionID servicioConsultarRequisitosPorVersionID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioConsultarRequisitosPorVersionID(requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioGuardarVersionInicial servicioGuardarVersionInicial(RequisitoRepositorioComando requisitoRepositorioComando, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria) {
        return new ServicioGuardarVersionInicial(requisitoRepositorioComando, faseRepositorioConsulta, seleccionRepositorioConsulta, proyectoRepositorioConsulta, servicioEnviarNotificacionFactoria);
    }

    @Bean
    public ServicioObtenerRequisitosEtapaAnterior servicioObtenerRequisitosEtapaAnterior(RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, ServicioValidarSiRequisitosSonIguales servicioValidarSiRequisitosSonIguales) {
        return new ServicioObtenerRequisitosEtapaAnterior(requisitoRepositorioConsulta, faseRepositorioConsulta, servicioValidarSiRequisitosSonIguales);
    }

    @Bean
    public ServicioRechazarVersionPorID servicioRechazarVersionPorID(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, SeleccionRepositorioConsulta seleccionRepositorioConsulta, ProyectoRepositorioConsulta proyectoRepositorioConsulta, ServicioObtenerRequisitosEtapaAnterior servicioObtenerRequisitosEtapaAnterior, ServicioGuardarRequisitos servicioGuardarRequisitos, ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria) {
        return new ServicioRechazarVersionPorID(requisitoRepositorioComando, requisitoRepositorioConsulta, faseRepositorioConsulta, seleccionRepositorioConsulta, proyectoRepositorioConsulta, servicioObtenerRequisitosEtapaAnterior, servicioGuardarRequisitos, servicioEnviarNotificacionFactoria);
    }

    @Bean
    public ServicioValidarSiRequisitosSonIguales servicioValidarSiRequisitosSonIguales(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioValidarSiRequisitosSonIguales(requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioTerminoProcesoIngenieriaRequisitosPorProyectoID servicioTerminoProcesoIngenieriaRequisitosPorProyectoID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioTerminoProcesoIngenieriaRequisitosPorProyectoID(requisitoRepositorioConsulta);
    }
}