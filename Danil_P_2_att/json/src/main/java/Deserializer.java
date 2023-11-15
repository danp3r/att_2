import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Deserializer {

    public static List<OnlineShop> deserialize(String fileName) throws IOException {
        return new ObjectMapper().readValue(
                new FileInputStream(
                        fileName
                ),
                new TypeReference<List<OnlineShop>>() {}
        );

    }
}
