package it.epicode.gp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LanguageExpetionHandle {
	@ExceptionHandler(LanguageExeption.class)
	ResponseEntity<String> langueageEx(LanguageExeption e) {
		return new ResponseEntity<String>(e.getMessage() + " langueageEx!", HttpStatus.NOT_FOUND);
	}
}
