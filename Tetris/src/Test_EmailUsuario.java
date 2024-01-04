import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ventanas.Ventana_SignIn;

class Test_EmailUsuario {

	 @Test
	    public void testValidarCorreo() {
	        // Casos de correos válidos
	        assertTrue(Ventana_SignIn.validarCorreo("usuario@dominio.com"));
	        assertTrue(Ventana_SignIn.validarCorreo("nombre.apellido@dominio.co.uk"));

	        // Casos de correos inválidos
	        assertFalse(Ventana_SignIn.validarCorreo("")); // Correo vacío
	        assertFalse(Ventana_SignIn.validarCorreo("correoInvalido")); // Sin @
	        assertFalse(Ventana_SignIn.validarCorreo("usuario@dominio")); // Sin extensión (com, org, etc.)
	        assertFalse(Ventana_SignIn.validarCorreo("correo espacios@dominio.com")); // Espacios en el correo
	        assertFalse(Ventana_SignIn.validarCorreo("@dominio.com")); // Sin nombre de usuario
	        assertFalse(Ventana_SignIn.validarCorreo("usuario@dominio@com")); // Múltiples @
	    }

	    @Test
	    public void testConNumeros() {
	        assertTrue(Ventana_SignIn.validarCorreo("nombre123@dominio.com")); // Con números
	        assertTrue(Ventana_SignIn.validarCorreo("23@gmail.com")); // Con números, pero al inicio del nombre de usuario
	    }

	    @Test
	    public void testConCaracteresEspeciales() {
	        assertTrue(Ventana_SignIn.validarCorreo("usuario@dominio.com")); // Sin caracteres especiales
	        assertTrue(Ventana_SignIn.validarCorreo("usuario+apellido@dominio.com")); // Con +
	        assertTrue(Ventana_SignIn.validarCorreo("usuario.apellido@dominio.com")); // Con .
	        assertTrue(Ventana_SignIn.validarCorreo("usuario_apellido@dominio.com")); // Con _
	        assertTrue(Ventana_SignIn.validarCorreo("usuario-123@dominio.com")); // Con -
	        assertTrue(Ventana_SignIn.validarCorreo("usuario&apellido@dominio.com")); // Con &
	        assertTrue(Ventana_SignIn.validarCorreo("correo_con_numeros123@dominio.org"));
	    }

	    @Test
	    public void testConStringVacio() {
	        assertFalse(Ventana_SignIn.validarCorreo("")); // String vacío
	        assertFalse(Ventana_SignIn.validarCorreo("   ")); // String de espacios
	    }

	    @Test
	    public void testExtensionCorreo() {
	        assertFalse(Ventana_SignIn.validarCorreo("usuario@dominio")); // Sin extensión (com, org, etc.)
	        assertFalse(Ventana_SignIn.validarCorreo("usuario@dominio.")); // Extensión vacía
	        assertFalse(Ventana_SignIn.validarCorreo("usuario@dominio.c")); // Extensión de una sola letra
	        assertTrue(Ventana_SignIn.validarCorreo("usuario@dominio.co.uk")); // Extensión válida
	    }

}
