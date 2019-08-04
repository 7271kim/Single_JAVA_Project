package LottoGet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;   
import java.util.Map;

public class LottoDB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement psmt = null;
    private Map<String, String> lottoData = new HashMap<String, String>();
    
    public Map<String, String> getLottoData() {
        return lottoData;
    }

    public void setLottoData(Map<String, String> lottoData) {
        this.lottoData = lottoData;
    }

    public void connectionDB(String url, String id, String pw) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,id,pw);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void insertLotto(String date, String number) {
        try {
            Map<String, String> getData = getQurry("SELECT * FROM lotto_data WHERE DATE='"+date+"'");
            if(getData.size() < 1) {            
                psmt = conn.prepareStatement("INSERT INTO lotto_data ( DATE, NUMBER ) values (?,?)");
                psmt.setString(1, date);
                psmt.setString(2, number);
                psmt.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Map<String, String> getQurry(String query) {
        Map<String, String> list = new HashMap<String, String>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM lotto_data");
            System.out.println(rs);
            writeResultSet(rs, list);
        } catch (Exception e) {
            System.out.println("error  : " +e);
        }
        return list;
    }
    
    public Map<String, String> allData() {
        Map<String, String> list = new HashMap<String, String>();
        try {
            stmt = conn.createStatement();
            rs = stmt
                    .executeQuery("SELECT * FROM lotto_data");
            writeResultSet(rs, list);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    private Map<String, String> writeResultSet(ResultSet resultSet, Map<String, String> list) throws SQLException {
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            String number = resultSet.getString("number");
            list.put(date, number);
        }
        return list;
    }
    
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }
}
