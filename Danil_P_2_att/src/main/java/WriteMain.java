import java.io.IOException;

public class WriteMain {
    public static void main(String[] args) throws IOException {
        Serializer.serialize("file.json", Randomizer.rand(5));
    }
}