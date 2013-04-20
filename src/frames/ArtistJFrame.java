/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import com.prcse.datamodel.*;
import java.util.HashMap;
import java.util.Vector;
import java.text.SimpleDateFormat;
/**
 *
 * @author snowman
 */
public class ArtistJFrame extends javax.swing.JFrame {

    /**
     * Creates new form CustomerJFrame
     */
    String process = "" ;
    long artistID = 0l;
    private HashMap getArtistDetails(String par){
      
        persistence.ArtistDAO dao  = new persistence.ArtistDAO();
       return dao.getArtistDetails(par);
    }
    private void fillArtistDetails(HashMap par){
        Artist a = (Artist) par.get(0);
        if(a==null)
            return;
        this.jTextName.setText(a.getName());
        this.jTextDesc.setText(a.getBio());
        this.artistID = a.getId();
        
       Vector eventlist = (Vector)par.get(1);
       SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        

         String[] colNames = {"Name" , "Start Time","End Time"};
         String[][] data  = new String[eventlist.size()][3];
         for (int i =0 ; i < eventlist.size();i++ ){
             Event e = (Event) eventlist.get(i);
             data[i][0] = e.getName();
             data[i][1] = sdf.format(e.getStartTime().getTime());
             data[i][2] = sdf.format(e.getEndTime().getTime());          
         }
         
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
         
          Vector tourlist = (Vector)par.get(2);
         
         String[] colNames2 = {"Name"};
         String[][] data2  = new String[tourlist.size()][1];
         for (int i =0 ; i < tourlist.size();i++ ){
             Tour e = (Tour) tourlist.get(i);
             data2[i][0] = e.getName();
                       
         }
         
          javax.swing.table.DefaultTableModel model2 = new javax.swing.table.DefaultTableModel(data2, colNames2);
         this.jTable3.setModel(model2);  
          
    }
    private void fillArtistList(){
           persistence.ArtistDAO dao  = new persistence.ArtistDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getArtistsList();
      for (int i = 0; i < list.length ; i++){
          model1.addElement(list[i]);
      }
      this.jList1.setModel(model1);
      jList1.setSelectedIndex(0);
    }
    public ArtistJFrame() {
        initComponents();
        fillArtistList();
     
        fillArtistDetails(getArtistDetails((String)jList1.getSelectedValue()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonCreate = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jTextDesc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuManage = new javax.swing.JMenu();
        jMenuItemCustomer = new javax.swing.JMenuItem();
        jMenuItemVenue = new javax.swing.JMenuItem();
        jMenuItemDates = new javax.swing.JMenuItem();
        jMenuItemCancel = new javax.swing.JMenuItem();
        jMenuItemEvent = new javax.swing.JMenuItem();
        jMenuItemTour = new javax.swing.JMenuItem();
        jMenuExit = new javax.swing.JMenu();

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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Artists List");

        jButtonSave.setText("Save Changes");
        jButtonSave.setEnabled(false);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel Changes");
        jButtonCancel.setEnabled(false);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonCreate.setText("Create Artist");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Edit Artist");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Description");

        jTextName.setEditable(false);

        jTextDesc.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 202, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextDesc)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Manage Artists");
        jLabel4.setToolTipText("");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Events");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Tours");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jMenuManage.setText("Manage");

