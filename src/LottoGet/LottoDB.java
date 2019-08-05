package LottoGet;

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
        String url = encryptor.decrypt("N9E8Gx8OgK3sSTU5lvKukxCZK1JvDJ6yYH00Oo2QGgSArGdet42ZOjulkKYItVCCgMcccyM5U4c=");
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
    
    public void insertLottoNum(String number, String number2, String value) {
        try {
                psmt = conn.prepareStatement("INSERT INTO lotto_number_total ( NUMBER, NUMBER_TWO,VALUE ) values (?,?,?)");
                psmt.setString(1, number);
                psmt.setString(2, number2);
                psmt.setString(3, value);
                psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateLottoNum(String number, String number2, String value) {
        try {
                psmt = conn.prepareStatement("UPDATE lotto_number_total SET VALUE = ? WHERE NUMBER=? AND NUMBER_TWO=?");
                psmt.setString(1, value);
                psmt.setString(2, number);
                psmt.setString(3, number2);
                psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int getLottoNumOne(String query) {
        int result = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            result = writeResultSet2(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public List<LottoModel> getLottoNumList(String number) {
        List<LottoModel> result = new ArrayList<LottoModel>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT NUMBER,NUMBER_TWO,VALUE FROM lotto_number_total WHERE NUMBER="+number);
            result = getResultSet(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public Map<String, String> getQurry(String query) {
        Map<String, String> list = new HashMap<String, String>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            writeResultSet(rs, list);
        } catch (Exception e) {
            System.out.println("error  : " +e);
        }
        return list;
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
    
    private List<LottoModel> getResultSet(ResultSet resultSet) throws SQLException {
        List<LottoModel> list = new ArrayList<LottoModel>();
        while (resultSet.next()) {
            LottoModel lottoModel = new LottoModel();
            lottoModel.setNumber(resultSet.getString("number"));
            lottoModel.setNumber_two(resultSet.getString("number_two"));
            lottoModel.setValue(resultSet.getString("value"));
            list.add(lottoModel);
        }
        return list;
    }
    
    private int writeResultSet2(ResultSet resultSet) throws SQLException {
        int result=1;
        while (resultSet.next()) {
            result =  Integer.parseInt(resultSet.getString("value"));
        }
        return result;
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
