package com.dawii.controller;

<<<<<<< Updated upstream
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.entity.Empleado;
import com.dawii.service.EmpleadoService;
<<<<<<< Updated upstream
import com.dawii.serviceImp.EmpleadoServiceImp;
import com.dawii.utils.Mensaje;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleado")

@CrossOrigin(originPatterns = "*")
public class EmpleadoController {
	
    @Autowired
    private EmpleadoServiceImp empleadoServiceImp;
    
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result) {
        // VALIDACIONES
        if (result.hasErrors()) {
            return new ResponseEntity<Mensaje>(new Mensaje("Error de validación"), HttpStatus.BAD_REQUEST);
        }

        // Validar si el DNI ya está registrado
        if (empleadoService.existeXDni(empleado.getDni())) {
            return new ResponseEntity<Mensaje>(new Mensaje("El DNI ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        }

        // Validar si el celular ya está registrado
        if (empleadoService.existeXCelular(empleado.getCelular())) {
            return new ResponseEntity<Mensaje>(new Mensaje("El celular ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        }

        // Resto de las validaciones según tus necesidades

        // Registramos al empleado
        Empleado empleadoRegistrado = empleadoServiceImp.registrar(empleado);

        return new ResponseEntity<Empleado>(empleadoRegistrado, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result) {
        // VALIDACIONES
        if (result.hasErrors()) {
            return new ResponseEntity<Mensaje>(new Mensaje("Error de validación"), HttpStatus.BAD_REQUEST);
        }

        // Verificar si el empleado existe
        Empleado empleadoExistente = empleadoServiceImp.buscarPorId(empleado.getId());
        if (empleadoExistente == null) {
            return new ResponseEntity<Mensaje>(new Mensaje("Empleado no encontrado"), HttpStatus.NOT_FOUND);
        }

        // Validar si el DNI ya está registrado para otro empleado
        if (empleadoService.existeOtroXDni(empleado.getId(), empleado.getDni())) {
            return new ResponseEntity<Mensaje>(new Mensaje("El DNI ya está registrado para otro empleado"), HttpStatus.BAD_REQUEST);
        }

        // Validar si el celular ya está registrado para otro empleado
        if (empleadoService.existeOtroXCelular(empleado.getId(), empleado.getCelular())) {
            return new ResponseEntity<Mensaje>(new Mensaje("El celular ya está registrado para otro empleado"), HttpStatus.BAD_REQUEST);
        }

        // Resto de las validaciones según tus necesidades

        // Actualizar al empleado
        Empleado empleadoActualizado = empleadoServiceImp.actualizar(empleado);

        return new ResponseEntity<Empleado>(empleadoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        // Validar si el empleado existe
        Empleado empleadoExistente = empleadoServiceImp.buscarPorId(id);
        if (empleadoExistente == null) {
            return new ResponseEntity<Mensaje>(new Mensaje("Empleado no encontrado"), HttpStatus.NOT_FOUND);
        }

        // Resto de las validaciones según tus necesidades

        // Eliminar al empleado
        empleadoServiceImp.eliminarPorId(id);

        return new ResponseEntity<Mensaje>(new Mensaje("Empleado eliminado exitosamente"), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = empleadoServiceImp.listarTodos();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarEmpleadoPorId(@PathVariable Long id) {
        Empleado empleado = empleadoServiceImp.buscarPorId(id);
        if (empleado != null) {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } else {
            return new ResponseEntity<Mensaje>(new Mensaje("Empleado no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

=======
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/empleado")
@CrossOrigin("http://localhost:4200")
public class EmpleadoController {

	@Autowired
	private EmpleadoService SEmpleado;
	
	@GetMapping("/username/{username}")
	public ResponseEntity<?> buscarXUsername(@PathVariable String username){
		Empleado bean = SEmpleado.buscarXUsername(username);
		if(bean != null) {
			return new ResponseEntity<Empleado>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Empleado no encontrado"),HttpStatus.BAD_REQUEST);
	}
	
>>>>>>> Stashed changes
}
