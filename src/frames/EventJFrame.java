/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import com.prcse.datamodel.Venue;
import com.prcse.datamodel.Event;
import com.prcse.datamodel.Billing;
import com.prcse.datamodel.Artist;
import com.prcse.datamodel.Customer;
import com.prcse.datamodel.Tour;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

 
public class EventJFrame extends javax.swing.JFrame {

    /**
     * Creates new form EventJFrame
     */
    
     //this is the process variable, it is used to know whether the
    //opearion is create or update, when the save button is clikced.
    private String process = "";
    
     //this is where the id of current event is saved, in order to be
    //used in update operation.
    private long eventID = 0l;
     
      //This is the constructor, it is called when the screen is first loaded,
    //it initiates the UI controls by calling initComponents() , then calls
    //fillEventList() to populate the List with the customers, then fills the 
    //details of the current selected Event by calling fillEventDetails
    // and passing it the Event object that is returned from calling getEventDetails
    // Which takes the current selected customer in the List as a parameter
      //The constructor also calls fillVenueList() & fillArtistList() & fillTourList() to fill the 
      //Venue and Artist and Tour drop down lists respectively
    //Finally it hides panel3 which contains the fields of entering new billing entry
    public EventJFrame() {
        initComponents();
        fillEventList();
        fillEventDetails(getEventDetails((String)jList1.getSelectedValue()));
        fillArtistList();
        fillVenueList();
         fillTourList();
        this.jPanel3.setVisible(false);
    }
    
        //This method use the EventDAO to retrieve all the Artists and fills
    // in the Combo Box (drop down list) control which displays the Artist. 
          private void fillArtistList(){
           persistence.EventDAO dao  = new persistence.EventDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getArtistsList();
      this.jComboBoxArtist.setModel(new javax.swing.DefaultComboBoxModel(list));
      
    }
         
          
       //This method use the EventDAO to retrieve all the Venues and fills
    // in the Combo Box (drop down list) control which displays the Venue. 
        private void fillVenueList(){
           persistence.EventDAO dao  = new persistence.EventDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getVenueList();
      this.jComboBoxVenue.setModel(new javax.swing.DefaultComboBoxModel(list));
      
    }
        
      
         //This method use the EventDAO to retrieve all the Events and fills
    // in the Combo Box (drop down list) control which displays the Event.     
                private void fillTourList(){
           persistence.EventDAO dao  = new persistence.EventDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getToursList();
      this.jComboBoxTour.setModel(new javax.swing.DefaultComboBoxModel(list));
      
    }
              
                
      //retrive the event information by event start & end dates
        //the getEventDetails method in EventDAO is called
        //the method returns Event object
     private Event getEventDetails(String par){
       
          if(par == null)
              return null;
        persistence.EventDAO dao  = new persistence.EventDAO();
       return dao.getEventDetails(par);
    }
     
     
       //This method takes the Event object as parameter,this Eventobject 
        //is returned by getEventDetails, and fills the data in the
        //UI controls in the screen
        
