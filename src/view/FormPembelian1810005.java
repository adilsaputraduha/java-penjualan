/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.*;
import database.Koneksidb;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mr Sanak
 */
public class FormPembelian1810005 extends javax.swing.JFrame {

    private Connection conn;
    private Statement stt;

    private void nomorOtomatis() {

        try {

            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();

            String sql1 = "SELECT beliid1810005 FROM beli1810005 ORDER BY beliid1810005 DESC";
            ResultSet rs;
            rs = stt.executeQuery(sql1);

            if (rs.next()) {
                jTextField1.setText(rs.getString("beliid1810005"));
                String nomor = jTextField1.getText().toString().substring(4);
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
                jTextField1.setText(String.valueOf("FPB-" + nol + nomorBaru));
            } else {
                jTextField1.setText("FPB-001");
            }
            rs.close();
            stt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }

    private void isicombosupplier() {
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "Select * from supplier1810005";
            ResultSet rs;
            rs = stt.executeQuery(sql);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("supplierid1810005") + "~" + rs.getString("suppliernama1810005"));
            }
            rs.close();
            stt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void caribarang() {
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "select * from barang1810005 where barangid1810005='" + jTextField2.getText() + "'";
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                jTextField3.setText(rs.getString(2));
                jTextField4.setText(rs.getString(7));
                jTextField5.requestFocus();
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Data Barang Tidak Ada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }

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
            jTable1.setModel(tblmodel);
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan" + e);
        }
    }

    private void totalharga() {
        int tothrg = 0;
        int jumrec = jTable1.getRowCount();
        for (int i = 0; i < jumrec; i++) {
            tothrg = tothrg + Integer.parseInt(jTable1.getValueAt(i, 6).toString());
        }
        jTextField9.setText(String.valueOf(tothrg));
        AngkaTerbilang konversiAngka = new AngkaTerbilang();
        String tot = String.valueOf(tothrg);
        int satuan = Integer.parseInt(tot);
        jLabel16.setText(konversiAngka.angkaTerbilang(satuan));
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
    }
    /**
     * Creates new form FormPembelian1810005
     */
    public FormPembelian1810005() {
        initComponents();
        isicombosupplier();
        tidakAktif();
        jTable1.setModel(tblmodel);
    }

    public javax.swing.table.DefaultTableModel getDefaultTableModel() {
        return new javax.swing.table.DefaultTableModel(new Object[][]{},
                new String[]{"No.","Kode Barang", "Nama Barang", "Harga Beli", "QTY", "Disc", "Jumlah Harga"}
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

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField13 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DATA PEMBELIAN");
        setBackground(new java.awt.Color(247, 247, 247));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel2.setText("No Faktur");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 58, -1, 35));

        jTextField1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 59, 208, 34));

        jPanel7.setBackground(new java.awt.Color(51, 102, 0));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INFORMASI FAKTUR");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, -1));

        jLabel7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel7.setText("Tanggal");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 104, -1, 34));

        jTextField11.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 104, 256, 34));

        jLabel14.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel14.setText("Kasir");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 149, -1, 32));

        jTextField12.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jPanel1.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 149, 256, 32));

        jButton1.setFont(new java.awt.Font("Cambria Math", 0, 13)); // NOI18N
        jButton1.setText("BUAT FAKTUR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 58, 120, 35));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel4.setText("Kode Barang");

        jLabel5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel5.setText("Nama Barang");

        jLabel8.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel8.setText("Stok");

        jLabel9.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel9.setText("Harga Beli");

        jLabel10.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel10.setText("QTY");

        jLabel11.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel11.setText("Disc");

        jLabel12.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel12.setText("Jumlah Harga");

        jTextField2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

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

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Search_20px.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel5)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel8)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel9)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel10)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(45, 45, 45))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jTextField5)
                    .addComponent(jTextField7)
                    .addComponent(jTextField6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jTable1.setGridColor(new java.awt.Color(51, 102, 0));
        jTable1.setRowHeight(23);
        jTable1.setSelectionBackground(new java.awt.Color(51, 102, 0));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel5.setBackground(new java.awt.Color(51, 102, 0));

        jLabel15.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Jumlah Data  :");

        jComboBox2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField13.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Cari Data");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel21)
                .addGap(15, 15, 15)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel19.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("DATA PEMBELIAN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel3.setText("Supplier");

        jPanel8.setBackground(new java.awt.Color(51, 102, 0));

        jLabel6.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("INFORMASI SUPPLIER");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel6)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel17.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel17.setText("No Telp.");

        jTextField14.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel17))
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 252, Short.MAX_VALUE)
                    .addComponent(jTextField14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField9.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel13.setText("TOTAL HARGA");

        jButton5.setBackground(new java.awt.Color(51, 102, 0));
        jButton5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("SIMPAN");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Cambria Math", 2, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel18.setText("Terbilang  :");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new FormCariBarang1810005().show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            caribarang();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String nofak = jTextField1.getText();
            String supplier = jComboBox1.getSelectedItem().toString().substring(0, 5);
            String total = jTextField9.getText();
            String terbilang = jLabel16.getText();
            
            String sql = "insert into beli1810005 values('" + nofak + "', curdate(),'" + supplier + "','" + total + "','"+ terbilang +"')";
            stt.executeUpdate(sql);
 
            String sql2 = "delete from temp1810005";
            stt.executeUpdate(sql2);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
            /*try {

            String nmFile = "./laporan/FakturPembelian1810005.jasper";
            HashMap parameter = new HashMap();
            parameter.put("awal", jTextField1.getText());
            JasperPrint jPrint = JasperFillManager.fillReport(nmFile, parameter, conn);
            JasperViewer jr = new JasperViewer(jPrint, false);
            jr.viewReport(jPrint, false);
            conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error"+e);
            }*/
            
            stt.close();
            conn.close();
            tampidata();
            bersih();
            tidakAktif();
            jButton1.setText("BUAT FAKTUR");
            int jumrec = jTable1.getRowCount();
            jTextField10.setText(String.valueOf(jumrec));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Proses Penyimpanan Gagal..." + e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();

            String idbarang = jTextField2.getText();
            String qty = jTextField6.getText();
            String harga = jTextField5.getText();
            String diskon = jTextField7.getText();
            String supplier = jComboBox1.getSelectedItem().toString().substring(0, 5);
            String total = jTextField9.getText();
            String sql = "ALTER TABLE temp1810005 AUTO_INCREMENT=0";
            stt.executeUpdate(sql);
            String sql1 = "insert into temp1810005 values(nomorurut1810005,'" + idbarang + "','" + qty + "','" + harga + "','" + diskon + "')";
            stt.executeUpdate(sql1);
            tampidata();
            totalharga();
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField2.requestFocus();
            int jumrec = jTable1.getRowCount();
            jTextField10.setText(String.valueOf(jumrec));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses Penambahan Gagal..." + e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            String nomorurut;
            int row = jTable1.getSelectedRow();
            nomorurut = jTable1.getValueAt(row, 0).toString();
            conn = Koneksidb.getKoneksi();
            stt = conn.createStatement();
            String sql = "delete from temp1810005 where nomorurut1810005='" + nomorurut + "'";
            stt.executeUpdate(sql);
            tampidata();
            totalharga();
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField2.requestFocus();
            int jumrec = jTable1.getRowCount();
            jTextField10.setText(String.valueOf(jumrec));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses Penghapusan Gagal..." + e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jButton1.getText().equalsIgnoreCase("BUAT FAKTUR")) {
            try {
                aktif();
                bersih();
                conn = Koneksidb.getKoneksi();
                stt = conn.createStatement();
                String sql = "TRUNCATE TABLE temp1810005";
                stt.executeUpdate(sql);
                nomorOtomatis();
                jButton1.setText("BATAL");
                jTextField1.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField2.requestFocus();
                stt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ada Kesalahan" + e);
            }
        } else {
            jButton1.setText("BUAT FAKTUR");
            tidakAktif();
            bersih();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
        String harga = jTextField5.getText();
        String qty = jTextField6.getText();
        String dis = jTextField7.getText();
        double h = Double.parseDouble(harga);
        double q = Double.parseDouble(qty);
        double d = Double.parseDouble(dis);
        double jumlah = h * q - d;
        jTextField8.setText(String.valueOf(jumlah));
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        jTextField6.requestFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
        jTextField7.requestFocus();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

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
            java.util.logging.Logger.getLogger(FormPembelian1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPembelian1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPembelian1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPembelian1810005.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPembelian1810005().setVisible(true);
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
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    public static javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
