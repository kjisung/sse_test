package com.example.springsse;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  /**
   * 1. JWT 없이 호출 하는 경우 2. JWT 형식이 이상하거나 만료된 토큰의 경우 3. JWT 토큰으로 호출하였으나 권한이 없는경우
   * 
   * 1,2번의 경우 EntryPoint로 받아 처리, 3번의 경우 AccessDenied를 상속받아 해결 둘다 강제 에러를 던질 수 있는 exception경로를 만들어
   * 제공한다. 둘다 커스텀 Advice가 잡을 수 없다. (필터라서)
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
        .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
        // jwt token으로 인증할것이므로 세션필요없으므로 생성안함.
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
        .antMatchers("/subscribe/**").permitAll() // 테스트용
        .antMatchers("/noty/**").permitAll() // swagger
        .anyRequest().hasRole("USER").and(); // 그외 나머지 요청은 모두 인증된 회원만 접근 가능


    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().antMatchers("/api-docs", "/api-docs/**", "/swagger-resources/**",
        "/swagger-ui/**", "/webjars/**", "/swagger/**", "/swagger-ui.html");
  }
}
