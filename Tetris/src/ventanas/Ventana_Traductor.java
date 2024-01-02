package ventanas;

import ventanas.Ventana_Idioma.Idioma;

public class Ventana_Traductor {
    private static String[][] traducciones = { /* ... tus traducciones ... */ };

    public static String traducir(Idioma idioma, int indicePalabra) {
        int indiceIdioma = idioma.ordinal();
        return traducciones[indicePalabra][indiceIdioma];
    }
}