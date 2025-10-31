package util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value.toString()); // ISO-8601
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString());
    }
}