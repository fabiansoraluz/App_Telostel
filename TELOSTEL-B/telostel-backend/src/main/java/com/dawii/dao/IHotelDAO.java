package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Hotel;

public interface IHotelDAO extends JpaRepository<Hotel,Long>{
	
}
