package it.epicode.gp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



//@ControllerAdvice
public class LanguageExeption extends Exception {
	public LanguageExeption(String msg) {
		super(msg);
	}


}
