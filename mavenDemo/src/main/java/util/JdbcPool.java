package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * 线程池,单例模式
 * 选择恶汉式单例模式，初始化环境
 * */
public class JdbcPool {

    private static  LinkedList<Connection> jdbcPool = new LinkedList<>();

    static{
        System.out.println("线程池初始化");
        init();
    }

    private static void init(){
        Connection connection = null;
        for(int i=0; i<20; i++){
            try{
                Class.forName(ConfigUtil.getProperty("jdbc.driver"));
                connection = DriverManager.getConnection(ConfigUtil.getProperty("jdbc.url"),
                        ConfigUtil.getProperty("jdbc.username"),
                        ConfigUtil.getProperty("jdbc.password"));
                jdbcPool.add(connection);
            }catch (Exception e){
                System.err.println("线程池初始化失败！");
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取线程池一个连接
     * */
    public synchronized static Connection getConnection(){
        if(jdbcPool.size() <= 0){
            return null;
        }
        // 删除第一个元素并返回
        final Connection connection =  jdbcPool.removeFirst();
        System.out.println("线程池剩余" + jdbcPool.size() + "条连接");
        return connection;
    }

    /**
     * 释放线程池连接
     * */
    public synchronized static void releaseConnecttion(Connection connection){
        jdbcPool.add(connection);
        System.out.println("线程池剩余" + jdbcPool.size() + "条连接");
    }
}
