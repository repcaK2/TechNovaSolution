package TechNovaSolution.TechNovaSolution.registration;

import TechNovaSolution.TechNovaSolution.event.RegistrationCompleteEvent;
import TechNovaSolution.TechNovaSolution.registration.token.VerificationTokenRepository;
import TechNovaSolution.TechNovaSolution.user.User;
import TechNovaSolution.TechNovaSolution.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

	private final UserService userService;
	private final ApplicationEventPublisher publisher;
	private final VerificationTokenRepository tokenRepository;

	@PostMapping
	public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
		User user = userService.registerUser(registrationRequest);
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "Success!  Please, check your email for to complete your registration";
	}

	public String applicationUrl(HttpServletRequest request) {
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
}
