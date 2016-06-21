package com.inventario.util.comun;

import com.inventario.jpa.data.UsuarioEntity;
import com.inventario.spring.service.LdapServicio;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;

@ManagedBean
@SessionScoped
public class datosSesion {

	/*ATRIBUTOS*/
	private UsuarioEntity usuario;
	//private Date fechaActual;


	/*GET & SET*/

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
}