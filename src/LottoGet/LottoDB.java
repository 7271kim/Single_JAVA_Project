package LottoGet;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class LottoDB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement psmt = null;
    private Map<String, String> lottoData = new HashMap<String, String>();
    
    public LottoDB() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(System.getenv("ASOG_ENCRYPTION_KEY"));
        String url = encryptor.decrypt("4oLHJBd+TTcLzoNeK1sT9DDgmv+Qvkt60TJ8USFya4tnufc3D1ztED2cERWKb4ST=");
        String id = encryptor.decrypt("Ma/9nT/AbenjlE85W0D+di1tNfRHyLTC");
        String pw = encryptor.decrypt("I7qV/0X33au3v+j7swW36uxKBFCrBbg4");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,id,pw);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Map<String, String> getLottoData() {
        return lottoData;
    }

    public void setLottoData(Map<String, String> lottoData) {
        this.lottoData = lottoData;
    }
    
    // o 
    public synchronized void insertLotto(String date, String number) {
        try {
            psmt = conn.prepareStatement("INSERT INTO lotto_data ( DATE, NUMBER ) values (?,?)");
            psmt.setString(1, date);
            psmt.setString(2, number);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //o 
    public synchronized void insertLottoNum( String number, String value ) {
        try {
                psmt = conn.prepareStatement("INSERT INTO lotto_number ( NUMBER, SUM ) values (?, ?)");
                psmt.setString(1, number);
                psmt.setString(2, value);
                psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // 0 
    public synchronized void updateLottoNum( String number, String value ) {
        try {
                psmt = conn.prepareStatement("UPDATE lotto_number SET SUM = ? WHERE NUMBER=? ");
                psmt.setString(1, value);
                psmt.setString(2, number);
                psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // 0 
    public synchronized int getOne(String query, String findColumn) {
        int result = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                result =  Integer.parseInt(rs.getString(findColumn));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public List<LottoModel> getLottoNumList( String query, TreeSet<String> columns ) {
        List<LottoModel> result = new ArrayList<LottoModel>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LottoModel lottoModel = new LottoModel();
                Class targetClass = Class.forName("LottoGet.LottoModel"); 
                Method methods[] = targetClass.getDeclaredMethods(); 

                for( String column : columns ) {
                    for(int i=0;i<methods.length;i++) {
                        String findMethod = methods[i].getName();
                        if(findMethod.equals( "set" + column)) {
                            try {
                                methods[i].invoke( lottoModel,rs.getString(column));
                            } catch ( Exception e) {
                            }
                        }
                    }
                }
                
                result.add(lottoModel);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    // d
    public synchronized boolean getQurry(String query) {
        boolean result = false;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            int size = 0;
            while (rs.next()) {
                size++;
            }
            result = size != 0;
        } catch (Exception e) {
            System.out.println("error  : " +e);
        }
        return result;
    }
    
    public Map<String, String> getAllData() {
        Map<String, String> list = new HashMap<String, String>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM lotto_data ORDER BY `DATE` DESC");
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
