package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.UserRole;
import com.unla.Grupo16OO22020.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unla.Grupo16OO22020.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}
	
	private User buildUser(com.unla.Grupo16OO22020.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}
	
	//private User traerUuario()
	
	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	public Authentication auth () {
		 return SecurityContextHolder.getContext().getAuthentication();
	}
	public String mostrarUsuario() {
		Authentication authentication = this.auth();		
		org.springframework.security.core.userdetails.User a = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();    
	    
		 return a.getUsername();
	}
	
	public Empleado traerEmpleadoLogueado() {
		Authentication authentication = this.auth();
		org.springframework.security.core.userdetails.User a = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
	    return userRepository.findByUsername(a.getUsername()).getEmpleado();
	}	
	
	public String traerRol() {
		Authentication authentication = this.auth();
		org.springframework.security.core.userdetails.User a = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
	    String rol = a.getAuthorities().iterator().next().toString();	    
		return rol;
	}
	
}
