

import java.io.InputStream;
import java.util.Properties;

/**
 * 测试获取property的值
 * */
public class PropertyTest {
    private final static String CONFIG_NAME = "jdbc.properties";

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertyTest.class.getClassLoader().getResourceAsStream(CONFIG_NAME);
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(properties.getProperty("jdbc.url"));
    }
}
