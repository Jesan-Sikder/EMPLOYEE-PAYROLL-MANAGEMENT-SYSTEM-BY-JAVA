
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Employee;
import util.LocalDateAdapter;
import java.time.LocalDate;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class EmployeeFileUtil {
    public static void saveEmployees(List<Employee> employees, String filename) throws IOException {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(employees, writer);
        }
    }

    public static List<Employee> loadEmployees(String filename) throws IOException {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();
        try (FileReader reader = new FileReader(filename)) {
            Type listType = new TypeToken<List<Employee>>(){}.getType();
            return gson.fromJson(reader, listType);
        }
    }
}