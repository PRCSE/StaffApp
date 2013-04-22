/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import com.prcse.datamodel.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
 
public class CustomerJFrame extends javax.swing.JFrame {

    /**
     * Creates new form CustomerJFrame
     */
    //this is the process variable, it is used to know whether the
    //opearion is create or update, when the save button is clikced.
    private String process = "";
    
    //this is where the id of current customer is saved, in order to be
    //used in update operation.
    private Long customerID = 0l;
    
     //retrive the customer information by customer first name & last name,
        //the getCustomerDetails method in CustomerDAO is called
        //the method returns Customer object
    private Customer getCustomerDetails(String par){
        if(par==null)
            return null;
        String arr[] = par.split(" ");
        persistence.CustomerDAO dao  = new persistence.CustomerDAO();
       return dao.getCustomerDetails(arr[1], arr[2]);
    }
    
    
       //This method takes the Customer object as parameter,this Customer object 
        //is returned by getCustomerDetails, and fills the data in the
        //UI controls in the screen
        
    private void fillCustomerDetails(Customer c){
     //test if the customer is null, exit and do not do anything
        if (c==null)
            return;
      //SimpleDateFormat is used for formatting dates, in order not to display the time with the date
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
       //filling the text fields with the data from the Customer object
        this.jTextTitle.setText(c.getTitle());
        this.jTextFirstName.setText(c.getForename());
        this.jTextLastName.setText(c.getSurname());
        this.jTextTel.setText(c.getTelephone());
        this.jTextMobile.setText(c.getMobile());
        this.jTextAddress1.setText(c.getAddr1());
         this.jTextAddress2.setText(c.getAddr2());
         this.jTextCity.setText(c.getTown());
         jComboBoxCountry.setSelectedItem(c.getCountry());
         this.jTextCounty.setText(c.getCounty());
         this.jTextCreated.setText(sdf.format(c.getCreated()));
         this.jTextPostCode.setText(c.getPostcode());
         
          customerID = c.getId();
        
       //Now fill the table with the bookings data, bookings are obtained by calling getBookings()
          // of the Customer object
         String[] colNames = {"Created" , "Created Confirmed","Cancelled","Cancelled Confirmed"};
         java.util.Date[][] data  = new java.util.Date[c.getBookings().size()][4];
         for (int i =0 ; i < c.getBookings().size();i++ ){
             Booking b = c.getBookings().get(i);
             data[i][0] = b.getCreated();
             data[i][1] = b.getConfirmed();
             data[i][2] = b.getCancelled();
             data[i][3]= b.getCancelConfirmed();
             
         }
         
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
          
          
    }
    
        //This method use the CustomerDAO to retrieve all the Customers and fills
    // in the List control at the left side of the screen.
    private void fillCustomerList(){
           persistence.CustomerDAO dao  = new persistence.CustomerDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getCustomersList();
      for (int i = 0; i < list.length ; i++){
          model1.addElement(list[i]);
      }
      this.jList1.setModel(model1);
      jList1.setSelectedIndex(0);
      
    }
    
         //This method use the CustomerDAO to retrieve all the Countries and fills
    // in the Combo Box (drop down list) control which displays the Country. 
      private void fillCountryList(){
           persistence.CustomerDAO dao  = new persistence.CustomerDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getCountryList();
      this.jComboBoxCountry.setModel(new javax.swing.DefaultComboBoxModel(list));
      
    }
      
         //This method use the CustomerDAO to retrieve all the Events and fills
    // in the Combo Box (drop down list) control which displays the Events. 
      //The event drop down list is initially hidden, but it appears when the
      //user clicks the Add Booking button.
      private void fillEventList(){
           persistence.CustomerDAO dao  = new persistence.CustomerDAO();
      javax.swing.DefaultListModel model1 = new javax.swing.DefaultListModel();
      String[] list = dao.getEventList();
      this.jComboBoxEvent.setModel(new javax.swing.DefaultComboBoxModel(list));
      
    }
    
