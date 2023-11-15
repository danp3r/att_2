import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Serializer {

    public static void serialize(String fileName, List<OnlineShop> list) throws IOException {

        new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValue(
                new File(
                        fileName
                ),
                list
        );

    }
}
