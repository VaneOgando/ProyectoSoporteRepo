package com.inventario.spring.service;

import com.inventario.jpa.data.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import java.util.List;
import java.util.Objects;

@Component
public class LdapServicio {

	/*ATRIBUTOS*/
	@Autowired
	private LdapTemplate ldapTemplate;


	/*METODOS*/
	public boolean autenticarUsuarioSoporte(String usuario, String contrasenia){

		boolean autenticado;

		Filter filter = new EqualsFilter("sAMAccountName", usuario);
		autenticado = ldapTemplate.authenticate("", filter.encode(), contrasenia);

		return autenticado;
	}


	public List<Object> obtenerTodosUsuarios() {

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "user"));

		return ldapTemplate.search("", filter.encode(),
				new AttributesMapper() {
					@Override
					public Object mapFromAttributes(javax.naming.directory.Attributes attributes) throws NamingException {

						Object usuario = (Object) new UsuarioEntity( attributes.get("name").get().toString(), attributes.get("sAMAccountName").get().toString());

						return usuario;
					}
				});

	}

	public UsuarioEntity ObtenerUsuarioCompleto(String usuario) {

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "user"));
		filter.and(new EqualsFilter("sAMAccountName", usuario));

		 List<UsuarioEntity> usuarios = ldapTemplate.search("", filter.encode(),
											new AttributesMapper() {
												@Override
												public Object mapFromAttributes(javax.naming.directory.Attributes attributes) throws NamingException {

													UsuarioEntity usuario =  new UsuarioEntity(attributes.get("name").get().toString(), attributes.get("sAMAccountName").get().toString());

													return usuario;
												}
											});

		return usuarios.get(0);

	}








	/*GET & SET*/
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}
}
