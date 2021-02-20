/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author Mr Sanak
 */
public class Koneksidb {
    private static Connection conn;
    private static Statement stt;
    
    public static Connection getKoneksi() {
        try {
            String ip = "", database ="", username = "", password = "";
            File data1 = new File("./koneksi/ipServer.txt");
            File data2 = new File("./koneksi/database.txt");
            File data3 = new File("./koneksi/username.txt");
            File data4 = new File("./koneksi/password.txt");
            Scanner sc1 = new Scanner(data1);
            Scanner sc2 = new Scanner(data2);
            Scanner sc3 = new Scanner(data3);
            Scanner sc4 = new Scanner(data4);
            Vector<String> v1 = new Vector<String>();
            Vector<String> v2 = new Vector<String>();
            Vector<String> v3 = new Vector<String>();
            Vector<String> v4 = new Vector<String>();
            while (sc1.hasNextLine()) {
                v1.add(sc1.nextLine());
            }
            while (sc2.hasNextLine()) {
                v2.add(sc2.nextLine());
            }
            while (sc3.hasNextLine()) {
                v3.add(sc3.nextLine());
            }
            while (sc4.hasNextLine()) {
                v4.add(sc4.nextLine());
            }
            for (int i = 0; i < v1.size(); i++) {
                ip = v1.get(i);
            }
            for (int j = 0; j < v2.size(); j++) {
                database = v2.get(j);
            }
            for (int k = 0; k < v3.size(); k++) {
                username = v3.get(k);
            }
            for (int l = 0; l < v4.size(); l++) {
                password = v4.get(l);
            }
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://" + ip + ":3306/";
            String db = database;
            String user = username;
            String pass = password;
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url + db, user, pass);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal.." + e);
            System.out.println(e.getMessage());
            return null;
        }
    }
}
