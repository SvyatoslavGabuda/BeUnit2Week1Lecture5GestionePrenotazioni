package it.epicode.gp.auth.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import it.epicode.gp.model.Utente;
import it.epicode.gp.repo.UtenteDaoRepo;
@Service
public class CustomUserDetailsService implements UserDetailsService{
@Autowired
UtenteDaoRepo uRepo;
@Override
public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
      Utente user = uRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
             .orElseThrow(() ->
                     new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

    Set<GrantedAuthority> authorities = user
            .getRoles()
            .stream()
            .map((role) -> new SimpleGrantedAuthority(role.getRoleType().toString())).collect(Collectors.toSet());

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            authorities);
//    return new org.springframework.security.core.userdetails.User(user.getEmail(),
//    		user.getPassword(),
//    		authorities);
}
}
