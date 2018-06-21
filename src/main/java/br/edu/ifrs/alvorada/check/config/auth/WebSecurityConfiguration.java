package br.edu.ifrs.alvorada.check.config.auth;


import br.edu.ifrs.alvorada.check.service.UserDetailsImplService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsImplService accountUserDetailsService;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/photos/**", "/img/**", "/resources/**", "/public/**", "/dist/**",
				"/db/**", "/test/**", "/css/**", "/js/**", "/static/**", "/json/**");
		//web.ignoring().antMatchers("/db/**", "/webjars/**", "/css/**", "/img/**", "/js/**", "/photos/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
	         .authorizeRequests()
	             .antMatchers("/login**", "/dist/**", "/webjars**", "/db/**", "/").permitAll()
	             .anyRequest().authenticated()
	             .and()
	         .formLogin()
	             .loginPage("/login")
	             .permitAll()
	             .defaultSuccessUrl("/home", true)
	             .and()
	         .logout()
	             .permitAll();
		http.csrf().disable();
	}
}
