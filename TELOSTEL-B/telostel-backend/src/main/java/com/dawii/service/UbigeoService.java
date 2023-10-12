package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IUbigeoDAO;
import com.dawii.entity.Ubigeo;

@Service
public class UbigeoService {
	
	@Autowired
	private IUbigeoDAO repo;
	
	public List<String> listarProvincias(){
		return repo.listarProvincias();
	}
	
	public List<Ubigeo> listarDistritos(String provincia){
		return repo.listarDistritos(provincia);
	}
	
}