    private void fillEventDetails(Event par){
         //SimpleDateFormat is used for formatting dates, in order not to display the time with the date
           SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            //test if the event is null, exit and do not do anything
           if(par == null)
               return;
         //filling the text fields with the data from the Customer object
        this.jTextName.setText(par.getName());
        this.dateChooserStartDate.setText(sdf.format(par.getStartTime().getTime()));
        this.dateChooserEndDate.setText(sdf.format(par.getEndTime().getTime()));
        String venueVal = par.getVenue().getId() + "," + par.getVenue().getName();
        this.jComboBoxVenue.setSelectedItem(venueVal);
        this.eventID = par.getId();
         
        
       //Now fill the table with the billings data, bookings are obtained by calling getBillings()
          // of the Event object 
         String[] colNames = {"Artist,Tour" , "lineup order"};
         String[][] data  = new String[par.getBillings().size()][2];
         for (int i =0 ; i < par.getBillings().size();i++ ){
             Billing b = (Billing) par.getBillings().get(i);
             data[i][0] = b.getArtist().getName();
             data[i][1] = String.valueOf(b.getLineupOrder());
             
                    
         }
         
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable1.setModel(model);
         

    
          
    }
    
    
    //This method use the EventDAO to retrieve all the Events and fills
    // in the List control at the left side of the screen.
     private void fillEventList(){
           persistence.EventDAO dao  = new persistence.EventDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getEventsList();
      for (int i = 0; i < list.length ; i++){
          model1.addElement(list[i]);
      }
      this.jList1.setModel(model1);
      jList1.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateChooserStartDate = new datechooser.beans.DateChooserCombo();
        dateChooserEndDate = new datechooser.beans.DateChooserCombo();
        jComboBoxVenue = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonSaveEvent = new javax.swing.JButton();
        jButtonCancelEvent = new javax.swing.JButton();
        jButtonCreate = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonAddBilling = new javax.swing.JButton();
        jButtonCancelBilling = new javax.swing.JButton();
        jButtonSaveBilling = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxArtist = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldLineup = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxTour = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuManage1 = new javax.swing.JMenu();
        jMenuItemCustomer1 = new javax.swing.JMenuItem();
        jMenuItemVenue = new javax.swing.JMenuItem();
        jMenuItemDates = new javax.swing.JMenuItem();
        jMenuItemDate = new javax.swing.JMenuItem();
        jMenuItemCancel = new javax.swing.JMenuItem();
        jMenuItemTour = new javax.swing.JMenuItem();
        jMenuExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Events List");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name");

