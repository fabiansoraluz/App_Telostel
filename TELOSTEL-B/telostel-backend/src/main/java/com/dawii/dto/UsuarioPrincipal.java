package com.dawii.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dawii.entity.Enlace;
import com.dawii.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails{

	private String nombre;
	private String username;
	private String password;
	private List<? extends GrantedAuthority> authorities;
	private List<Enlace> enlaces;
	
	public static UsuarioPrincipal build(Usuario user,List<Enlace> enlaces) {
		List<? extends GrantedAuthority> authorities = user.getRoles().stream().
				map(rol -> new SimpleGrantedAuthority(rol.getNombre())).toList();
		return new UsuarioPrincipal(user.getEmpleado().getNombre(),user.getUsername(),user.getPassword(),authorities,enlaces);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	private static final long serialVersionUID = 1L;
}
