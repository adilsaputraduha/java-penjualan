/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.sql.*;
import database.Koneksidb;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Mr Sanak
 */
public class FormBarang1810005 extends javax.swing.JFrame {
    private Connection conn;
    private Statement stt;
    
    private void tampilData(){
        DefaultTableModel tblmodel = new DefaultTableModel();
        tblmodel.addColumn("Kode Barang");
        tblmodel.addColumn("Nama Barang");
        tblmodel.addColumn("Merk");
        tblmodel.addColumn("Jenis");
        tblmodel.addColumn("Satuan");
        tblmodel.addColumn("Harga");
        tblmodel.addColumn("Stok");
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "select barangid1810005, barangnama1810005, merknama1810005,"
                    + " jenisnama1810005, barangsatuan1810005, barangharga1810005,"
                    + " barangstok1810005 from barang1810005 join merk1810005 on barangmerkid1810005=merkid1810005"
                    + " join jenis1810005 on barangjenisid1810005=jenisid1810005";
            ResultSet rs = stt.executeQuery(sql);
            while(rs.next()){
                tblmodel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                });
            }
            jTable1.setModel(tblmodel);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan" +e);
        }
    }
    private void isicombomerk(){
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "Select * from merk1810005";
            ResultSet rs = stt.executeQuery(sql);
            while(rs.next()){
                jComboBox1.addItem(rs.getString("merkid1810005")+ " ~ " + rs.getString("merknama1810005"));
            }
            rs.close();
            stt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void isicombojenis(){
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "Select * from jenis1810005";
            ResultSet rs = stt.executeQuery(sql);
            while(rs.next()){
                jComboBox2.addItem(rs.getString("jenisid1810005")+ " ~ " + rs.getString("jenisnama1810005"));
            }
            rs.close();
            stt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void cekidbarang(){
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "select barang1810005.*, merknama1810005, jenisnama1810005"
                    + " from barang1810005 join merk1810005 on barangmerkid1810005=merkid1810005"
                    + " join jenis1810005 on barangjenisid1810005=jenisid1810005"
                    + " where barangid1810005='"+ jTextField1.getText() +"'";
            ResultSet rs = stt.executeQuery(sql);
            
            if(rs.next()){
                jTextField2.setText(rs.getString("barangnama1810005"));
                jComboBox1.setSelectedItem(rs.getString("barangmerkid1810005") + " ~ " + rs.getString("merknama1810005"));
                jComboBox2.setSelectedItem(rs.getString("barangjenisid1810005") + " ~ " + rs.getString("jenisnama1810005"));
                jTextField3.setText(rs.getString("barangsatuan1810005"));
                jTextField4.setText(rs.getString("barangharga1810005"));
                jTextField5.setText(rs.getString("barangstok1810005"));
                jButton2.setText("UPDATE");
            }else{
                jTextField2.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" +e);
        }
    }
    void bersih(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox1.setSelectedItem("===PILIH===");
        jComboBox2.setSelectedItem("===PILIH===");
    }
    void aktif(){
        jTextField1.setEnabled(true);
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
    }
    void tidakAktif(){
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
    }
    /**
     * Creates new form FormBarang1810005
     */
    public FormBarang1810005() {
        initComponents();
        isicombomerk();
        isicombojenis();
        tampilData();
        tidakAktif();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "===PILIH===" }));

        jComboBox2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "===PILIH===" }));

        jTextField3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel2.setText("Kode Barang");

        jLabel3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel4.setText("Merk Barang");

        jLabel5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel5.setText("Jenis Barang");

        jLabel8.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel8.setText("Satuan");

        jLabel9.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel9.setText("Harga");

        jLabel10.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel10.setText("Stok");

        jPanel5.setBackground(new java.awt.Color(51, 102, 0));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INFORMASI BARANG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel14.setText("Gambar");

        jLabel15.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel15.setText("Rp.");

        jButton4.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton4.setText("...");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jButton4))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTextField6.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(32, 65, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Search_20px_1.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kode Barang", "Nama Barang", "Merk Barang", "Jenis Barang" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(32, 65, 0));
        jButton1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Add_File_30px.png"))); // NOI18N
        jButton1.setText("TAMBAH");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(32, 65, 0));
        jButton2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Save_30px_1.png"))); // NOI18N
        jButton2.setText("SIMPAN");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(32, 65, 0));
        jButton3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Delete_30px.png"))); // NOI18N
        jButton3.setText("HAPUS");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setContentAreaFilled(false);
        jButton3.setOpaque(true);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(51, 102, 0));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Close_Window_40px.png"))); // NOI18N
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("User Aktif :");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(233, 233, 233));
        jLabel12.setText("DATA BARANG");

        jLabel11.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("Tanggal    :");

        jLabel13.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Jam            :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(246, 246, 246))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel12)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(24, 24, 24))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(51, 102, 0));

        jLabel6.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TABEL DATA BARANG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(51, 102, 0));
        jTable1.setRowHeight(23);
        jTable1.setSelectionBackground(new java.awt.Color(51, 102, 0));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(32, 65, 0));
        jButton6.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Shopping_Cart_30px.png"))); // NOI18N
        jButton6.setText("Add List");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setContentAreaFilled(false);
        jButton6.setIconTextGap(10);
        jButton6.setOpaque(true);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(32, 65, 0));
        jButton7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_View_30px_1.png"))); // NOI18N
        jButton7.setText("Lihat List");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.setContentAreaFilled(false);
        jButton7.setIconTextGap(9);
        jButton7.setOpaque(true);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            cekidbarang();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
        jTextField4.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jButton1.getText().equals("TAMBAH")){
            aktif();
            jTextField1.requestFocus();
            jButton1.setText("BATAL");
        }else{
            bersih();
            tidakAktif();
            tampilData();
            jButton1.setText("TAMBAH");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            String id = jTextField1.getText();
            String nama = jTextField2.getText();
            String satuan = jTextField3.getText();
            String harga = jTextField4.getText();
            String stok = jTextField5.getText();
            
            String merk = null;
            merk = jComboBox1.getSelectedItem().toString().substring(0,5);
            
            String jenis = null;
            jenis = jComboBox2.getSelectedItem().toString().substring(0,5);
            
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String label = jButton2.getText();
            
                if(label.equals("SIMPAN")){
                    String sql = "insert into barang1810005 values ('"+ id +"','"+ nama +"',"
                            + "'"+ merk +"','"+ jenis +"','"+ satuan +"','"+ harga +"','"+ stok +"')";
                    stt.executeUpdate(sql);
                    conn.close();
                    stt.close();
                    tidakAktif();
                    jButton1.setText("TAMBAH");
                    JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan" );
                }else{
                    String sql = "update barang1810005 set barangnama1810005='"+ nama +"',"
                            + " barangmerkid1810005='"+ merk +"',"
                            + " barangjenisid1810005='"+ jenis +"',"
                            + " barangsatuan1810005='"+ satuan +"',"
                            + " barangharga1810005='"+ harga +"',"
                            + " barangstok1810005='"+ stok +"'"
                            + " where barangid1810005='"+ id +"'";
                    stt.executeUpdate(sql);
                    stt.close();
                    conn.close();
                    tidakAktif();
                    jButton1.setText("TAMBAH");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
                }
                bersih();
                tampilData();
                jButton2.setText("SIMPAN");
                jButton3.setEnabled(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Proses Penyimpanan Gagal"+e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int pilih = JOptionPane.showConfirmDialog(null, "Hapus data ini?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        
        if(pilih == JOptionPane.YES_OPTION){
            try {
                conn = Koneksidb.getKoneksi();
                stt = conn.createStatement();
                String sql = "delete from barang1810005 where barangid1810005='"+ jTextField1.getText() +"'";
                stt.executeUpdate(sql);
                stt.close();
                conn.close();
                bersih();
                tidakAktif();
                tampilData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" +e);
            }
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int tabel = jTable1.getSelectedRow();
        String idbarang = jTable1.getValueAt(tabel, 0).toString();
        jTextField1.setText(idbarang);
        cekidbarang();
        aktif();
        jButton1.setText("Batal");
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblmodel = new DefaultTableModel();
        tblmodel.addColumn("Kode Barang");
        tblmodel.addColumn("Nama Barang");
        tblmodel.addColumn("Merk");
        tblmodel.addColumn("Jenis");
        tblmodel.addColumn("Satuan");
        tblmodel.addColumn("Harga");
        tblmodel.addColumn("Stok");
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = null;
            String pilih = jComboBox3.getSelectedItem().toString();
            if(pilih.equals("Kode Barang")){
                sql = "select barangid1810005, barangnama1810005, merknama1810005, "
                    + "jenisnama1810005, barangsatuan1810005, barangharga1810005, barangstok1810005 "
                    + "from barang1810005 join merk1810005 on barangmerkid1810005=merkid1810005 "
                    + "join jenis1810005 on barangjenisid1810005=jenisid1810005 "
                    + "where barangid1810005 like '%"+ jTextField6.getText() +"%'";
            }else if(pilih.equals("Nama Barang")){
                sql = "select barangid1810005, barangnama1810005, merknama1810005, "
                    + "jenisnama1810005, barangsatuan1810005, barangharga1810005, barangstok1810005 "
                    + "from barang1810005 join merk1810005 on barangmerkid1810005=merkid1810005 "
                    + "join jenis1810005 on barangjenisid1810005=jenisid1810005 "
                    + "where barangnama1810005 like '%"+ jTextField6.getText() +"%'";
            }else if(pilih.equals("Merk Barang")){
                sql = "select barangid1810005, barangnama1810005, merknama1810005, "
                    + "jenisnama1810005, barangsatuan1810005, barangharga1810005, barangstok1810005 "
                    + "from barang1810005 join merk1810005 on barangmerkid1810005=merkid1810005 "
                    + "join jenis1810005 on barangjenisid1810005=jenisid1810005 "
                    + "where merknama1810005 like '%"+ jTextField6.getText() +"%'";
            }else{
                sql = "select barangid1810005, barangnama1810005, merknama1810005, "
                    + "jenisnama1810005, barangsatuan1810005, barangharga1810005, barangstok1810005 "
                    + "from barang1810005 join merk1810005 on barangmerkid1810005=merkid1810005 "
                    + "join jenis1810005 on barangjenisid1810005=jenisid1810005 "
                    + "where jenisnama1810005 like '%"+ jTextField6.getText() +"%'";
            }
            ResultSet rs = stt.executeQuery(sql);
            while(rs.next()){
                tblmodel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                });
            }
            jTable1.setModel(tblmodel);
            int tabel = jTable1.getRowCount();
            if(tabel==0){
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan"+ e);
        }
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblmodel = new DefaultTableModel();
        tblmodel.addColumn("Kode Barang");
        tblmodel.addColumn("Nama Barang");
        tblmodel.addColumn("Merk");
        tblmodel.addColumn("Jenis");
        tblmodel.addColumn("Satuan");
        tblmodel.addColumn("Harga");
        tblmodel.addColumn("Stok");
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();

            String sql = "select barangid1810005, barangnama1810005, merknama1810005, "
                    + "jenisnama1810005, barangsatuan1810005, barangharga1810005, barangstok1810005 "
                    + "from barang1810005 join merk1810005 on barangmerkid1810005=merkid1810005 "
                    + "join jenis1810005 on barangjenisid1810005=jenisid1810005 "
                    + "where barangnama1810005 like '%"+ jTextField6.getText() +"%'";
            ResultSet rs = stt.executeQuery(sql);
            while(rs.next()){
                tblmodel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                });
            }
            jTable1.setModel(tblmodel);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan"+ e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        new FormMenu1810005().show();
        this.dispose();
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
        jButton6.setBackground(new Color(25,52,0));
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
        jButton7.setBackground(new Color(25,52,0));
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setBackground(new Color(25,52,0));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
        jButton2.setBackground(new Color(25,52,0));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // TODO add your handling code here:
        jButton3.setBackground(new Color(25,52,0));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        // TODO add your handling code here:
        jButton6.setBackground(new Color(32,65,0));
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        // TODO add your handling code here:
        jButton7.setBackground(new Color(32,65,0));
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton1.setBackground(new Color(32,65,0));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        // TODO add your handling code here:
        jButton2.setBackground(new Color(32,65,0));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        // TODO add your handling code here:
        jButton3.setBackground(new Color(32,65,0));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
        jButton5.setBackground(new Color(32,65,0));
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
        jButton5.setBackground(new Color(25,52,0));
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormBarang1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBarang1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBarang1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarang1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBarang1810005().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
