package co.edu.uco.arquisw.infraestructura.configuracion;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.servicio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioRequisito {
    @Bean
    public ServicioActualizarRequisito servicioActualizarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioActualizarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarRequisitoPorID servicioConsultarRequisitoPorID(RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioConsultarRequisitoPorID(requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioConsultarRequisitosPorEtapaID servicioConsultarRequisitosPorEtapaID(RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta) {
        return new ServicioConsultarRequisitosPorEtapaID(requisitoRepositorioConsulta, faseRepositorioConsulta);
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
    public ServicioEliminarRequisito servicioEliminarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta) {
        return new ServicioEliminarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta);
    }

    @Bean
    public ServicioGenerarVersionFinal servicioGenerarVersionFinal(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, ServicioObtenerVersionFinal servicioObtenerVersionFinal) {
        return new ServicioGenerarVersionFinal(requisitoRepositorioComando, requisitoRepositorioConsulta, faseRepositorioConsulta, servicioObtenerVersionFinal);
    }

    @Bean
    public ServicioGuardarRequisito servicioGuardarRequisito(RequisitoRepositorioComando requisitoRepositorioComando, RequisitoRepositorioConsulta requisitoRepositorioConsulta, FaseRepositorioConsulta faseRepositorioConsulta, ServicioObtenerVersionFinal servicioObtenerVersionFinal) {
        return new ServicioGuardarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta, faseRepositorioConsulta, servicioObtenerVersionFinal);
    }

    @Bean
    public ServicioObtenerVersionFinal servicioObtenerVersionFinal() {
        return new ServicioObtenerVersionFinal();
    }
}