//package comp3095.patriots.securepaymentprocessingsystem.config;
//
//import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class ControllerRedirectMvcConfigurer implements WebMvcConfigurer {
//
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new ControllerInterceptor(userService));
//	}
//}
