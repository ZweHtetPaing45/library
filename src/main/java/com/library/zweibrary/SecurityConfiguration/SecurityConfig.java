package com.library.zweibrary.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.library.zweibrary.AdminService.AdminService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AdminService admSer;

    public SecurityConfig(AdminService admSer) {
        this.admSer = admSer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return admSer;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(admSer);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return daoProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> {
                    formLogin.loginPage("/login").permitAll();
                    formLogin.loginProcessingUrl("/login").permitAll();
                    formLogin.defaultSuccessUrl("/admin_tho", true).permitAll();

                })
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/", "/eight", "/five", "/four", "/login", "/nine", "/seven", "/six", "/ten", "/tho", "/three", "/two",
                            "/css/**", "/fontawesome/css/**", "/fontawsome/webfonts/**", "/fonts/arimo/**", "/images/**", "/js/**", "/sass/common/**", "/sass/components/**",
                            "/sass/mixin/**", "/sass/pages/**", "/sass/variable/**", "/sass/**", "/registry", "/admin_tho", "/pdf/view/**", "/pdf/delete/**", "/admin_two",
                            "/pdf/two_view/**", "/pdf/two_delete/**", "/admin_three","/pdf/three_view/**", "/pdf/three_delete/**", "/admin_four","/pdf/four_view/**",
                            "/pdf/four_delete/**", "/admin_five","/pdf/five_view/**", "/pdf/five_delete/**", "/admin_six","/pdf/six_view/**", "/pdf/six_delete/**", "/admin_seven",
                            "/pdf/seven_view/**", "/pdf/seven_delete/**", "/admin_eight","/pdf/eight_view/**", "/pdf/eight_delete/**", "/admin_nine","/pdf/nine_view/**", "/pdf/nine_delete/**", 
                            "/admin_ten", "/pdf/ten_view/**", "/pdf/ten_delete/**","/pdf/download/**","/pdf/two_download/**","/pdf/three_download/**","/pdf/four_download/**",
                            "/pdf/five_download/**","/pdf/six_download/**","/pdf/seven_download/**","/pdf/eight_download/**","/pdf/nine_download/**","/pdf/ten_download/**"
                    ).permitAll();
                    // registry.requestMatchers("/admin_tho").authenticated();
                })
                .build();
    }
}
