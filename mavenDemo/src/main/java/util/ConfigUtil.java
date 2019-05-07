package util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 单例模式，取配置文件的信息
 * */
public class ConfigUtil{

    private static ConfigUtil configUtil = null;
    private static Properties properties = null;
    private static Object configLock = new Object();
    private static Object porpertyLock = new Object();
    private final static String CONFIG_FILE_NAME = "jdbc.properties";

    private ConfigUtil(){

    }

    private static ConfigUtil getInstance() {
        if(configUtil == null){
            synchronized (configLock){
                if(configUtil == null){
                    configUtil = new ConfigUtil();
                }
            }
        }
        return configUtil;
    }

    private static Properties initProperties(){
        if(properties == null){
            synchronized (porpertyLock){
                if(properties == null){
                    properties = new Properties();
                }
            }
        }
        return properties;
    }

    public static String getProperty(String name){
        return getInstance()._getProperty(name);
    }

    private String _getProperty(String name){
        loadProperties();
        String propertyValue = properties.getProperty(name);
        if(propertyValue == null){
            propertyValue = "";
        }else{
            propertyValue = propertyValue.trim();
        }
        return  propertyValue;
    }

    private void loadProperties(){
        properties = initProperties();
        InputStream inputStream = null;
        try{
            inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
            properties.load(inputStream);
        } catch (Exception e){
            System.err.println("加载配置失败！");
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (Exception e){
                    System.err.println("资源未注销！");
                    e.printStackTrace();
                }
            }
        }
    }

    public static Properties loadProperties(String fileName){
        if(fileName == null||"".equals(fileName) ){
            throw new RuntimeException( fileName + "不存在！");
        }
        properties = initProperties();
        InputStream inputStream = null;
        try{
            inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(inputStream);
        }catch (Exception e){
            System.err.println("加载配置失败！");
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (Exception e){
                    System.err.println("资源未注销！");
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

}
