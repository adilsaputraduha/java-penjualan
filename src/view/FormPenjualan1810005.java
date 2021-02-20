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
import java.util.Date;
import java.text.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static view.FormPembelian1810005.jTextField2;
import static view.FormPembelian1810005.jTextField5;
/**
 *
 * @author Mr Sanak
 */
public class FormPenjualan1810005 extends javax.swing.JFrame {
    
    private Connection conn;
    private Statement stt;
    
    private void tampidata() {
        DefaultTableModel tblmodel = new DefaultTableModel();
        tblmodel.addColumn("No.");
        tblmodel.addColumn("Kode Barang");
        tblmodel.addColumn("Nama Barang");
        tblmodel.addColumn("Harga");
        tblmodel.addColumn("QTY");
        tblmodel.addColumn("Diskon");
        tblmodel.addColumn("Jumlah Harga");

        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();

            String sql = "Select nomorurut1810005, idbarang1810005, barangnama1810005, harga1810005, qty1810005, diskon1810005, (harga1810005 * qty1810005 - diskon1810005) as jmlharga from temp1810005 join barang1810005 on barangid1810005=idbarang1810005";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
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
            jTable2.setModel(tblmodel);
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan" + e);
        }
    }
    private void nomorOtomatis() {
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();

            Date date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
            
            String sql1 = "SELECT jualid1810005 FROM jual1810005 ORDER BY jualid1810005 DESC";
            ResultSet rs;
            rs = stt.executeQuery(sql1);

            if (rs.next()) {
                jTextField1.setText(rs.getString("jualid1810005"));
                String tanggal = jTextField1.getText().toString().substring(4, 12);
                String str = s.format(date);
                if (tanggal.equals(str)) {
                    String nomor = jTextField1.getText().toString().substring(12);
                    int n = Integer.parseInt(nomor);
                    int nomorBaru = n + 1;
                    String nB = String.valueOf(nomorBaru);
                    int panjang = nB.length();
                    String puanjang = String.valueOf(panjang);
                    String nol = null;

                    if (puanjang.equalsIgnoreCase("1")) {
                        nol = "00";
                    } else if (puanjang.equalsIgnoreCase("2")) {
                        nol = "0";
                    } else {
                        nol = "";
                    }
                    jTextField1.setText(String.valueOf("FPJ-" + (s.format(date)) + nol + nomorBaru));
                } else {
                    jTextField1.setText("FPJ-" + (s.format(date)) + "001");
                }
            } else {
                jTextField1.setText("FPJ-" + (s.format(date)) + "001");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Bato"+ e);
        }

    }
    private void totalHarga(){
        int tothrg = 0;
        int jumrec = jTable2.getRowCount();
        for (int i = 0; i < jumrec; i++) {
            tothrg = tothrg + Integer.parseInt(jTable2.getValueAt(i, 6).toString());
        }
        jTextField10.setText(String.valueOf(tothrg));
        AngkaTerbilang konversiAngka = new AngkaTerbilang();
            String tot = String.valueOf(tothrg);
            int satuan = Integer.parseInt(tot);
            jLabel18.setText(konversiAngka.angkaTerbilang(satuan));
    }
    private void caribarang() {
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "select * from barang1810005 where barangid1810005='" + jTextField3.getText() + "'";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                jTextField4.setText(rs.getString(2));
                jTextField5.setText(rs.getString(7));
                jTextField6.setText(rs.getString(6));
                jTextField7.requestFocus();
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Data Barang Tidak Ada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }
    private void tidakAktif() {
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        jTextField7.setEnabled(false);
        jTextField8.setEnabled(false);
        jTextField9.setEnabled(false);
        jTextField10.setEnabled(false);
        jTextField14.setEnabled(false);
        jTextField15.setEnabled(false);
        jTextField16.setEnabled(false);
        jTextField17.setEnabled(false);
    }

    private void aktif() {
        jTextField1.setEnabled(true);
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jTextField6.setEnabled(true);
        jTextField7.setEnabled(true);
        jTextField8.setEnabled(true);
        jTextField9.setEnabled(true);
        jTextField10.setEnabled(true);
        jTextField14.setEnabled(true);
        jTextField15.setEnabled(true);
        jTextField16.setEnabled(true);
        jTextField17.setEnabled(true);
    }

    private void bersih() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
    }
    /**
     * Creates new form FormPenjualan1810005
     */
    public FormPenjualan1810005() {
        initComponents();
        setBackground(new Color(229,229,229));
        tidakAktif();
        jTable2.setModel(tblmodel);
    }
    public javax.swing.table.DefaultTableModel getDefaultTableModel() {
        return new javax.swing.table.DefaultTableModel(new Object[][]{},
                new String[]{"No.","Kode Barang", "Nama Barang", "Harga", "QTY", "Disc", "Jumlah Harga"}
        );
    }
    String data[] = new String[7];
    private javax.swing.table.DefaultTableModel tblmodel = getDefaultTableModel();
    int row = 0;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField18 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel2.setText("No Faktur");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jTextField1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 210, 32));

        jPanel4.setBackground(new java.awt.Color(51, 102, 0));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INFORMASI FAKTUR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BUAT FAKTUR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 110, 32));

        jLabel6.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel6.setText("Tanggal");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jTextField14.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jPanel2.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 210, 30));

        jLabel7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel7.setText("Kasir");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jTextField15.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jPanel2.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 210, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel4.setText("Kode Barang");

        jLabel5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel5.setText("Nama Barang");

        jLabel8.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel8.setText("Stok");

        jLabel9.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel9.setText("Harga Jual");

        jLabel10.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel10.setText("QTY");

        jLabel11.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel11.setText("Disc %");

        jLabel12.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel12.setText("Jumlah Harga");

        jTextField3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField9.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("CARI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(625, 625, 625)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4)
                                .addGap(66, 66, 66)
                                .addComponent(jLabel5)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel8)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel9)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel10)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(39, 39, 39)))))
                .addGap(13, 13, 13))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTable2.setFocusable(false);
        jTable2.setGridColor(new java.awt.Color(51, 102, 0));
        jTable2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable2.setRowHeight(23);
        jTable2.setSelectionBackground(new java.awt.Color(51, 102, 0));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setBackground(new java.awt.Color(51, 102, 0));
        jButton5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("SIMPAN");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 102, 0));
        jButton6.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("LIST TRANSAKSI");
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        jLabel13.setText("Total Harga");

        jTextField10.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Cambria Math", 2, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel22.setText(" Terbilang  :");

        jButton7.setBackground(new java.awt.Color(51, 102, 0));
        jButton7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Lanjut Pembayaran");
        jButton7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.setContentAreaFilled(false);
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel13))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField10))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel15.setText("Jumlah Data  :");

        jComboBox1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField18.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel21.setText("Cari Data");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addComponent(jTextField18))))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(51, 102, 0));

        jLabel14.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("INFORMASI PELANGGAN");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jTextField2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel3.setText("Nama Pelanggan");

        jLabel19.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel19.setText("Telp/NoHp");

        jTextField16.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel20.setText("Alamat");

        jTextField17.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(51, 102, 0));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel8.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 0, 30, 30));

        jLabel16.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("DATA PENJUALAN");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
        caribarang();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            caribarang();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
        jTextField7.requestFocus();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
        jTextField8.requestFocus();
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
        String harga = jTextField6.getText();
        String qty = jTextField7.getText();
        String dis = jTextField8.getText();
        double h = Double.parseDouble(harga);
        double q = Double.parseDouble(qty);
        double d = Double.parseDouble(dis);
        double totdis = d / 100;
        double jumlahdis = (h * q) * totdis;
        double total = (h * q) - jumlahdis;
        jTextField9.setText(String.valueOf(total));
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new FormCariBarang21810005().show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();

            String idbarang = jTextField3.getText();
            String nofaktur = jTextField1.getText();
            String qty = jTextField7.getText();
            String harga = jTextField6.getText();
            String diskon = jTextField8.getText();
            String sql = "ALTER TABLE temp1810005 AUTO_INCREMENT=0";
            stt.executeUpdate(sql);
            String sql1 = "insert into temp1810005 values(nomorurut1810005,'" + idbarang + "','" + qty + "','" + harga + "','" + diskon + "')";
            stt.executeUpdate(sql1);
            tampidata();
            totalHarga();
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");
            jTextField3.requestFocus();
            int jumrec = jTable2.getRowCount();
            jTextField11.setText(String.valueOf(jumrec));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses Penambahan Gagal..." + e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            String nomorurut;
            int row = jTable2.getSelectedRow();
            nomorurut = jTable2.getValueAt(row, 0).toString();
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "delete from temp1810005 where nomorurut1810005='" + nomorurut + "'";
            stt.executeUpdate(sql);
            tampidata();
            totalHarga();
            int jumrec = jTable2.getRowCount();
            jTextField11.setText(String.valueOf(jumrec));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses Penghapusan Gagal..." + e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            nomorOtomatis();
            String nofak = jTextField1.getText();
            String napel = jTextField2.getText();
            String total = jTextField10.getText();
            
            String sql = "insert into jual1810005 values('" + nofak + "', curdate(),'" + napel + "','" + total + "')";
            stt.executeUpdate(sql);
            
            String sql2 = "delete from temp1810005";
            stt.executeUpdate(sql2);

            //tampidata();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
            /*try {
             String nmFile = "./laporan/FakturPenjualan1810005.jasper";
             HashMap parameter = new HashMap();
             parameter.put("awal", jTextField1.getText());
             JasperPrint jPrint = JasperFillManager.fillReport(nmFile, parameter, conn);
             JasperViewer jr = new JasperViewer(jPrint, false);
             jr.viewReport(jPrint, false);
             conn.close();
             } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error"+e);
             }*/
            bersih();
            tidakAktif();

            stt.close();
            
            jButton1.setText("BUAT FAKTUR");
            int jumrec = jTable2.getRowCount();
            jTextField11.setText(String.valueOf(jumrec));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Proses Penyimpanan Gagal..." + e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if (jButton1.getText().equalsIgnoreCase("BUAT FAKTUR")) {
            try {
                aktif();
                bersih();
                conn = Koneksidb.getKoneksi();
                stt = conn.createStatement();
                nomorOtomatis();
                String str = s.format(date);
                jTextField14.setText(str);
                jTextField14.setEnabled(false);
                jTextField15.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                jButton1.setText("BATAL");
                jTextField2.requestFocus();
                stt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ada Kesalahan" + e);
            }
        } else {
            try {
                conn = Koneksidb.getKoneksi();
                stt = conn.createStatement();
                String sql = "delete from jual1810005 where jualid1810005='"+ jTextField1.getText() +"'";
                stt.executeUpdate(sql);
                jButton1.setText("BUAT FAKTUR");
                tidakAktif();
                bersih();
                stt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ada Kesalahan" + e);
            }  
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        jTextField16.requestFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String total = jTextField10.getText();
        new FormPembayaran1810005().show();
        FormPembayaran1810005.jTextField10.setText(total);
        FormPembayaran1810005.jTextField10.setEnabled(false);
        FormPembayaran1810005.jTextField12.requestFocus();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
        jTextField17.requestFocus();
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
        jTextField3.requestFocus();
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        new FormMenu1810005().show();
        this.dispose();
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new FormMenu1810005().show();
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
            java.util.logging.Logger.getLogger(FormPenjualan1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPenjualan1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPenjualan1810005().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    public static javax.swing.JTextField jTextField6;
    public static javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
