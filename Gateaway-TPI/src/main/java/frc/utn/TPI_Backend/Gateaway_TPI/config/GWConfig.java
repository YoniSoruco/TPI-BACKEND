package frc.utn.TPI_Backend.Gateaway_TPI.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${Gateaway-TPI.url-microservicio-pruebas}") String uriPruebas,
                                        @Value("${Gateaway-TPI.url-microservicio-notificaciones}") String uriNotificaciones,
                                        @Value("${Gateaway-TPI.url-microservicio-vehiculos}") String uriVehiculos)
    {
        return builder.routes()
                // Ruteo al Microservicio de Personas
                .route(p -> p.path("/api/pruebas/**").uri(uriPruebas))
                // Ruteo al Microservicio de Entradas
                .route(p -> p.path("/api/notificaciones/**").uri(uriNotificaciones))

                .route(p -> p.path("/api/vehiculos/**").uri(uriVehiculos))
                .build();

    }

}
