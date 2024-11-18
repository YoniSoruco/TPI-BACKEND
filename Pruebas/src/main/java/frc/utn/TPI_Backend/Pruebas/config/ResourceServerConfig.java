package frc.utn.TPI_Backend.Pruebas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize

                .requestMatchers("/publico/**")
                .permitAll()

                .requestMatchers("/protegido-vehiculo/**")
                .hasAnyRole("VEHICULO","EMPLEADO", "ADMIN")
                .requestMatchers("/protegido-empleados/**")
                .hasAnyRole("EMPLEADO", "ADMIN")
                .requestMatchers("/protegido-administradores/**")
                .hasRole("ADMIN")


                .anyRequest()
                .authenticated()


        ).oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );
        return http.build();

    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
            grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities"); // Analiza el claim 'authorities'
            grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // Prefijo necesario para Spring Security

            // Convierte las autoridades del claim 'authorities'
            var authorities = grantedAuthoritiesConverter.convert(jwt);

            // Filtra y asegura que solo se incluyan los roles relevantes
            return authorities.stream()
                    .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                    .toList();
        });

        return jwtAuthenticationConverter;
    }
}