       //This is the constructor, it is called when the screen is first loaded,
    //it initiates the UI controls by calling initComponents() , then calls
    //fillCustomerList() to populate the List with the customers, then fills the 
    //details of the current selected Customer by calling fillCustomerDetails
    // and passing it the Customer object that is returned from calling getCustomerDetails
    // Which takes the current selected customer in the List as a parameter
      //The constructor also calls fillCountryList() & fillEventList() to fill the 
      //country and event drop down lists respectively
      
    public CustomerJFrame() {
        initComponents();
        fillCustomerList();
         fillCountryList();
         fillEventList();
        fillCustomerDetails(getCustomerDetails((String)jList1.getSelectedValue()));
       this.jPanel1.setVisible(false);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButtonAddBooking = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextTitle = new javax.swing.JTextField();
        jTextFirstName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextLastName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextAddress1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextAddress2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextCity = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextTel = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextMobile = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextPostCode = new javax.swing.JTextField();
        jTextCounty = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextCreated = new javax.swing.JTextField();
        jComboBoxCountry = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxEvent = new javax.swing.JComboBox();
        jButtonSaveBooking = new javax.swing.JButton();
        jButtonCancelBooking = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuManage = new javax.swing.JMenu();
        jMenuItemArtist = new javax.swing.JMenuItem();
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
        jLabel1.setText("Customer List");

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

        jButtonCreate.setText("Create Customer");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Edit Customer");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

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

        jButtonAddBooking.setText("Add Booking");
        jButtonAddBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddBookingActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Bookings");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Title");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("First Name");

        jTextTitle.setEditable(false);
        jTextTitle.setToolTipText("");

        jTextFirstName.setEditable(false);
        jTextFirstName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextFirstName.setToolTipText("");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Last Name");

        jTextLastName.setEditable(false);
        jTextLastName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextLastName.setToolTipText("");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Address 1");

        jTextAddress1.setEditable(false);
        jTextAddress1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextAddress1.setToolTipText("");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Address 2");

        jTextAddress2.setEditable(false);
        jTextAddress2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextAddress2.setToolTipText("");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("City");

