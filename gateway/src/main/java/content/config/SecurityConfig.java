package content.config;
import content.security.jwt.JwtFilter;
import content.security.AuthorityConstant;
import content.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http ) throws Exception {
        http.csrf( AbstractHttpConfigurer::disable );
        http.sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http.securityMatcher("/**" )
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()
                        .requestMatchers(HttpMethod.POST,"/admin").hasAuthority( AuthorityConstant._ADMIN )
                        .requestMatchers(HttpMethod.POST,"/maintenance").hasAuthority( AuthorityConstant._ADMIN )
                        .requestMatchers("/scooter/**").hasAuthority( AuthorityConstant._USER )
                        .requestMatchers("/ticket/**").hasAuthority( AuthorityConstant._USER )
                        //.requestMatchers("/user/**").hasAuthority( AuthorityConstant._USER )
                        .requestMatchers( "/trip/**").hasAuthority( AuthorityConstant._USER )
                        .requestMatchers( "/pause/**").hasAuthority( AuthorityConstant._USER )
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}