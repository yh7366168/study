package util;

import java.sql.*;

/**
 * JDBC
 * */
public class JdbcUtil {

    public static void main(String[] args) {
        try{
            Class.forName(ConfigUtil.getProperty("jdbc.driver"));
            Connection connection = DriverManager.getConnection(ConfigUtil.getProperty("jdbc.url"),
                    ConfigUtil.getProperty("jdbc.username"),
                    ConfigUtil.getProperty("jdbc.password"));
            String sql = "select contract_no from contract where scene_id='10000210'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()){
                System.out.println( rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * */
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(ConfigUtil.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(ConfigUtil.getProperty("jdbc.url"),
                    ConfigUtil.getProperty("jdbc.username"),
                    ConfigUtil.getProperty("jdbc.password"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 执行SQL
     */
    private static ResultSet exeQuery(String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = JdbcPool.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                   ps.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                JdbcPool.releaseConnecttion(connection);
            }
        }
        return rs;
    }
}
