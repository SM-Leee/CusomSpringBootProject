package com.example.springtest.security.config;

import com.example.springtest.security.filter.CorsFilter;
import com.example.springtest.security.jwt.JwtAccessDeniedHandler;
import com.example.springtest.security.jwt.JwtAuthenticationEntryPoint;
import com.example.springtest.security.jwt.JwtSecurityConfig;
import com.example.springtest.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	// private final JwtFilter jwtFilter;
	// private final JwtExceptionFilter jwtExceptionFilter;

	public SecurityConfig(
		TokenProvider tokenProvider,
		CorsFilter corsFilter,
		JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
		JwtAccessDeniedHandler jwtAccessDeniedHandler
		// JwtFilter jwtFilter
		// JwtExceptionFilter jwtExceptionFilter

	){
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
		// this.jwtFilter = jwtFilter;
		// this.jwtExceptionFilter = jwtExceptionFilter;
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/**
	 * 	Spring-Security 앞단 설정을 할 수 있다.
	 * */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// resources 모든 접근을 허용하는 설정을 해버리면 HyypSecurity 설정한
		// ADMIN 권한을 가진 사용자만 resources 접근가능한 설정을 무시해버린다.
		web.ignoring().antMatchers("/resources/**");
	}
	/** HttpSecurity 설정
	 * Spring-Security의 설정을 할 수 있다.
	 * 1. 리소스(URL) 접근 권한 설정
	 * 2. 인증 전체 흐름에 필요한 Login, Logout 페이지 인증완료 후 페이지 인증 실패 시 이동페이지 등등 설정
	 * 3. 인증 로직을 커스텀하기위한 커스텀 필터설정
	 * 4. 기타 crsf, 강제 https 호출 등등 거의 모든 Spring-Security의 설정
	 * */
	@Override
    protected void configure(HttpSecurity http) throws Exception{

        http
			.cors().configurationSource(corsConfigurationSource())    // rest api이므로 csrf 보안이 필요없으므로 disable처리.
			.and().csrf().disable()
				//crsf(Cross Site Request Forgery) : 사이즈간 위조 요청, 즉 정상적인 사용자가 의도치 않은 위조요청을 보내는것을 의미
 			.httpBasic().disable();


	 	http.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // jwt token으로 인증하므로 stateless 하도록 처리.

 			// .and()
		// antMatchers : 특정 리소스에 대해서 권한을 설정한다.
		// permitAll : 리소스의 접근을 인증절차 없이 허용한다는 의미.
		// hasAnyRole : 모든 URL은 인증 후 해당 권한을 가진 사용자만 접근을 허용한다는 의미.
		//anyRequest : 설정한 리소스를 제외하고 그외 나머지 리소스들은 무조건 인증을 완료해야한다는 의미.
		http.authorizeRequests()
			// .antMatchers("/board/create").hasRole("USER")  // 인증권한이 필요한 페이지.
			// .antMatchers("/api/v1/board/edit").hasRole("USER")  // 인증권한이 필요한 페이지.
			.antMatchers("/login").permitAll();
			//.antMatchers("/comp").permitAll();
			//.antMatchers("/api/v1/login").permitAll()
			//.antMatchers("/api/v1/join").permitAll();
			// .antMatchers("/api/v1/board/edit").permitAll();

		http
			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			// .addFilterBefore(usernamepass, JwtFilter.class)
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler);
			// .expressionHanling
			// .antMatchers("/api/").permitAll();
			// .antMatchers("/").permitAll()
			// .anyRequest().authenticated();     // 나머지 모든 요청 불허  ( 생략 가능 )

		http.apply(new JwtSecurityConfig(tokenProvider));

		// http.addFilter(jwtau)

	}
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
	}
}
