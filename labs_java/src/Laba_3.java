import java.io.*;
import java.util.Properties;

public class Laba_3 {
    public static void main(String[] args)
    {
        FileInputStream config = null;
        try {
            config = new FileInputStream("config.prop");
        }
        catch (FileNotFoundException e) {
            System.out.println("no file\n");
            return;
        }

        Properties properties = new Properties();
        try {
            properties.load(config);
        }
        catch (IOException e) {
            System.out.println("can't read\n");
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("\nname or surname? (enter for exit) ");
                String string = bufferedReader.readLine();
                if (string == null || string.length() == 0) {
                    break;
                }
                if (properties.containsKey(string)) {
                    System.out.println("my "+ string +" is "+ properties.getProperty(string));
                } else {
                    System.out.println("name or surname!");
                }
            }
            bufferedReader.close();

        }
        catch (IOException e) {
            System.out.println("can't read\n");
        }
    }
}