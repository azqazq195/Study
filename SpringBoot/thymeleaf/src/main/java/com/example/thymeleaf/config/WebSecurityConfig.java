package com.example.thymeleaf.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 보드에 대해서 permitall, anyrequest 등으로 인증이 되지 않았기 때문에 로그인 페이지로 이동
		// 윗줄 로그인 없이 이용할 수 있는 페이지
		// 이외 페이지 접근시 로그인 페이지로
		http.csrf().disable().authorizeRequests().antMatchers("/", "/account/register", "/css/**", "/api/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/account/login").permitAll()
				.and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password,enabled " + "from user " + "where username = ?")
				.authoritiesByUsernameQuery(
						"select u.username, r.name " + "from user_role ur inner join user u on ur.user_id = u.id "
								+ "inner join role r on ur.role_id = r.id " + "where u.username = ?");
	}

	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}