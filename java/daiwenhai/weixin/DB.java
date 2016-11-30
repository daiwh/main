package daiwenhai.weixin;
import android.util.Log;

import java.sql.*;
import java.util.concurrent.Callable;

public class DB {
    private String dbHostAddr = "123.207.124.135:3306";
    private String dbName = "myWeixin";
    private String dbUserName = "myWeixin";
    private String dbPassword = "myWeixin";
    private Connection connection=null;
    private ResultSet resultSet=null;

    private String userMessageTableName = "user";

    public DB(){
    }
    private Statement getStatement(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            Log.e("ERROR", "Class.forName error");
        }
        String url="jdbc:mysql://" + dbHostAddr + "/" + dbName;
        try{
            connection = DriverManager.getConnection(url,dbUserName,dbPassword);
        }catch (Exception e){
            Log.e("ERROR", "getConnection error");
        }
        try{
            return connection.createStatement();
        }catch (Exception e){
            Log.e("ERROR", "createStatement error");
        }
        return null;
    }
    class LOGIN implements Callable{
        private  String userName;
        private  String password;
        public LOGIN(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
        public String call(){
            String sql="select * from" + userMessageTableName + "where userName ='" + userName +"'and userPassword ='" + password +"'";
            Statement  statement =getStatement();
            try{
                resultSet = statement.executeQuery(sql);
            }catch (Exception e){
                Log.e("ERROR", "executeQuery ERROR");
            }
            try {
                if(resultSet.next())
                    return "true";
            }catch (SQLException e){
                Log.e("error", "33");
            }
            return "false";
        }
    }
    //登陆，成功返回true
    public boolean login(String userName, String password){
        String s = new LOGIN(userName, password).call();

        if(s.equals("true"))
            return true;
        return false;
    }
}
