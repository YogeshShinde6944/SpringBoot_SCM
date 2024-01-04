package com.SCM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SCM.dao.UserRepository;
import com.SCM.entities.Contact;
import com.SCM.entities.User;
import com.SCM.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		User user = new User();
		user.setName("yogesh");
		user.setEmail("yogesh@gmail.com");

		Contact contact = new Contact();
		user.getContacts().add(contact);

		userRepository.save(user);
		return "working";
	}

	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("title", "Home- smart contact manager");
		return "home";
	}

	@GetMapping("/about")
	public String getAboutPage(Model model) {
		model.addAttribute("title", "About- smart contact manager");
		return "about";
	}

	@GetMapping("/signup")
	public String getSignupPage(Model model, HttpSession session) {
		model.addAttribute("title", "Register- smart contact manager");
		model.addAttribute("user", new User());

		session.removeAttribute("message");

		return "signup";
	}

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String getDoRegister(@Valid @ModelAttribute("user") User user, BindingResult result1,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {

		try {

			if (!agreement) {
				System.out.println("you have not agreed the terms and condition");
				throw new Exception("you have not agreed the terms and condition");
			}

			if (result1.hasErrors()) {
				System.out.println("Error : " + result1.toString());
				model.addAttribute("user", user);
				return "signup";
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			System.out.println("agreement : " + agreement);
			System.out.println("User : " + user);

			User result = userRepository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("successfully registered !!", "alert-success"));

			return "signup";

		} catch (Exception e) {
			System.out.println("in catch block");
			e.printStackTrace();
			session.setAttribute("something went wrong !!" + e.getMessage(), "alert-danger");
		}
		return "signup";
	}

}
