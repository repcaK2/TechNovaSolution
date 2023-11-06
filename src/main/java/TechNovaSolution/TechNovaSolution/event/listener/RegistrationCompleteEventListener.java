package TechNovaSolution.TechNovaSolution.event.listener;

import TechNovaSolution.TechNovaSolution.event.RegistrationCompleteEvent;
import TechNovaSolution.TechNovaSolution.user.User;
import TechNovaSolution.TechNovaSolution.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

	private final UserService userService;
	private User theUser;

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		User theUser = event.getUser();
		String verificationToken = UUID.randomUUID().toString();
		userService.saveUserVerificationToken(theUser, verificationToken);
		String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
		log.info("Click the link to verify your registration :  {}", url);
	}
}
