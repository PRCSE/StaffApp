/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import com.prcse.datamodel.*;
import java.util.HashMap;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author snowman
 */
public class TourJFrame extends javax.swing.JFrame {

    /**
     * Creates new form CustomerJFrame
     */
    private String process = "";
    private long tourID = 0l;
    private Vector<Event> getTourEvents(String par){
        if (par==null)
            return null;
        persistence.TourDAO dao  = new persistence.TourDAO();
       return dao.getTourEvents(par);
    }
    
    private Tour getTourDetails(String par){
        if (par==null)
            return null;
        persistence.TourDAO dao  = new persistence.TourDAO();
        return dao.getTourDetails(par);
    }
    
    private void fillTourDetails(Tour par){
       
        if (par==null)
            return;
        
        this.jTextField1.setText(par.getName());
       String art = par.getArtist().getArtistID() + "," + par.getArtist().getName();
       this.jComboBoxArtist.setSelectedItem(art);
       this.tourID = par.getTourID();
       
        
    }
    
    private void fillArtistList(){
           persistence.EventDAO dao  = new persistence.EventDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getArtistsList();
      this.jComboBoxArtist.setModel(new javax.swing.DefaultComboBoxModel(list));
      
    }
    
      private void fillEventList(){
           persistence.TourDAO dao  = new persistence.TourDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getEventsList();
      this.jComboBoxEvent.setModel(new javax.swing.DefaultComboBoxModel(list));
      
    }
    private void fillTourEvents(Vector<Event> par){
       if (par == null)
           return;
    
       SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        

         String[] colNames = {"Name" , "Start Time","End Time"};
         String[][] data  = new String[par.size()][3];
         for (int i =0 ; i < par.size();i++ ){
             Event e = (Event) par.get(i);
             data[i][0] = e.getName();
             data[i][1] = sdf.format(e.getStartTime().getTime());
             data[i][2] = sdf.format(e.getEndTime().getTime());          
         }
         
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
         
     
          
    }
    private void fillTourList(){
            persistence.TourDAO dao  = new persistence.TourDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getToursList();
      for (int i = 0; i < list.length ; i++){
          model1.addElement(list[i]);
      }
      this.jList1.setModel(model1);
      jList1.setSelectedIndex(0);
    }
    public TourJFrame() {
        initComponents();
        fillTourList();
        fillArtistList();
        fillTourEvents(getTourEvents((String)jList1.getSelectedValue()));
        fillTourDetails(getTourDetails((String)jList1.getSelectedValue()));
        fillEventList();
        this.jPanel2.setVisible(false);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxArtist = new javax.swing.JComboBox();
        jButtonSaveTour = new javax.swing.JButton();
        jButtonCancelTour = new javax.swing.JButton();
        jButtonCreateTour = new javax.swing.JButton();
        jButtonEditTour = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxEvent = new javax.swing.JComboBox();
        jButtonAddEvent = new javax.swing.JButton();
        jButtonCancelEvent = new javax.swing.JButton();
        jButtonSaveEvent = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuManage1 = new javax.swing.JMenu();
        jMenuItemCustomer1 = new javax.swing.JMenuItem();
        jMenuItemVenue = new javax.swing.JMenuItem();
        jMenuItemDates = new javax.swing.JMenuItem();
        jMenuItemDate = new javax.swing.JMenuItem();
        jMenuItemCancel = new javax.swing.JMenuItem();
        jMenuItemEvent = new javax.swing.JMenuItem();
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
        jLabel1.setText("Tours List");

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

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Events");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("Manage Tours");
        jLabel15.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Name");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField1.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Artist");