        jTextName.setEditable(false);
        jTextName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Start date");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("End date");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Venue");

        dateChooserStartDate.setCalendarPreferredSize(new java.awt.Dimension(350, 350));
        dateChooserStartDate.setEnabled(false);

        dateChooserEndDate.setCalendarPreferredSize(new java.awt.Dimension(350, 350));
        dateChooserEndDate.setEnabled(false);

        jComboBoxVenue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxVenue.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxVenue, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(dateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxVenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(124, 124, 124))
        );

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Lineup");

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

        jButtonSaveEvent.setText("Save Event");
        jButtonSaveEvent.setEnabled(false);
        jButtonSaveEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveEventActionPerformed(evt);
            }
        });

        jButtonCancelEvent.setText("Cancel Changes");
        jButtonCancelEvent.setEnabled(false);
        jButtonCancelEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelEventActionPerformed(evt);
            }
        });

        jButtonCreate.setText("Create Event");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Edit Event");
        jButtonEdit.setToolTipText("");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jButtonAddBilling.setText("Add to Lineup");
        jButtonAddBilling.setToolTipText("");
        jButtonAddBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddBillingActionPerformed(evt);
            }
        });

        jButtonCancelBilling.setText("Cancel Changes");
        jButtonCancelBilling.setToolTipText("");
        jButtonCancelBilling.setEnabled(false);
        jButtonCancelBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelBillingActionPerformed(evt);
            }
        });

        jButtonSaveBilling.setText("Save Lineup");
        jButtonSaveBilling.setEnabled(false);
        jButtonSaveBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveBillingActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Artist");

        jComboBoxArtist.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Lineup Order");

        jTextFieldLineup.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Tour List");

        jComboBoxTour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLineup, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTour, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxArtist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldLineup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxTour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 102));
        jLabel14.setText("Manage Events");
        jLabel14.setToolTipText("");

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

        jMenuItemTour.setText("Tours");
        jMenuItemTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTourActionPerformed(evt);
            }
        });
        jMenuManage1.add(jMenuItemTour);

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
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButtonSaveEvent)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonCancelEvent)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonCreate)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonEdit))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButtonAddBilling)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonCancelBilling)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonSaveBilling))
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSaveEvent)
                            .addComponent(jButtonCancelEvent)
                            .addComponent(jButtonCreate)
                            .addComponent(jButtonEdit))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddBilling)
                    .addComponent(jButtonCancelBilling)
                    .addComponent(jButtonSaveBilling))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     //When the user selects another Event from the list, fills its
    //details in the fields and tables. 
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
         fillEventDetails(getEventDetails((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jList1ValueChanged
     //this is called when the Customer menu item is selected from the Manage menu.
    private void jMenuItemCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCustomer1ActionPerformed
        // TODO add your handling code here:
        CustomerJFrame  myFrame = new   CustomerJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemCustomer1ActionPerformed
     //this is called when the Artist menu item is selected from the Manage menu.
    private void jMenuItemVenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVenueActionPerformed
        // TODO add your handling code here:
        ArtistJFrame  myFrame = new   ArtistJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemVenueActionPerformed
    
     //this is called when the Venue menu item is selected from the Manage menu.
    private void jMenuItemDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDatesActionPerformed
        // TODO add your handling code here:
        VenueJFrame  myFrame = new   VenueJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDatesActionPerformed

     //this is called when the Date menu item is selected from the Manage menu.
    private void jMenuItemDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDateActionPerformed
        // TODO add your handling code here:
        DateJFrame  myFrame = new   DateJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDateActionPerformed

     //this is called when the cancellations menu item is selected from the Manage menu.
    private void jMenuItemCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelActionPerformed
        // TODO add your handling code here:
        CancellationJFrame  myFrame = new   CancellationJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemCancelActionPerformed

    
     //this is called when the Tour menu item is selected from the Manage menu.
    private void jMenuItemTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTourActionPerformed
        // TODO add your handling code here:
        TourJFrame  myFrame = new   TourJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemTourActionPerformed

     //this is called when the Exit menu is clicked to exit the application
    private void jMenuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuExitMouseClicked

    //this event handler is called when the create button is pressed
    //it empties the text fields and tables to enter the data of new event
    //it also sets the value of process variable to be "add"
    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        // TODO add your handling code here:
        process = "add";
         this.jTextName.setText("");
        this.dateChooserStartDate.setText("");
        this.dateChooserEndDate.setText("");
         
        
        jTextName.setEditable(true);
        dateChooserStartDate.setEnabled(true);
        dateChooserEndDate.setEnabled(true);
       this.jComboBoxVenue.setEnabled(true);
        
        this.jButtonEdit.setEnabled(false);
        this.jButtonCancelEvent.setEnabled(true);
        this.jButtonSaveEvent.setEnabled(true);
        this.jButtonCreate.setEnabled(false);
        this.jButtonAddBilling.setEnabled(false);
 
        
             String[] colNames = {"Artist" , "lineup order"};
         String[][] data  = new String[0][2];
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable1.setModel(model);
        
        
    }//GEN-LAST:event_jButtonCreateActionPerformed
    //This event handler is called when the Save button is pressed
    private void jButtonSaveEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveEventActionPerformed
        // TODO add your handling code here:
        
        //first it creates a new Venue object and sets its id to the values selected
        //in the venue drop down list
        Venue v = new Venue(null,0,0);
        String[] arr = this.jComboBoxVenue.getSelectedItem().toString().split(",");
        v.setId(Long.parseLong(arr[0]));
        v.setName(arr[1]);
     
      
                 persistence.EventDAO eventdao = new persistence.EventDAO();
                 String result2="";
           // it creates a new Event object and fills it with information
           //which are entered by the user in the text fields.
                 Event currentevent = new Event(0l,this.jTextName.getText(),this.dateChooserStartDate.getSelectedDate().getTime(),this.dateChooserEndDate.getSelectedDate().getTime());
             //it testes the process variable, if it is add, it will call insertSeatingPlan method
             //from VenueDAO and insertEvent from EventDAO passing the above mentioned Venue object , Event object repsectively
         //if it is  edit it will call updateEvent.     
                 
                 if(process.equalsIgnoreCase("add")) 
                  {   
                     persistence.VenueDAO dao = new persistence.VenueDAO();
                    dao.insertSeatingPlan(v);             
                    result2 = eventdao.indertEvent(currentevent );
                  }
                     if(process.equalsIgnoreCase("edit")) 
                     {
                         currentevent.setId(this.eventID);
                         result2 = eventdao.updateEvent(currentevent );
                     }
             //the result2 variable contains the result of the above operation, if all things
          //went correctly, then display success message to the user, and disable the text 
          //fields , enable the create and edit buttons, disable the save and cancel buttons  
                if(result2.equalsIgnoreCase("0")){
                    
                        javax.swing.JOptionPane.showMessageDialog(null,  "Event Data saved successfully",
                 "Event Process", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        
                        this.fillEventList();
                         if (this.process.equalsIgnoreCase("add"))  
                           fillEventDetails(getEventDetails((String)jList1.getSelectedValue()));
                         
                     jTextName.setEditable(false);
                     dateChooserStartDate.setEnabled(false);
                     dateChooserEndDate.setEnabled(false);
                    this.jComboBoxVenue.setEnabled(false);
        
                    this.jButtonEdit.setEnabled(true);
                    this.jButtonCancelEvent.setEnabled(false);
                    this.jButtonSaveEvent.setEnabled(false);
                    this.jButtonCreate.setEnabled(true);
                      this.jButtonAddBilling.setEnabled(true);
                }
                else
                {
                 //if an error happened, display the error message which is obtained from the ErrorMessage
             //class by passing it the sql error code retruned from the DAO-->oracle DB
                         javax.swing.JOptionPane.showMessageDialog(null,  "An Error Occurred: " +  new util.ErrorMessages().getMessage(result2),
                 "Event Process", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
                
                
        
        
    }//GEN-LAST:event_jButtonSaveEventActionPerformed

       //event handler which is called when Edit button is clicked, it enables
    //the controls so the use can change the data, and sets the process to be "edit"
    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        // TODO add your handling code here:
          this.process = "edit";
        jTextName.setEditable(true);
        dateChooserStartDate.setEnabled(true);
        dateChooserEndDate.setEnabled(true);
           this.jComboBoxVenue.setEnabled(true);
        
        this.jButtonEdit.setEnabled(false);
        this.jButtonCancelEvent.setEnabled(true);
        this.jButtonSaveEvent.setEnabled(true);
        this.jButtonCreate.setEnabled(false);
          this.jButtonAddBilling.setEnabled(false);
    
    }//GEN-LAST:event_jButtonEditActionPerformed

    
    //This event handler is called when the Add Billing button is clicked, it enables 
    //the save & Cancel billing buttons, disable the Add Billing Button and makes the 
    //Panel3 , which contains the billing entry fields, visible, as it is set initiallly
    //to be invisible
    private void jButtonAddBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddBillingActionPerformed
        // TODO add your handling code here:
        this.jPanel3.setVisible(true);
           this.jButtonEdit.setEnabled(false);
        this.jButtonCreate.setEnabled(false);
          this.jButtonAddBilling.setEnabled(false);
          this.jButtonCancelBilling.setEnabled(true);
          this.jButtonSaveBilling.setEnabled(true);
          
        
    }//GEN-LAST:event_jButtonAddBillingActionPerformed

     //This event handler is called when the Save Billing Button is clicked
    private void jButtonSaveBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveBillingActionPerformed
        // TODO add your handling code here:
        //obtain the value selected in the artist list, split it to get the artist id
        String[] artistArr = this.jComboBoxArtist.getSelectedItem().toString().split(",");
        //obtain the value selected in the tour list, split it to get the tour id
        String[] tourArr = this.jComboBoxTour.getSelectedItem().toString().split(",");
        
            //creates event object with the current event id 
        Event e = new Event(this.eventID,null,null,null);
       
      //Create Artist object with the id of the Artist selected from the drop down list
        Artist a = new Artist(Long.parseLong(artistArr[0]),null,null,null,null,null);
        a.setId(Long.parseLong(artistArr[0]));
        //Create Tour object with the if of the Tour selected from the tours drop down list.
         Tour t = new Tour(Long.parseLong(tourArr[0]),null,a);
        t.setId(Long.parseLong(tourArr[0]));
        //creating a Billing object for the Artist object created above, which is passed to the constructor
          Billing b = new Billing(0l,a,null,0);
          b.setLineupOrder(Integer.parseInt(this.jTextFieldLineup.getText()));
          //add the billing object created in the previous step to the Tour object
        t.addBill(b);
        
       //send the billing object to the database by calling addBilling method from EventDAO
        //passing the Tour and Event objects as parameters
        persistence.EventDAO dao = new persistence.EventDAO();
        dao.addBilling(t, e);
        
        //enable the Add Billing button, disable the Save & Cancel Billing Buttons, and hide the
        // panel which contains the billing data entry fields
        this.jPanel3.setVisible(false);
           this.jButtonEdit.setEnabled(true);
        this.jButtonCreate.setEnabled(true);
          this.jButtonAddBilling.setEnabled(true);
          this.jButtonCancelBilling.setEnabled(false);
          this.jButtonSaveBilling.setEnabled(false);
         //refresh the data by calling  fillEventDetails again.
            fillEventDetails(getEventDetails((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jButtonSaveBillingActionPerformed

      //This event handler is invoked when the Cancel Billing button is clicked, it 
    //enable the Add Billing button, disable the Save & Cancel Billing Buttons,
    //and hide the panel which contains the billing data entry fields
    private void jButtonCancelBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelBillingActionPerformed
        // TODO add your handling code here:
         this.jPanel3.setVisible(false);
           this.jButtonEdit.setEnabled(true);
        this.jButtonCreate.setEnabled(true);
          this.jButtonAddBilling.setEnabled(true);
          this.jButtonCancelBilling.setEnabled(false);
          this.jButtonSaveBilling.setEnabled(false);
    }//GEN-LAST:event_jButtonCancelBillingActionPerformed

        //event handler which is called when the cancel button is pressed
    //it cancels the edit or create operation, undo the changes and 
    //disable the text fields.
    private void jButtonCancelEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelEventActionPerformed
        // TODO add your handling code here:
        
              //it calls the  fillEventDetails to fill the information
             //of the currently selected Event
          fillEventDetails(getEventDetails((String)jList1.getSelectedValue()));
                     jTextName.setEditable(false);
                     dateChooserStartDate.setEnabled(false);
                     dateChooserEndDate.setEnabled(false);
                        this.jComboBoxVenue.setEnabled(false);
        
                    this.jButtonEdit.setEnabled(true);
                    this.jButtonCancelEvent.setEnabled(false);
                    this.jButtonSaveEvent.setEnabled(false);
                    this.jButtonCreate.setEnabled(true);
                      this.jButtonAddBilling.setEnabled(true);
                      
                      
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
            java.util.logging.Logger.getLogger(EventJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserEndDate;
    private datechooser.beans.DateChooserCombo dateChooserStartDate;
    private javax.swing.JButton jButtonAddBilling;
    private javax.swing.JButton jButtonCancelBilling;
    private javax.swing.JButton jButtonCancelEvent;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonSaveBilling;
    private javax.swing.JButton jButtonSaveEvent;
    private javax.swing.JComboBox jComboBoxArtist;
    private javax.swing.JComboBox jComboBoxTour;
    private javax.swing.JComboBox jComboBoxVenue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuExit;
    private javax.swing.JMenuItem jMenuItemCancel;
    private javax.swing.JMenuItem jMenuItemCustomer1;
    private javax.swing.JMenuItem jMenuItemDate;
    private javax.swing.JMenuItem jMenuItemDates;
    private javax.swing.JMenuItem jMenuItemTour;
    private javax.swing.JMenuItem jMenuItemVenue;
    private javax.swing.JMenu jMenuManage1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldLineup;
    private javax.swing.JTextField jTextName;
    // End of variables declaration//GEN-END:variables
}
