package comp3095.patriots.securepaymentprocessingsystem.config;

import comp3095.patriots.securepaymentprocessingsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class ControllerInterceptor extends HandlerInterceptorAdapter {

	private final UserService userService;
	UrlPathHelper urlPathHelper = new UrlPathHelper();


	public ControllerInterceptor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		List<String> ignorePaths = Arrays.asList("/login", "/logout", "/register");
		String path = urlPathHelper.getLookupPathForRequest(request);

		if (ignorePaths.contains(path)) {
			return true;
		}
 		else if (userService.getAuthenticatedUser() == null) {
		    System.out.println("user is NOT authenticated");

			String encodedRedirectURL = response.encodeRedirectURL("/logout");
			response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
			response.setHeader("Location", encodedRedirectURL);

			return false;
		} else {
		    System.out.println("user is authenticated");
		    System.out.println(userService.getAuthenticatedUser().getEmail());

			return true;
		}
	}
}
