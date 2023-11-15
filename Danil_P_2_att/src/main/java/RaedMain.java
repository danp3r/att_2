import java.io.IOException;

public class RaedMain {
    public static void main(String[] args) throws IOException {
        System.out.println(Deserializer.deserialize("file.json"));
    }
}