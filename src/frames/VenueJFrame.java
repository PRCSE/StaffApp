/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import com.prcse.datamodel.*;
import java.text.SimpleDateFormat;
 
public class VenueJFrame extends javax.swing.JFrame {

    /**
     * Creates new form CustomerJFrame
     */
    
    //this is the process variable, it is used to know whether the
    //opearion is create or update, when the save button is clikced.
    String process = ""; 
    
    //this is where the id of current venue is saved, in order to be
    //used in update operation.
    long venueID = 0l;
    
         //retrive the venue information by venue name,
        //the getVenueDetails method in VenueDAO is called
        //the method returns Venue object
    private Venue getVenueDetails(String par){
  
        persistence.VenueDAO dao  = new persistence.VenueDAO();
        return dao.getVenueDetails(par);
    }
    
    
     //This method takes the Venue object as parameter,this Venue object 
        //is returned by getVenueDetails, and fills the data in the
        //UI controls in the screen
        
    private void fillVenueDetails(Venue v){
    
   //test if the venue is null, exit and do not do anything
        if(v==null)
            return;
    //filling the text fields with the data from the Venue object
        this.jTextName.setText(v.getName());
        this.jTextAddress1.setText(v.getAddr1());
        this.jTextPostCode.setText(v.getPostcode());  
        this.jTextDesc.setText(v.getDescription());
        this.venueID = v.getId();
        
        //SimpleDateFormat is used for formatting dates, in order not to display the time with the date
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                
      //Now fill the table with the events data, events are obtained by calling getEventList()
          // of the Venue object
         String[] colNames = {"Name" , "Start Time","End Time"};
          String[][] data  = new  String[v.getEventList().size()][3];
         for (int i =0 ; i < v.getEventList().size();i++ ){
             Event e = v.getEventList().get(i);
             data[i][0] = e.getName();
             data[i][1] = sdf.format(e.getStartTime().getTime());
             data[i][2] = sdf.format(e.getEndTime().getTime()); 
             
         }
         
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
          
          
    }
    
    
     //This method use the VenueDAO to retrieve all the Venues and fills
    // in the List control at the left side of the screen.
    private void fillVenueList(){
           persistence.VenueDAO dao  = new persistence.VenueDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getVenueList();
      for (int i = 0; i < list.length ; i++){
          model1.addElement(list[i]);
      }
      this.jList1.setModel(model1);
      jList1.setSelectedIndex(0);
    }
    
    
       //This is the constructor, it is called when the screen is first loaded,
    //it initiates the UI controls by calling initComponents() , then calls
    //fillVenueList() to populate the List with the venues, then fills the 
    //details of the current selected Venue by calling fillVenueDetails
    // and passing it the Venue object that is returned from calling getVenueDetails
    // Which takes the current selected venue in the List as a parameter
       
