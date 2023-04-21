package it.epicode.gp.auth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.epicode.gp.auth.payload.LoginDto;
import it.epicode.gp.auth.payload.RegisterDto;
import it.epicode.gp.auth.security.JwtTokenProvider;
import it.epicode.gp.enums.RoleType;
import it.epicode.gp.exeption.MyAPIException;
import it.epicode.gp.model.Role;
import it.epicode.gp.model.Utente;
import it.epicode.gp.repo.RoleRepository;
import it.epicode.gp.repo.UtenteDaoRepo;

@Service
public class AuthServiceImpl implements AuthService {
	private AuthenticationManager authenticationManager;
	private UtenteDaoRepo userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UtenteDaoRepo userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {

		// add check for username exists in database
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
		}

		// add check for email exists in database
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
		}

		Utente user = new Utente();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

		Set<Role> roles = new HashSet<>();

		if (registerDto.getRoles() != null) {
			registerDto.getRoles().forEach(role -> {
				Role userRole = roleRepository.findByRoleType(getRole(role)).get();
				roles.add(userRole);
			});
		} else {
			Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER).get();
			roles.add(userRole);
		}

		user.setRoles(roles);
		System.out.println(user);
		userRepository.save(user);

		return "User registered successfully!.";
	}

	public RoleType getRole(String role) {
		if (role.equals("ROLE_ADMIN"))
			return RoleType.ROLE_ADMIN;
		else if (role.equals("ROLE_MODERATOR"))
			return RoleType.ROLE_MODERATOR;
		else
			return RoleType.ROLE_USER;
	}

}
