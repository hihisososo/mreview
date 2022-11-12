package org.zerock.mreview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.zerock.mreview.security.dto.ClubLoginSuccessHandler;
import org.zerock.mreview.security.service.ClubOAuth2UserDetailsService;
import org.zerock.mreview.security.service.ClubUserDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private ClubUserDetailService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll();
              /*  .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER")
                .antMatchers("/sample/admin").hasRole("ADMIN");*/
        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.formLogin()//인증, 인가 실패할 경우 로그인 화면
                .successHandler(new ClubLoginSuccessHandler(passwordEncoder()));
        http.oauth2Login()//oauth2 로그인 설정
                .successHandler(new ClubLoginSuccessHandler(passwordEncoder()));
        http.logout(); //로그아웃 설정

        http.rememberMe().tokenValiditySeconds(60*60*24*7)
                .userDetailsService(userDetailsService);
        return http.build();
    }


}
