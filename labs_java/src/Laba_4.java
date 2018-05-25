import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;

public class Laba_4 {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Map<String, String> props = tryOpenProps(scanner);
        System.out.println("OK. Name or surname?\nwrite ESC fot exit");

        String key;
        while ((key = scanner.next()) != null) {
            if (key.equals("ESC")) break;
            System.out.println(props.getOrDefault(key, "i don't know it: " + key));
        }
        scanner.close();
    }

    private static Map tryOpenProps(final Scanner scanner) {
        InputStream stream = null;
        Properties properties = null;
        do {
            stream = tryOpenInputStream(stream, "config.prop");
            if (stream == null) continue;
            properties = tryOpenProperties(stream, properties, "config.prop");
        } while (stream == null || properties == null);
        return properties;
    }

    private static Properties tryOpenProperties(InputStream stream, Properties properties, String fname) {
        try {
            properties = loadProperties(stream);
        } catch (IOException e) {
            System.out.println("can't read");
        }
        return properties;
    }

    private static Properties loadProperties(final InputStream propstream) throws IOException {
        final Properties properties = new Properties();
        properties.load(propstream);
        propstream.close();
        return properties;
    }

    private static InputStream tryOpenInputStream(InputStream stream, String filename) {
        try {
            stream = Files.newInputStream(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("can't open ");
        }
        return stream;
    }
}