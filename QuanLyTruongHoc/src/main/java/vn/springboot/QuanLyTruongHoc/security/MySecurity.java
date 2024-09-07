package vn.springboot.QuanLyTruongHoc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.utils.CustomLoggingSuccessHandler;
import vn.springboot.QuanLyTruongHoc.utils.CustomLogoutSuccessHandler;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MySecurity {
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomLoggingSuccessHandler customLoggingSuccessHandler;

    @Autowired
    public MySecurity(CustomLogoutSuccessHandler customLogoutSuccessHandler, CustomLoggingSuccessHandler customLoggingSuccessHandler){
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.customLoggingSuccessHandler = customLoggingSuccessHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer -> configurer.requestMatchers("/image/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                form -> form.loginPage("/showLoginPage")
                                 .loginProcessingUrl("/authenticateTheUser")
                                  .successHandler(customLoggingSuccessHandler)
                                    .permitAll()
        ).logout(
                logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessHandler(customLogoutSuccessHandler)
                                .permitAll()
        )
         .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