        jComboBoxArtist.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxArtist.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxArtist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxArtist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonSaveTour.setText("Save Tour");
        jButtonSaveTour.setEnabled(false);
        jButtonSaveTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveTourActionPerformed(evt);
            }
        });

        jButtonCancelTour.setText("Cancel Changes");
        jButtonCancelTour.setEnabled(false);
        jButtonCancelTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelTourActionPerformed(evt);
            }
        });

        jButtonCreateTour.setText("Create Tour");
        jButtonCreateTour.setToolTipText("");
        jButtonCreateTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateTourActionPerformed(evt);
            }
        });

        jButtonEditTour.setText("Edit Tour");
        jButtonEditTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditTourActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Event");

        jComboBoxEvent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jButtonAddEvent.setText("Add Event");
        jButtonAddEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddEventActionPerformed(evt);
            }
        });

        jButtonCancelEvent.setText("Cancel Changes");
        jButtonCancelEvent.setEnabled(false);
        jButtonCancelEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelEventActionPerformed(evt);
            }
        });

        jButtonSaveEvent.setText("Save Event");
        jButtonSaveEvent.setEnabled(false);
        jButtonSaveEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveEventActionPerformed(evt);
            }
        });

        jMenuManage1.setText("Manage");

        jMenuItemCustomer1.setText("Customers");
        jMenuItemCustomer1.setToolTipText("");
        jMenuItemCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCustomer1ActionPerformed(evt);
            }
        });
        jMenuManage1.add(jMenuItemCustomer1);

        jMenuItemVenue.setText("Artists");
        jMenuItemVenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVenueActionPerformed(evt);
            }
        });
        jMenuManage1.add(jMenuItemVenue);

        jMenuItemDates.setText("Venue");
        jMenuItemDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDatesActionPerformed(evt);
            }
        });
        jMenuManage1.add(jMenuItemDates);

        jMenuItemDate.setText("Dates");
        jMenuItemDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDateActionPerformed(evt);
            }
        });
        jMenuManage1.add(jMenuItemDate);

        jMenuItemCancel.setText("Cancellations");
        jMenuItemCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelActionPerformed(evt);
            }
        });
        jMenuManage1.add(jMenuItemCancel);

        jMenuItemEvent.setText("Events");
        jMenuItemEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEventActionPerformed(evt);
            }
        });
        jMenuManage1.add(jMenuItemEvent);

        jMenuBar2.add(jMenuManage1);

        jMenuExit.setText("Exit");
        jMenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuExitMouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenuExit);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAddEvent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelEvent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSaveEvent))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonSaveTour)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonCancelTour)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonCreateTour)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonEditTour))
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(499, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSaveTour)
                            .addComponent(jButtonCancelTour)
                            .addComponent(jButtonCreateTour)
                            .addComponent(jButtonEditTour))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddEvent)
                    .addComponent(jButtonCancelEvent)
                    .addComponent(jButtonSaveEvent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        fillTourEvents(getTourEvents((String)jList1.getSelectedValue()));
          fillTourDetails(getTourDetails((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jList1ValueChanged

    private void jMenuItemCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCustomer1ActionPerformed
        // TODO add your handling code here:
        CustomerJFrame  myFrame = new   CustomerJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemCustomer1ActionPerformed

    private void jMenuItemVenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVenueActionPerformed
        // TODO add your handling code here:
        ArtistJFrame  myFrame = new   ArtistJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemVenueActionPerformed

    private void jMenuItemDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDatesActionPerformed
        // TODO add your handling code here:
        VenueJFrame  myFrame = new   VenueJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDatesActionPerformed

    private void jMenuItemDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDateActionPerformed
        // TODO add your handling code here:
        DateJFrame  myFrame = new   DateJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDateActionPerformed

    private void jMenuItemCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelActionPerformed
        // TODO add your handling code here:
        CancellationJFrame  myFrame = new   CancellationJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemCancelActionPerformed

    private void jMenuItemEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEventActionPerformed
        // TODO add your handling code here:
        EventJFrame  myFrame = new   EventJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemEventActionPerformed

    private void jMenuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuExitMouseClicked

    private void jButtonCreateTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateTourActionPerformed
        // TODO add your handling code here:
         this.process = "add";
           this.jTextField1.setText("");
           
         
             this.jTextField1.setEditable(true);
          this.jComboBoxArtist.setEditable(true);
          this.jComboBoxArtist.setEnabled(true);
          
          this.jButtonCancelTour.setEnabled(true);
          this.jButtonCreateTour.setEnabled(false);
          this.jButtonEditTour.setEnabled(false);
          this.jButtonSaveTour.setEnabled(true);
         
             String[] colNames = {"Name" , "Start Time","End Time"};
         java.util.Date[][] data  = new java.util.Date[0][3];     
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
    }//GEN-LAST:event_jButtonCreateTourActionPerformed

    private void jButtonEditTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditTourActionPerformed
        // TODO add your handling code here:
            this.process = "edit";
        
                this.jButtonCancelTour.setEnabled(true);
          this.jButtonCreateTour.setEnabled(false);
          this.jButtonEditTour.setEnabled(false);
          this.jButtonSaveTour.setEnabled(true);
         
             this.jTextField1.setEditable(true);
          this.jComboBoxArtist.setEditable(true);
          this.jComboBoxArtist.setEnabled(true);
         
    }//GEN-LAST:event_jButtonEditTourActionPerformed

    private void jButtonSaveTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveTourActionPerformed
        // TODO add your handling code here:
        
        Tour t = new Tour(null);
         persistence.TourDAO dao  = new persistence.TourDAO();
         t.setName(this.jTextField1.getText());
         t.setTourID(this.tourID);
         String arr[]= this.jComboBoxArtist.getSelectedItem().toString().split(",");
         Artist a = new Artist(null);
         a.setArtistID(Long.parseLong(arr[0]));
         t.setArtist(a);
         
         
         String result = "";
         if (this.process.equalsIgnoreCase("add"))
                result = dao.insertTour(t);
          if (this.process.equalsIgnoreCase("edit"))
                result = dao.updateTour(t);
         
         if (result.equalsIgnoreCase("0"))
         {
                 javax.swing.JOptionPane.showMessageDialog(null,  "Tour saved successfully",
                 "Tour Process", javax.swing.JOptionPane.INFORMATION_MESSAGE);
         
         fillTourList();
           if (this.process.equalsIgnoreCase("add")){
              fillTourDetails(getTourDetails((String)jList1.getSelectedValue()));
              this.fillTourEvents(this.getTourEvents((String)jList1.getSelectedValue()));
           }
            this.jTextField1.setEditable(false);
          this.jComboBoxArtist.setEditable(false);
          this.jComboBoxArtist.setEnabled(false);
         
           this.jButtonCancelTour.setEnabled(false);
          this.jButtonCreateTour.setEnabled(true);
          this.jButtonEditTour.setEnabled(true);
          this.jButtonSaveTour.setEnabled(false);
         
         
         }
         else
         {
             String errorMsg = "An Error occurred: " + new util.ErrorMessages().getMessage(result);
             javax.swing.JOptionPane.showMessageDialog(null, errorMsg, "error", javax.swing.JOptionPane.ERROR_MESSAGE); 
         }
         
         
         
    }//GEN-LAST:event_jButtonSaveTourActionPerformed

    private void jButtonCancelTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelTourActionPerformed
        // TODO add your handling code here:
         if(process.equalsIgnoreCase("add")){
            jList1.setSelectedIndex(0);
            fillTourDetails(getTourDetails((String)jList1.getSelectedValue()));
             this.fillTourEvents(this.getTourEvents((String)jList1.getSelectedValue()));
        }
        
        if(process.equalsIgnoreCase("edit")){
                 fillTourDetails(getTourDetails((String)jList1.getSelectedValue()));
             this.fillTourEvents(this.getTourEvents((String)jList1.getSelectedValue()));
        }
        
          this.jTextField1.setEditable(false);
          this.jComboBoxArtist.setEditable(false);
          this.jComboBoxArtist.setEnabled(false);
         
    this.jButtonCancelTour.setEnabled(false);
          this.jButtonCreateTour.setEnabled(true);
          this.jButtonEditTour.setEnabled(true);
          this.jButtonSaveTour.setEnabled(false);
    }//GEN-LAST:event_jButtonCancelTourActionPerformed

    private void jButtonAddEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddEventActionPerformed
        // TODO add your handling code here:
            this.jButtonAddEvent.setEnabled(false);
        this.jButtonSaveEvent.setEnabled(true);
           this.jButtonCancelEvent.setEnabled(true);
        this.jPanel2.setVisible(true);  
    }//GEN-LAST:event_jButtonAddEventActionPerformed

    private void jButtonSaveEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveEventActionPerformed
        // TODO add your handling code here:
              
          String[] artistArr = this.jComboBoxArtist.getSelectedItem().toString().split(",");
          String[] eventArr = this.jComboBoxEvent.getSelectedItem().toString().split(",");
        Event e = new Event(null,null,null);
        e.setEventID(Long.parseLong(eventArr[0]));
          
        Artist a = new Artist(null);
        a.setArtistID(Long.parseLong(artistArr[0]));
         Tour t = new Tour(null,a);
        t.setTourID(this.tourID);
          Billing b = new Billing(a);
          b.setLineupOrder(1);
        t.addBill(b);
        
   
        persistence.EventDAO dao = new persistence.EventDAO();
        dao.addBilling(t, e);
        
        
          this.jButtonAddEvent.setEnabled(true);
        this.jButtonSaveEvent.setEnabled(false);
           this.jButtonCancelEvent.setEnabled(false);
        this.jPanel2.setVisible(false);
            fillTourEvents(getTourEvents((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jButtonSaveEventActionPerformed

    private void jButtonCancelEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelEventActionPerformed
        // TODO add your handling code here:
            this.jButtonAddEvent.setEnabled(true);
        this.jButtonSaveEvent.setEnabled(false);
           this.jButtonCancelEvent.setEnabled(false);
        this.jPanel2.setVisible(false);
    }//GEN-LAST:event_jButtonCancelEventActionPerformed

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
            java.util.logging.Logger.getLogger(TourJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TourJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TourJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TourJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TourJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddEvent;
    private javax.swing.JButton jButtonCancelEvent;
    private javax.swing.JButton jButtonCancelTour;
    private javax.swing.JButton jButtonCreateTour;
    private javax.swing.JButton jButtonEditTour;
    private javax.swing.JButton jButtonSaveEvent;
    private javax.swing.JButton jButtonSaveTour;
    private javax.swing.JComboBox jComboBoxArtist;
    private javax.swing.JComboBox jComboBoxEvent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuExit;
    private javax.swing.JMenuItem jMenuItemCancel;
    private javax.swing.JMenuItem jMenuItemCustomer1;
    private javax.swing.JMenuItem jMenuItemDate;
    private javax.swing.JMenuItem jMenuItemDates;
    private javax.swing.JMenuItem jMenuItemEvent;
    private javax.swing.JMenuItem jMenuItemVenue;
    private javax.swing.JMenu jMenuManage1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}