package udemy.blogpost.demo.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import udemy.blogpost.demo.service.CharuCustomUserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	DataSource dSource;

	@Autowired 
	CharuCustomUserDetailsService uService;
	
	// We need to override configure(HttpSecurity http) for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("commentapi/v1/getcommentsforpostId").hasAnyRole("ADMIN","USER")
			.antMatchers("postapi/v1/allposts").hasRole("ADMIN")
			.anyRequest().authenticated() 
			.and().formLogin().and().httpBasic();
	}
	//.antMatchers("/**").hasRole("ADMIN")  //, "ADMIN"
	
//	//Authorization
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http.httpBasic().and().authorizeRequests()
//	        .anyRequest().authenticated()
//	        .and()
//	        .formLogin().permitAll()
//	        .and()
//	        .logout().permitAll();     
//	}

	
	//Authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dSource)
//				.usersByUsernameQuery("select username, password, enabled from users where username=?")
//				.authoritiesByUsernameQuery("select username, role from users where username=?");
//	}

	
//	@Bean
//	public AuthenticationProvider authProvider() {
//		DaoAuthenticationProvider dProvider = new DaoAuthenticationProvider();
//		dProvider.setUserDetailsService(uService);
//		dProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		return dProvider;
//	}
	// use above written code or below one
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uService).passwordEncoder(new BCryptPasswordEncoder());//NoOpPasswordEncoder.getInstance()
	}
	
//In memory authentication	

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("akhil").password("charu").roles("USER").and().withUser("charu").password("akhil").roles("ADMIN");
//	}
//	
//  PasswordEncoder	
//	@Bean
//	public PasswordEncoder passenc() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	
 //BcryptPasswordEncoder	
//	@Bean
//	public PasswordEncoder passenc() {
//		return new BCryptPasswordEncoder();
//	}	
}
