/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 2
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 08/11/2020
 * Description: Configuration class to allow H2 console to work with Spring Security.
 * creates an url mapping for the console at /console instead of the usual /h2-console
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}
}