        jTextCity.setEditable(false);
        jTextCity.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Country");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Telephone");

        jTextTel.setEditable(false);
        jTextTel.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Mobile");

        jTextMobile.setEditable(false);
        jTextMobile.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextMobile.setToolTipText("");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Post Code");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("County");

        jTextPostCode.setEditable(false);
        jTextPostCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jTextCounty.setEditable(false);
        jTextCounty.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextCounty.setToolTipText("");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Created");

        jTextCreated.setEditable(false);
        jTextCreated.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jComboBoxCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextTel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextCity, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextCounty, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxCountry, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextMobile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))))
                    .addComponent(jTextCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTextMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26)
                        .addComponent(jTextCounty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextCreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Manage Customers");
        jLabel2.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Book For Event");

        jComboBoxEvent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jButtonSaveBooking.setText("Save Booking");
        jButtonSaveBooking.setToolTipText("");
        jButtonSaveBooking.setEnabled(false);
        jButtonSaveBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveBookingActionPerformed(evt);
            }
        });

        jButtonCancelBooking.setText("Cancel Changes");
        jButtonCancelBooking.setToolTipText("");
        jButtonCancelBooking.setEnabled(false);
        jButtonCancelBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelBookingActionPerformed(evt);
            }
        });

        jMenuManage.setText("Manage");

        jMenuItemArtist.setText("Artists");
        jMenuItemArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArtistActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItemArtist);

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonSave)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonCancel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEdit))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonAddBooking)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonSaveBooking)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonCancelBooking))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addContainerGap(49, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 56, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSave)
                            .addComponent(jButtonCancel)
                            .addComponent(jButtonCreate)
                            .addComponent(jButtonEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddBooking)
                    .addComponent(jButtonSaveBooking)
                    .addComponent(jButtonCancelBooking))
                .addGap(100, 100, 100))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     //When the user selects another Customer from the list, fills its
    //details in the fields and tables. 
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        if (jList1.getSelectedValue() != null)
           fillCustomerDetails(getCustomerDetails((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jList1ValueChanged

    //this is called when the Exit menu is clicked, it exits the application
    private void jMenuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuExitMouseClicked

    //this is called when the Artist menu item is selected from the Manage menu.
    private void jMenuItemArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArtistActionPerformed
        // TODO add your handling code here:
            ArtistJFrame  myFrame = new  ArtistJFrame ();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemArtistActionPerformed

    //this is called when the Tour menu item is selected from the Manage menu.
    private void jMenuItemTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTourActionPerformed
        // TODO add your handling code here:
                    TourJFrame  myFrame = new   TourJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemTourActionPerformed
    
    //this is called when the Event menu item is selected from the Manage menu.
    private void jMenuItemEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEventActionPerformed
        // TODO add your handling code here:
              EventJFrame  myFrame = new   EventJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemEventActionPerformed

     //this event handler is called when the create button is pressed
    //it empties the text fields and tables to enter the data of new customer
    //it also sets the value of process variable to be "add"
    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        // TODO add your handling code here:
        this.process = "add";
           this.jTextTitle.setText("");
        this.jTextFirstName.setText("");
        this.jTextLastName.setText("");
        this.jTextTel.setText("");
        this.jTextMobile.setText("");
        this.jTextAddress1.setText("");
         this.jTextAddress2.setText("");
         this.jTextCity.setText("");
         this.jComboBoxCountry.setSelectedIndex(0);
         this.jTextCounty.setText("");
         this.jTextCreated.setText("");
         this.jTextPostCode.setText("");
         
             this.jTextTitle.setEditable(true);
        this.jTextFirstName.setEditable(true);
        this.jTextLastName.setEditable(true);
        this.jTextTel.setEditable(true);
        this.jTextMobile.setEditable(true);
        this.jTextAddress1.setEditable(true);
         this.jTextAddress2.setEditable(true);
         this.jTextCity.setEditable(true);
         this.jComboBoxCountry.setEditable(true);
         this.jTextCounty.setEditable(true);
         this.jTextPostCode.setEditable(true);
         
         this.jButtonCreate.setEnabled(false);
         this.jButtonSave.setEnabled(true);
         this.jButtonCancel.setEnabled(true);
         this.jButtonEdit.setEnabled(false);
         
                String[] colNames = {"Created" , "Created Confirmed","Cancelled","Cancelled Confirmed"};
         java.util.Date[][] data  = new java.util.Date[0][4];     
          javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, colNames);
         this.jTable2.setModel(model);
         
    }//GEN-LAST:event_jButtonCreateActionPerformed

      //This event handler is called when the Save button is pressed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        
          //first it creates a new Customer object and fills it with information
        //which are entered by the user in the text fields.
        Customer c = new Customer();
         persistence.CustomerDAO dao  = new persistence.CustomerDAO();
         
         c.setTitle(this.jTextTitle.getText());
         c.setForename(jTextFirstName.getText());
         c.setSurname(this.jTextLastName.getText());
         c.setTelephone(this.jTextTel.getText());
         c.setMobile(this.jTextMobile.getText());
         c.setAddr1(this.jTextAddress1.getText());
         c.setAddr2(this.jTextAddress2.getText());
         c.setTown(this.jTextCity.getText());
         c.setCountry(this.jComboBoxCountry.getSelectedItem().toString());
         c.setCounty(this.jTextCounty.getText());
         c.setPostcode(this.jTextPostCode.getText());
         c.setId(this.customerID);
         
          //it testes the process variable, if it is add, it will call insertCustomer method
         //if it is  edit it will call updateCustomer. In either case, it will pass the
         //Customer object mentioned abive as a parameter
         String result = "";
         if (this.process.equalsIgnoreCase("add"))
                result = dao.insertCustomer(c);
          if (this.process.equalsIgnoreCase("edit"))
                result = dao.updateCustomer(c);
          
           //the result variable contains the result of the above operation, if all things
          //went correctly, then display success message to the user, and disable the text 
          //fields , enable the create and edit buttons, disable the save and cancel buttons
         if (result.equalsIgnoreCase("0"))
         {
                 javax.swing.JOptionPane.showMessageDialog(null,  "Customer saved successfully",
                 "Customer Process", javax.swing.JOptionPane.INFORMATION_MESSAGE);
         
         fillCustomerList();
           if (this.process.equalsIgnoreCase("add"))
              fillCustomerDetails(getCustomerDetails((String)jList1.getSelectedValue()));
          this.jTextTitle.setEditable(false);
        this.jTextFirstName.setEditable(false);
        this.jTextLastName.setEditable(false);
        this.jTextTel.setEditable(false);
        this.jTextMobile.setEditable(false);
        this.jTextAddress1.setEditable(false);
         this.jTextAddress2.setEditable(false);
         this.jTextCity.setEditable(false);
         this.jComboBoxCountry.setEditable(false);
         this.jTextCounty.setEditable(false);
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

      //event handler which is called when Edit button is clicked, it enables
    //the controls so the use can change the data, and sets the process to be "edit"
    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        // TODO add your handling code here:
          this.process = "edit";
                    this.jTextTitle.setEditable(true);
        this.jTextFirstName.setEditable(true);
        this.jTextLastName.setEditable(true);
        this.jTextTel.setEditable(true);
        this.jTextMobile.setEditable(true);
        this.jTextAddress1.setEditable(true);
         this.jTextAddress2.setEditable(true);
         this.jTextCity.setEditable(true);
         this.jComboBoxCountry.setEditable(true);
         this.jTextCounty.setEditable(true);
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
        if(process.equalsIgnoreCase("add")){
            jList1.setSelectedIndex(0);
             //it calls the fillCustomerDetails to fill the information
             //of the currently selected Customer
            fillCustomerDetails(getCustomerDetails((String)jList1.getSelectedValue()));
        }
        
        if(process.equalsIgnoreCase("edit")){
                fillCustomerDetails(getCustomerDetails((String)jList1.getSelectedValue()));
        }
        
        this.jTextTitle.setEditable(false);
        this.jTextFirstName.setEditable(false);
        this.jTextLastName.setEditable(false);
        this.jTextTel.setEditable(false);
        this.jTextMobile.setEditable(false);
        this.jTextAddress1.setEditable(false);
         this.jTextAddress2.setEditable(false);
         this.jTextCity.setEditable(false);
         this.jComboBoxCountry.setEditable(false);
         this.jTextCounty.setEditable(false);
         this.jTextPostCode.setEditable(false);
         
         this.jButtonCreate.setEnabled(true);
         this.jButtonSave.setEnabled(false);
         this.jButtonCancel.setEnabled(false);
         this.jButtonEdit.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    //This event handler is called when the Add Booking button is clicked, it enables 
    //the save & Cancel booking buttons, disable the Add Booking Button and makes the 
    //Panel1 , which contains the event drop down list, visible, as it is set initiallly
    //to be invisible
    private void jButtonAddBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddBookingActionPerformed
        // TODO add your handling code here:
         this.jButtonAddBooking.setEnabled(false);
        this.jButtonSaveBooking.setEnabled(true);
           this.jButtonCancelBooking.setEnabled(true);
        this.jPanel1.setVisible(true);      
    }//GEN-LAST:event_jButtonAddBookingActionPerformed
    
    //This event handler is called when the Save Booking Button is clicked
    private void jButtonSaveBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveBookingActionPerformed
        // TODO add your handling code here:
            ArrayList booklist = new ArrayList<Booking>();
            //obtain the value selected in the event list, split it to get the event id
        String[] arr = this.jComboBoxEvent.getSelectedItem().toString().split(",");
        //creates event object with the selected event id, create a Booking object with the Event object
        //add the Booking object to a Booking List
        Event e = new Event(Long.parseLong(arr[0]),null,null,null);
        e.setId(Long.parseLong(arr[0]));
        booklist.add(new Booking(e));
        //Create Customer object and assign it the Booking List that is created above.
        //and set the customer ID to be the current selected customer ID in the screen
        Customer c = new Customer();
        c.setBookings(booklist);
        c.setId(this.customerID);
        //call insertBooking in the BookingDAO passing it the Customer object
        persistence.BookingDAO dao = new persistence.BookingDAO();
        dao.insertBooking(c);
        //enable the Add Booking button, disable the Save & Cancel Booking Buttons, and hide the
        //events drop down list again
        this.jButtonAddBooking.setEnabled(true);
        this.jButtonSaveBooking.setEnabled(false);
        this.jButtonCancel.setEnabled(false);
        this.jPanel1.setVisible(false);
        //refresh the data by calling fillCustomerDetails again.
            fillCustomerDetails(getCustomerDetails((String)jList1.getSelectedValue()));
    }//GEN-LAST:event_jButtonSaveBookingActionPerformed

    //This event handler is invoked when the Cancel Booking button is clicked, it 
    //enable the Add Booking button, disable the Save & Cancel Booking Buttons,
    //and hide the events drop down list again
    private void jButtonCancelBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelBookingActionPerformed
        // TODO add your handling code here:
         this.jButtonAddBooking.setEnabled(true);
        this.jButtonSaveBooking.setEnabled(false);
           this.jButtonCancel.setEnabled(false);
        this.jPanel1.setVisible(false);
           fillCustomerDetails(getCustomerDetails((String)jList1.getSelectedValue()));
        
    }//GEN-LAST:event_jButtonCancelBookingActionPerformed

        //this is called when the Venue menu item is selected from the Manage menu.
    private void jMenuItemVenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVenueActionPerformed
        // TODO add your handling code here:
               VenueJFrame  myFrame = new   VenueJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemVenueActionPerformed

        //this is called when the Dates menu item is selected from the Manage menu.
    private void jMenuItemDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDatesActionPerformed
        // TODO add your handling code here:
           DateJFrame  myFrame = new   DateJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDatesActionPerformed

        //this is called when the Cancellations menu item is selected from the Manage menu.
    private void jMenuItemCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelActionPerformed
        // TODO add your handling code here:
          CancellationJFrame  myFrame = new   CancellationJFrame();
        myFrame.setExtendedState(myFrame.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        myFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemCancelActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddBooking;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCancelBooking;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveBooking;
    private javax.swing.JComboBox jComboBoxCountry;
    private javax.swing.JComboBox jComboBoxEvent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuExit;
    private javax.swing.JMenuItem jMenuItemArtist;
    private javax.swing.JMenuItem jMenuItemCancel;
    private javax.swing.JMenuItem jMenuItemDates;
    private javax.swing.JMenuItem jMenuItemEvent;
    private javax.swing.JMenuItem jMenuItemTour;
    private javax.swing.JMenuItem jMenuItemVenue;
    private javax.swing.JMenu jMenuManage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextAddress1;
    private javax.swing.JTextField jTextAddress2;
    private javax.swing.JTextField jTextCity;
    private javax.swing.JTextField jTextCounty;
    private javax.swing.JTextField jTextCreated;
    private javax.swing.JTextField jTextFirstName;
    private javax.swing.JTextField jTextLastName;
    private javax.swing.JTextField jTextMobile;
    private javax.swing.JTextField jTextPostCode;
    private javax.swing.JTextField jTextTel;
    private javax.swing.JTextField jTextTitle;
    // End of variables declaration//GEN-END:variables
}
