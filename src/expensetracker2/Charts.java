package expensetracker2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Date;
import java.util.Calendar;

public final class Charts extends javax.swing.JFrame {
    
   private DefaultPieDataset pieDataSet;
    private JFreeChart pieChart;
    private ChartPanel chartPanel;
    private double income = 0.0;
    private double expenses = 0.0;
    
    private int UserId;
    private int Month;


    DBConnection db = new DBConnection();
    public Charts() {
        initComponents();
        showPieChart();
    }
    public Charts(int userid, int month) {
        this.UserId= userid;
        this.Month = month;
        initComponents();
        showPieChart();
    }
    

   public void showPieChart() {
       
        pieDataSet = new DefaultPieDataset();
        updateDataset();

        pieChart = ChartFactory.createPieChart3D(
                "Budget Overview",
                pieDataSet,
                true,
                true,
                false);
        
        PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setSectionPaint("Income", Color.BLUE);
        plot.setSectionPaint("Expenses", Color.RED);


        
        chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(640, 402));

//        jPanel2.removeAll();
//        jPanel2.add(chartPanel, BorderLayout.CENTER);
//        jPanel2.revalidate();
//        jPanel2.repaint();
//        jPanel2.setLayout(new BorderLayout());
//        jPanel2.add(chartPanel, BorderLayout.CENTER);
jPanel2.setLayout(new BorderLayout());
        jPanel2.add(chartPanel, BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }
   public void addIncome(double amount) {
        income += amount;
        updateDataset();
    }

    public void addExpenses(double amount) {
        expenses += amount;
        updateDataset();
    }

    public void updateDataset() {
        double totalIncome = 0.0;
        double totalExpense = 0.0;

        // Get the current date and extract the year and month
        Calendar selectedDate = Calendar.getInstance();
        int year = selectedDate.get(Calendar.YEAR);
       // Month = selectedDate.get(Calendar.MONTH) + 1; // Months are zero-based in Calendar

        String query = "SELECT SUM(amount) AS total_income FROM income WHERE user_id = ? AND YEAR(date) = ? AND MONTH(date) = ?";
        Connection cn = db.getconn();
        try (PreparedStatement pstmt = cn.prepareStatement(query)) {
            pstmt.setInt(1, UserId);
            pstmt.setInt(2, year);
            pstmt.setInt(3, Month);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalIncome = rs.getDouble("total_income");
                    
                } else {
                    totalIncome = 0.0;
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        String query1 = "SELECT SUM(amount) AS total_expense FROM  expences WHERE user_id = ? AND YEAR(date) = ? AND MONTH(date) = ?";
        try (PreparedStatement pstmt = cn.prepareStatement(query1)) {
            pstmt.setInt(1, UserId);
            pstmt.setInt(2, year);
            pstmt.setInt(3, Month);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalExpense = rs.getDouble("total_expense");
                    
                } else {
                    totalExpense = 0.0;
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        pieDataSet.setValue("Income", totalIncome);
        pieDataSet.setValue("Expenses", totalExpense);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Chaparral Pro Light", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRACK YOUR BUDGET");

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(29, 29, 29))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 640, 410));

        jMenu1.setText("Home");
        jMenu1.setMinimumSize(new java.awt.Dimension(37, 23));
        jMenu1.setPreferredSize(new java.awt.Dimension(55, 22));
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setPreferredSize(new java.awt.Dimension(36, 22));

        jMenuItem1.setText("Expenses");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator1);

        jMenuItem2.setText("Income");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Statistics");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Profile p1 = new Profile(UserId);
        p1.setVisible(true);
        p1.setLocationRelativeTo(null); 
    }//GEN-LAST:event_jButton1ActionPerformed
  
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Charts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
