package webLayer;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private String HOME_PAGE = "home";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage(Model model, Principal principal) {
		return HOME_PAGE;
	}

}