    public VenueJFrame() {
        initComponents();
        fillVenueList();
        fillVenueDetails(getVenueDetails((String)jList1.getSelectedValue()));
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
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextAddress1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextPostCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextDesc = new javax.swing.JTextField();
        jButtonCreate = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCustomers = new javax.swing.JMenu();
        jMenuArtist = new javax.swing.JMenu();
        jMenuVenue = new javax.swing.JMenu();
        jMenuDate = new javax.swing.JMenu();
        jMenuCancellation = new javax.swing.JMenu();
        jMenuEvents = new javax.swing.JMenu();
        jMenuTour = new javax.swing.JMenu();
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
        jLabel1.setText("Venue List");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Manage Venue");
        jLabel6.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name");

        jTextName.setEditable(false);
        jTextName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextName.setName(""); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Address 1");

        jTextAddress1.setEditable(false);
        jTextAddress1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextAddress1.setName(""); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Post Code");

        jTextPostCode.setEditable(false);
        jTextPostCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextPostCode.setName(""); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Description");

        jTextDesc.setEditable(false);
        jTextDesc.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextDesc.setName(""); // NOI18N

        jButtonCreate.setText("Create Venue");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Edit Venue");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEdit)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextDesc, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(82, 82, 82))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonCreate)
                    .addComponent(jButtonEdit))
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Events");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(522, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jMenuCustomers.setText("Customers");
        jMenuCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuCustomersMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuCustomers);

        jMenuArtist.setText("Artists");
        jMenuArtist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuArtistMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuArtist);

        jMenuVenue.setText("Venue");
        jMenuVenue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuVenueMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuVenue);

        jMenuDate.setText("Dates");
        jMenuDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuDateMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuDate);

        jMenuCancellation.setText("Cancellations");
        jMenuCancellation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuCancellationMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuCancellation);

        jMenuEvents.setText("Events");
        jMenuEvents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuEventsMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuEvents);

        jMenuTour.setText("Tours");
        jMenuTour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuTourMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuTour);

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(382, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

       //When the user selects another Venue from the list, fills its
    //details in the fields and tables.
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        fillVenueDetails(getVenueDetails((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jList1ValueChanged

     //this event handler is called when the create button is pressed
    //it empties the text fields and tables to enter the data of new venue
    //it also sets the value of process variable to be "add"
    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        // TODO add your handling code here:
         this.process = "add";
           this.jTextName.setText("");
        this.jTextDesc.setText("");
          this.jTextAddress1.setText("");
        this.jTextPostCode.setText("");  
         
             this.jTextName.setEditable(true);
        this.jTextDesc.setEditable(true);
           this.jTextAddress1.setEditable(true);
        this.jTextPostCode.setEditable(true); 
        
         
         this.jButtonCreate.setEnabled(false);
         this.jButtonSave.setEnabled(true);
         this.jButtonCancel.setEnabled(true);
         this.jButtonEdit.setEnabled(false);
         
            String[] colNames = {"Name" , "Start Time","End Time"};
          String[][] data  = new  String[0][3];  
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
          
    }//GEN-LAST:event_jButtonCreateActionPerformed

     //event handler which is called when Edit button is clicked, it enables
    //the controls so the use can change the data, and sets the process to be "edit"
    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        // TODO add your handling code here:
          this.process = "edit";
             this.jTextName.setEditable(true);
        this.jTextDesc.setEditable(true);
              this.jTextAddress1.setEditable(true);
        this.jTextPostCode.setEditable(true); 
     
         
         this.jButtonCreate.setEnabled(false);
         this.jButtonSave.setEnabled(true);
         this.jButtonCancel.setEnabled(true);
         this.jButtonEdit.setEnabled(false);
    }//GEN-LAST:event_jButtonEditActionPerformed

    //event handler which is called when the cancel button is pressed
    //it cancels the edit or create operation, undo the changes and 
    //disable the text fields.
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
           this.jButtonCreate.setEnabled(true);
         this.jButtonSave.setEnabled(false);
         this.jButtonCancel.setEnabled(false);
         this.jButtonEdit.setEnabled(true);
         
        //it calls the  fillVenueDetails to fill the information
             //of the currently selected Venue
           fillVenueDetails(getVenueDetails((String)jList1.getSelectedValue()));
           
           this.jTextName.setEditable(false);
        this.jTextDesc.setEditable(false);
               this.jTextAddress1.setEditable(false);
        this.jTextPostCode.setEditable(false); 
    }//GEN-LAST:event_jButtonCancelActionPerformed

    //This event handler is called when the Save button is pressed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        //first it creates a new Venue object and fills it with information
        //which are entered by the user in the text fields.
        Venue v = new  Venue(this.venueID,this.jTextName.getText());
         persistence.VenueDAO dao  = new persistence.VenueDAO();
         
         v.setName(this.jTextName.getText());
         v.setDescription(jTextDesc.getText());
         v.setAddr1(jTextAddress1.getText());
         v.setPostcode(jTextPostCode.getText());
         v.setId(this.venueID);
         
         
         String result = "";
           //it testes the process variable, if it is add, it will call insertVenue method
         //if it is  edit it will call updateVenue. In either case, it will pass the
         //Venue object mentioned abive as a parameter
         if (this.process.equalsIgnoreCase("add"))
                result = dao.insertVenue(v);
          if (this.process.equalsIgnoreCase("edit"))
                result = dao.updateVenue(v);
        
          
           //the result variable contains the result of the above operation, if all things
          //went correctly, then display success message to the user, and disable the text 
          //fields , enable the create and edit buttons, disable the save and cancel buttons
         if (result.equalsIgnoreCase("0"))
         {
                 javax.swing.JOptionPane.showMessageDialog(null,  "Venue saved successfully",
                 "Venue Process", javax.swing.JOptionPane.INFORMATION_MESSAGE);
         
         fillVenueList();
           if (this.process.equalsIgnoreCase("add"))
              fillVenueDetails(getVenueDetails((String)jList1.getSelectedValue()));
          this.jTextName.setEditable(false);
        this.jTextDesc.setEditable(false);
           this.jTextAddress1.setEditable(false);
        this.jTextPostCode.setEditable(false); 
     
         
         this.jButtonCreate.setEnabled(true);
         this.jButtonSave.setEnabled(false);
         this.jButtonCancel.setEnabled(false);
         this.jButtonEdit.setEnabled(true);
         
         
         }
         else
         {
             //if an error happened, display the error message which is obtained from the ErrorMessage
             //class by passing it the sql error code retruned from the DAO-->oracle DB
             String errorMsg = "An Error occurred: " + new util.ErrorMessages().getMessage(result);
             javax.swing.JOptionPane.showMessageDialog(null, errorMsg, "error", javax.swing.JOptionPane.ERROR_MESSAGE); 
         }
         
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jMenuCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuCustomersMouseClicked
        // TODO add your handling code here:
        CustomerJFrame  myFrame = new  CustomerJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuCustomersMouseClicked

    private void jMenuArtistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuArtistMouseClicked
        // TODO add your handling code here:
        ArtistJFrame  myFrame = new  ArtistJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuArtistMouseClicked

    private void jMenuDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuDateMouseClicked
        // TODO add your handling code here:
        DateJFrame  myFrame = new  DateJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuDateMouseClicked

    private void jMenuCancellationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuCancellationMouseClicked
        // TODO add your handling code here:
        CancellationJFrame  myFrame = new  CancellationJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuCancellationMouseClicked

    private void jMenuEventsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEventsMouseClicked
        // TODO add your handling code here:
        EventJFrame  myFrame = new  EventJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuEventsMouseClicked

    private void jMenuTourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuTourMouseClicked
        // TODO add your handling code here:
        TourJFrame  myFrame = new  TourJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuTourMouseClicked

    private void jMenuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuExitMouseClicked

    private void jMenuVenueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuVenueMouseClicked
        // TODO add your handling code here:
        VenueJFrame  myFrame = new  VenueJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuVenueMouseClicked

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
            java.util.logging.Logger.getLogger(VenueJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VenueJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VenueJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VenueJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VenueJFrame().setVisible(true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenuArtist;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCancellation;
    private javax.swing.JMenu jMenuCustomers;
    private javax.swing.JMenu jMenuDate;
    private javax.swing.JMenu jMenuEvents;
    private javax.swing.JMenu jMenuExit;
    private javax.swing.JMenu jMenuTour;
    private javax.swing.JMenu jMenuVenue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextAddress1;
    private javax.swing.JTextField jTextDesc;
    private javax.swing.JTextField jTextName;
    private javax.swing.JTextField jTextPostCode;
    // End of variables declaration//GEN-END:variables
}
