package com.inventario.spring.service;

import org.springframework.stereotype.Component;

@Component
public class InicioSesionServicio {

	public boolean ValidarUsuarioContrasenia(String usuario, String contrasenia){

		//Conexion a LDAP, validar contraseña d usuario correcta, y usuario del departamento d soporte

		if (usuario.equals("admin") && contrasenia.equals("admin"))
			return true;

		return false;
	}

}
