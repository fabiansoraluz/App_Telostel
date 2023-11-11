package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IUbigeoDAO;
import com.dawii.entity.Distrito;

@Service
public class UbigeoService {
	
	@Autowired
	private IUbigeoDAO repo;
	
	public List<String> listarDep() {
		return repo.listarDepartamentos();
	}

	public List<String> listarProvXDep(String departamento) {
		return repo.listarProvinciasXDepartamento(departamento);
	}

	public List<Distrito> listarDisXProv(String provincia) {
		return repo.listarDistritosXProvincia(provincia);
	}
}
