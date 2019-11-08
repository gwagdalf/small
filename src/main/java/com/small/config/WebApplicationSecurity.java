package com.small.config;

import org.apache.commons.lang.UnhandledException;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// java config
// spring security에 대한 설정을 하기 위해서 WebSecurityConfigurerAdapter
// 를 상속받는다.
@Configuration
public class WebApplicationSecurity
            extends WebSecurityConfigurerAdapter{

    // 인증 처리를 아예 하지 않게 하고 싶을 경우
    // /css/** , /js/**, /images/** , /webjars/** 등
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources()
                        .atCommonLocations())
                .requestMatchers(
                        new AntPathRequestMatcher("/**.html"));

    }

    // 특정 ROLE을 가진 사용자가 접근할 수 있는 경로를 지정
    // logoute경로를 지정한다던지 대부분의 인증 처리에 대한 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                    .logoutRequestMatcher(
                            new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/").and()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/members/joinform").permitAll()
                    .antMatchers(HttpMethod.POST,
                            "/members/join").permitAll()
                    .antMatchers("/members/welcome").permitAll()
                    .antMatchers("/members/login").permitAll()
                    .antMatchers("/members/**").hasRole("USER")
                    .antMatchers("/api/**").hasRole("USER")
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                .and().headers().frameOptions().disable()
                .and()
                    .csrf().ignoringAntMatchers("/**")// post방식으로 값을 전달할 때 csrf를 무시
                .and()
                .formLogin()
                    .loginProcessingUrl("/members/login")
                    .loginPage("/members/login")
                    .usernameParameter("id")
                    .passwordParameter("password")
        ;
    }
}
