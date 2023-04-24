package it.epicode.gp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.gp.exeption.LanguageExeption;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/info")
public class InfoController {

	@GetMapping
	public @ResponseBody String getInfoPaga() {
		return ("Info pag");
	}
	@GetMapping("/pr")
	public String prova () {
		return"infoPage";
	}
	@GetMapping("/{lingua}")
	public ModelAndView infoItaliano (@PathVariable String lingua) throws LanguageExeption {
		ModelAndView model = new ModelAndView("infoPage");
		if(lingua.equals("it") ) {
			
			model.addObject("in","descriozione in italiano");
		}else if (lingua.equals("eng")) {
			model.addObject("in","descriozione in inglese");
		}else {
			throw new LanguageExeption("lingua non valida");
		}
		return model;
	}
}
