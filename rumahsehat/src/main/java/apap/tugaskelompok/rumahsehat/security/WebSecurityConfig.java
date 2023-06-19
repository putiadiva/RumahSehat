package apap.tugaskelompok.rumahsehat.security;

import apap.tugaskelompok.rumahsehat.config.JwtAuthenticationEntryPoint;
import apap.tugaskelompok.rumahsehat.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Configuration
    @Order(1)
    public class RestApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable()
                    .antMatcher("/api/**").cors()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/v1/authenticate").permitAll()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Configuration
    @Order(2)
    public class UILoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/login-sso", "/validate-ticket", "/resep/view/*").permitAll()
                    .antMatchers("/appointment/viewall", "/appointment/*").hasAnyAuthority("ADMIN", "DOKTER", "PASIEN")
                    .antMatchers("/resep/viewall", "/obat/view-all").hasAnyAuthority("ADMIN", "APOTEKER")
                    .antMatchers("/dokter/*", "/pasien/*", "/apoteker/*").hasAuthority("ADMIN")
                    .antMatchers("/appointment/resep/*").hasAuthority("DOKTER")
                    .antMatchers("/obat/*/update").hasAuthority("APOTEKER")
                    .antMatchers("/dokter/chart/**").hasAnyAuthority("ADMIN")
                    .antMatchers("/resep/confirm/{idResep}").hasAnyAuthority("APOTEKER")
                    .antMatchers("/appointment/confirm/{idApt}").hasAnyAuthority("DOKTER")
                    .antMatchers("/appointment/resep/{idApt}").hasAnyAuthority("DOKTER")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll()
                    .and()
                    .sessionManagement().sessionFixation().newSession().maximumSessions(1);
        }
    }
}