        jMenuItemCustomer.setText("Customers");
        jMenuItemCustomer.setToolTipText("");
        jMenuItemCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCustomerActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItemCustomer);

        jMenuItemVenue.setText("Venue");
        jMenuItemVenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVenueActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItemVenue);

        jMenuItemDates.setText("Dates");
        jMenuItemDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDatesActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItemDates);

        jMenuItemCancel.setText("Cancellations");
        jMenuItemCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItemCancel);

        jMenuItemEvent.setText("Events");
        jMenuItemEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEventActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItemEvent);

        jMenuItemTour.setText("Tours");
        jMenuItemTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTourActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItemTour);

        jMenuBar1.add(jMenuManage);

        jMenuExit.setText("Exit");
        jMenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonSave)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonCancel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEdit))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 322, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSave)
                            .addComponent(jButtonCancel)
                            .addComponent(jButtonCreate)
                            .addComponent(jButtonEdit))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(281, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        fillArtistDetails(getArtistDetails((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jList1ValueChanged

    private void jMenuItemEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEventActionPerformed
        // TODO add your handling code here:
        EventJFrame  myFrame = new   EventJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemEventActionPerformed

    private void jMenuItemTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTourActionPerformed
        // TODO add your handling code here:
        TourJFrame  myFrame = new   TourJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemTourActionPerformed

    private void jMenuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuExitMouseClicked

    private void jMenuItemVenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVenueActionPerformed
        // TODO add your handling code here:
            VenueJFrame  myFrame = new   VenueJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemVenueActionPerformed

    private void jMenuItemDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDatesActionPerformed
        // TODO add your handling code here:
          DateJFrame  myFrame = new   DateJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDatesActionPerformed

    private void jMenuItemCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelActionPerformed
        // TODO add your handling code here:
           CancellationJFrame  myFrame = new   CancellationJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemCancelActionPerformed

    private void jMenuItemCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCustomerActionPerformed
        // TODO add your handling code here:
         CustomerJFrame  myFrame = new   CustomerJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemCustomerActionPerformed

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        this.process = "add";
           this.jTextName.setText("");
        this.jTextDesc.setText("");
         
         
             this.jTextName.setEditable(true);
        this.jTextDesc.setEditable(true);
        
         
         this.jButtonCreate.setEnabled(false);
         this.jButtonSave.setEnabled(true);
         this.jButtonCancel.setEnabled(true);
         this.jButtonEdit.setEnabled(false);
         
           String[] colNames = {"Name" , "Start Time","End Time"};
         String[][] data  = new String[0][3];       
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
         
         String[] colNames2 = {"Name"};
         String[][] data2  = new String[0][1];  
          javax.swing.table.DefaultTableModel model2 = new javax.swing.table.DefaultTableModel(data2, colNames2);
         this.jTable3.setModel(model2); 
    }//GEN-LAST:event_jButtonCreateActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
        Artist a = new Artist(this.artistID,this.jTextName.getText(),jTextDesc.getText(),null,null,null);
         persistence.ArtistDAO dao  = new persistence.ArtistDAO();
      
         
         
         String result = "";
         if (this.process.equalsIgnoreCase("add"))
                result = dao.insertArtist(a);
          if (this.process.equalsIgnoreCase("edit"))
                result = dao.updateArtist(a);
         
         if (result.equalsIgnoreCase("0"))
         {
                 javax.swing.JOptionPane.showMessageDialog(null,  "Artist saved successfully",
                 "Artist Process", javax.swing.JOptionPane.INFORMATION_MESSAGE);
         
         fillArtistList();
           if (this.process.equalsIgnoreCase("add"))
              fillArtistDetails(getArtistDetails((String)jList1.getSelectedValue()));
          this.jTextName.setEditable(false);
        this.jTextDesc.setEditable(false);
     
         
         this.jButtonCreate.setEnabled(true);
         this.jButtonSave.setEnabled(false);
         this.jButtonCancel.setEnabled(false);
         this.jButtonEdit.setEnabled(true);
         
         
         }
         else
         {
             String errorMsg = "An Error occurred: " + new util.ErrorMessages().getMessage(result);
             javax.swing.JOptionPane.showMessageDialog(null, errorMsg, "error", javax.swing.JOptionPane.ERROR_MESSAGE); 
         }
         
         
         
        
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
            
            this.jButtonCreate.setEnabled(true);
         this.jButtonSave.setEnabled(false);
         this.jButtonCancel.setEnabled(false);
         this.jButtonEdit.setEnabled(true);
           
           fillArtistDetails(getArtistDetails((String)jList1.getSelectedValue()));
              this.jTextName.setEditable(false);
        this.jTextDesc.setEditable(false);
        
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        // TODO add your handling code here:
      
          this.process = "edit";
             this.jTextName.setEditable(true);
        this.jTextDesc.setEditable(true);
     
         
         this.jButtonCreate.setEnabled(false);
         this.jButtonSave.setEnabled(true);
         this.jButtonCancel.setEnabled(true);
         this.jButtonEdit.setEnabled(false);
    }//GEN-LAST:event_jButtonEditActionPerformed

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
            java.util.logging.Logger.getLogger(ArtistJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArtistJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArtistJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArtistJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArtistJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuExit;
    private javax.swing.JMenuItem jMenuItemCancel;
    private javax.swing.JMenuItem jMenuItemCustomer;
    private javax.swing.JMenuItem jMenuItemDates;
    private javax.swing.JMenuItem jMenuItemEvent;
    private javax.swing.JMenuItem jMenuItemTour;
    private javax.swing.JMenuItem jMenuItemVenue;
    private javax.swing.JMenu jMenuManage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextDesc;
    private javax.swing.JTextField jTextName;
    // End of variables declaration//GEN-END:variables
}
