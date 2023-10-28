package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawii.entity.Empleado;

public interface IEmpleadoDAO extends JpaRepository<Empleado, Long>{

	//Buscar si existe empleado por celular
	public boolean existsByCelular(String celular);
	//Buscar si existe empleado por DNI
	public boolean existsByDni(String dni);
<<<<<<< Updated upstream
	
    // Buscar si existe empleado por celular excluyendo el ID proporcionado
    boolean existsByCelularAndIdNot(String celular, Long id);

    // Buscar si existe empleado por DNI excluyendo el ID proporcionado
    boolean existsByDniAndIdNot(String dni, Long id);
=======
	//Buscar por username
	@Query("SELECT E FROM Usuario U "
			+ "INNER JOIN Empleado E ON U.empleado=E "
			+ "WHERE U.username = ?1")
	public Empleado findXUsername(String username);
	
>>>>>>> Stashed changes
}
