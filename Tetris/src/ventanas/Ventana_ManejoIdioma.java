package ventanas;

import java.util.ArrayList;

public class Ventana_ManejoIdioma {
	private static Ventana_Idioma.Idioma idiomaActual = Ventana_Idioma.Idioma.ESP;
    private static ArrayList<VentanaIdiomaActualizable> ventanasActualizables = new ArrayList<>();

    public static void cambiarIdioma(Ventana_Idioma.Idioma nuevoIdioma) {
        idiomaActual = nuevoIdioma;
        actualizarIdiomaEnTodasLasVentanas();
    }

    public static void registrarVentana(Ventana_Principal ventana_Principal) {
        ventanasActualizables.add((VentanaIdiomaActualizable) ventana_Principal);
    }

    private static void actualizarIdiomaEnTodasLasVentanas() {
        for (VentanaIdiomaActualizable ventana : ventanasActualizables) {
            ventana.actualizarIdioma(idiomaActual);
        }
    }
}