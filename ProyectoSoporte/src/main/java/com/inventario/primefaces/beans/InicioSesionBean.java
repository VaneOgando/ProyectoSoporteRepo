package com.inventario.primefaces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.inventario.jpa.data.UsuarioEntity;
import com.inventario.util.comun.Constantes;

import com.inventario.spring.service.LdapServicio;
import com.inventario.util.comun.datosSesion;
import org.primefaces.context.RequestContext;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;

@ManagedBean
@ViewScoped
public class InicioSesionBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{ldapServicio}")
	private LdapServicio ldapServicio;

	private String user;
	private String pass;

	private boolean respuesta;

    private LdapTemplate ldapTemplate;
    private datosSesion sesion;

	/*METODOS*/
	public String bt_ingresar_action() {

		respuesta = ldapServicio.autenticarUsuarioSoporte(user, pass);

		if (respuesta == true) {

            UsuarioEntity prueba = ldapServicio.ObtenerUsuarioCompleto(user);

            sesion.setUsuario(prueba);
			return "consultarInventario";

		}else {
			this.user = "";
			this.pass = "";

			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR! Usuario y/o cotraseña invalido", null));
			RequestContext.getCurrentInstance().update("mensajesError");
		}
		return "";
	}


//	public String authenticateUser() throws NamingException {
//
//		Hashtable<String, String> env = new Hashtable<String, String>();
//
//		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//		env.put(Context.PROVIDER_URL, "ldap://infra.tcs.local/DC=tcs,DC=local" );
//
//		// Needed for the Bind (User Authorized to Query the LDAP server)
//		env.put(Context.SECURITY_AUTHENTICATION, "simple");
//		env.put(Context.SECURITY_PRINCIPAL, "incidencias");
//		env.put(Context.SECURITY_CREDENTIALS, "sDuZv5TfCMOgQ5");
//
//		DirContext ctx;
//
//		try {
//			ctx = new InitialDirContext(env);
//		} catch (NamingException e) {
//			throw new RuntimeException(e);
//		}
//
//		NamingEnumeration<SearchResult> results = null;
//
//		try {
//
//			SearchControls controls = new SearchControls();
//			controls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Search Entire Subtree
//			controls.setCountLimit(1);   //Sets the maximum number of entries to be returned as a result of the search
//			controls.setTimeLimit(5000); // Sets the time limit of these SearchControls in milliseconds
//
//			String searchString = "(&(objectCategory=user)(sAMAccountName=" + user + "))";
//
//			results = ctx.search("", searchString, controls);
//
//			if (results.hasMore()) {
//
//				SearchResult result = results.next();
//				Attributes attrs = result.getAttributes();
//				javax.naming.directory.Attribute dnAttr = attrs.get("distinguishedName");
//				String dn = dnAttr.get().toString();
//
//				// User Exists, Validate the Password
//
//				env.put(Context.SECURITY_PRINCIPAL, dn);
//				env.put(Context.SECURITY_CREDENTIALS, pass);
//
//				new InitialDirContext(env); // Exception will be thrown on Invalid case
//				return "consultarInventario.xhtml";
//			}
//			else
//				return "";
//
//		} catch (AuthenticationException e) { // Invalid Login
//			return "";
//		} catch (NameNotFoundException e) { // The base context was not found.
//
//			return "";
//		} catch (NamingException e) {
//			throw new RuntimeException(e);
//		} finally {
//
//			if (results != null) {
//				try { results.close(); } catch (Exception e) { /* Do Nothing */ }
//			}
//
//			if (ctx != null) {
//				try { ctx.close(); } catch (Exception e) { /* Do Nothing */ }
//			}
//		}
//	}


	/*GET & SET*/
	public LdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(LdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

    public LdapTemplate getLdapTemplate() {
        return ldapTemplate;
    }

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public datosSesion getSesion() {
        return sesion;
    }

    public void setSesion(datosSesion sesion) {
        this.sesion = sesion;
    }
}