package com.dawii.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.dawii.entity.Enlace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {

	private String token;
	private String bearer = "Bearer";
	private String nombre;
	private String username;
	private String rol;
	private List<Enlace> enlaces;

	public JwtDto(String token,String nombre, String username, Collection<? extends GrantedAuthority> authorities,List<Enlace> enlaces) {
		this.token = token;
		this.nombre = nombre;
		this.username = username;
		this.rol = authorities.iterator().next().getAuthority();
		this.enlaces = enlaces;
	}

}
