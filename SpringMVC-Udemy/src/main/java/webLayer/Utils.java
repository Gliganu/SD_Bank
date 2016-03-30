package webLayer;

import org.springframework.ui.Model;

public class Utils {

	private static String MESSAGE_PAGE = "message";
	private static String HOME_PAGE = "home";

	public static String showMessagePage(Model model, String message) {
		model.addAttribute("message", message);

		return HOME_PAGE;
	}

}
