package it.epicode.gp.auth.service;

import it.epicode.gp.auth.payload.LoginDto;
import it.epicode.gp.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
