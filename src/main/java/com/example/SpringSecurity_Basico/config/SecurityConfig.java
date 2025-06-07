package com.example.SpringSecurity_Basico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**para configurar usuarios en memoria o desde aca es el intermediario para acceder bd y traer los usuarios almacendos*/
    @Bean
    public UserDetailsService userDetailsService(){

        //primer usuario en memoria
        UserDetails user = User
                .withUsername("usuario")
                .password("{noop}1234")
                .roles("USER")
                .build();

        //segundo usuario en memoria
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}1234")//se debe enviar encriptada,pero como no esta se le indica con el noop pero, que la contraseña no va estar encriptada
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);//almacendo los usuarios en memoria
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin", "/user").permitAll()//aca le estoy indicando que estos edpoint no tenga seguridad osea que no pida iniciar secccion
                        //.requestMatchers("/admin").hasRole("ADMIN") como tambien le puede definir que al iniciar seccion tiene que tener un rol en especifico
                        //.requestMatchers("/user").hasRole("USER")
                        .anyRequest().authenticated())//aca es todo lo contrario,le estamos indicando que los otros endopints se me pida inicio de secion
                .formLogin(from -> from.permitAll());//le estamos indicando que utilice el formulario por defecto que tiene springSecurity, como tambien le podemos decir de donde peude sacar el formulario con un html de nosotros
        return httpSecurity.build();
    }
}
