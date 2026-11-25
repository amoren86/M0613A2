package cat.institutmarianao.medicine.stock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// Defines HTTP security rules, custom login page, and logout behavior
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				// Protected routes: only users with ROLE_ADMIN can add medicines or modify
				// stock
				.requestMatchers("/medicines/add", "/medicines/stock-movement/**").hasRole("ADMIN")
				// Public routes: home page, catalog, detail view, login page, and static assets
				.requestMatchers("/", "/medicines", "/medicines/all", "/medicines/medicine", "/login", "/css/**",
						"/js/**")
				.permitAll().anyRequest().permitAll())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
						.defaultSuccessUrl("/medicines/add", true).failureUrl("/login?error=true").permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true").permitAll());

		return http.build();
	}

	// Password encoder using BCrypt hashing algorithm
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// In-memory user store for testing authentication and roles
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.builder().username("admin").password(encoder.encode("admin123")).roles("ADMIN")
				.build();

		UserDetails user = User.builder().username("pharmacist").password(encoder.encode("user123")).roles("USER")
				.build();

		return new InMemoryUserDetailsManager(admin, user);
	}
}