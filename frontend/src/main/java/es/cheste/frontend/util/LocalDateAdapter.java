package es.cheste.frontend.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador de Gson para serializar y deserializar objetos LocalDate.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Escribe un objeto LocalDate en el formato JSON.
     *
     * @param jsonWriter el escritor de JSON
     * @param localDate  el objeto LocalDate a escribir
     * @throws IOException si ocurre un error de entrada/salida
     */
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.value(localDate.format(FORMATTER));
    }

    /**
     * Lee un objeto LocalDate desde el formato JSON.
     *
     * @param jsonReader el lector de JSON
     * @return el objeto LocalDate leído
     * @throws IOException si ocurre un error de entrada/salida
     */
    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        return LocalDate.parse(jsonReader.nextString(), FORMATTER);
    }
}