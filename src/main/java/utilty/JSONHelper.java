package utilty;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Static interface implementing methods for handling JSON
 *
 * @author erik
 * @version 1.0
 */

public interface JSONHelper {
    /**
     * Write a list of any object to a json file
     *
     * @param listToWrite the input list
     * @param filePath    the path to write to
     * @throws Exception whenever there is an issue with writing (JSON issues, file issues...)
     */
    @java.lang.SuppressWarnings({"squid:S112"})
    public static void writeListToJSON(List<?> listToWrite, String filePath) throws Exception {
        try (Jsonb jsonb = JsonbBuilder.create(); FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonb.toJson(listToWrite));
        }
    }

    @java.lang.SuppressWarnings({"squid:S112"})
    public static <T> List<T> readListFromJSON(String filePath, Class<?> anonymousClass) throws Exception {
        try (Jsonb jsonb = JsonbBuilder.create()) {
            String jsonLista = Files.readString(Paths.get(filePath));
            return jsonb.fromJson(jsonLista, anonymousClass.getGenericSuperclass());
        }
    }
}
