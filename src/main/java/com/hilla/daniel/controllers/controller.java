package com.hilla.daniel.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;

@Controller
public class controller {

	private boolean access = false;

	@ModelAttribute("access")
	public boolean getAccess() {
		return this.access;
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/process")
	public String process(@RequestParam(value = "code") String code, RedirectAttributes redirectAttributes) {
		if (code.equals("bushido")) {
			this.access = true;
			return "redirect:/code";
		} else {
			redirectAttributes.addFlashAttribute("errors", "You must train harder!");
			return "redirect:/";
		}
	}

	@RequestMapping("/code")
	public String code(RedirectAttributes redirectAttributes) {
		if (this.getAccess()) {
			return "code";
		} else {
			redirectAttributes.addFlashAttribute("errors", "You must train harder!");
			return "redirect:/";
		}
	}
}
