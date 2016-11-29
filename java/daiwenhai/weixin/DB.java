package daiwenhai.weixin;
import android.util.Log;

import java.sql.*;

public class DB {

    Connection con=null;
    ResultSet rs=null;
    private Statement getStatement(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            Log.i("ERROR", "Class.forName error");
        }
        String url="jdbc:mysql://123.207.124.135:3306/myWeixin?useUnicode=true&characterEncoding=utf8";
        try{
            con=DriverManager.getConnection(url,"myWeixin","myWeixin");
        }catch (Exception e){
            Log.i("ERROR", "getConnection error");
        }
        try{
            return con.createStatement();
        }catch (Exception e){
            Log.i("ERROR", "createStatement error");
        }
        return null;
    }
    public boolean login(String userName, String password){
        String sql="select * from sinfo";
        Statement stmt = this.getStatement();
        try{
            rs=stmt.executeQuery(sql);
        }catch (Exception e){
            Log.i("ERROR", "executeQuery ERROR");
        }
        return false;
    }


}
