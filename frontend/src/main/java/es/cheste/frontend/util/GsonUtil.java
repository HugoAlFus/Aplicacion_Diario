package es.cheste.frontend.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

/**
 * Clase de utilidad para la configuración y obtención de instancias de Gson.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class GsonUtil {

    // Instancia de Gson configurada con un adaptador para LocalDate
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    /**
     * Obtiene la instancia de Gson configurada.
     *
     * @return la instancia de Gson
     */
    public static Gson getGson() {
        return GSON;
    }
}