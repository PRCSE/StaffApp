/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import com.prcse.datamodel.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Calendar;
 
public class BookingDAO {
    //This method uses the ConnectionPar Class to obtain connection to database
   private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
      
   //this method retireves all the cancelled bookings from database
      public Vector<Customer> getCancellations(){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
         PreparedStatement eventst = null;
        ResultSet eventrs = null;
        Vector<Customer> result = new Vector<Customer>();
        
        //sql statement
        String sql = "select * from customer";
         //obtain the database connection by calling getConn()
        con = getConn();
        
        try {
           
             //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql  
            st = con.prepareCall(sql);
            
            //Calls executeQuery and the resulting data is returned in the resultset
            rs = st.executeQuery();
            
            while(rs.next()){
                Customer c = new Customer();
                c.setForename(rs.getString("forename"));
                c.setSurname(rs.getString("surname"));
                
                sql = "select b.created,b.created_confirmed,b.cancelled,b.cancelled_confirmed";
                sql+= ",e.name from booking b,event e where b.event_id = e.id and b.customer_id=" + rs.getString("id");
                sql+= " and b.cancelled_confirmed is not null";
                eventst = con.prepareCall(sql);
                eventrs = eventst.executeQuery();
                ArrayList<Booking> booklist = new ArrayList<Booking>();
                
                //loop through the result set and save the data in the datamodel objects
                while (eventrs.next()){
                     Event e = new Event(0l,eventrs.getString("name"),null,null);
                     Booking b = new Booking(e);
                     b.setCreated(eventrs.getDate("created"));
                     b.setConfirmed(eventrs.getDate("created_confirmed"));
                     b.setCancelled(eventrs.getDate("cancelled"));
                     b.setCancelConfirmed(eventrs.getDate("cancelled_confirmed"));
                     booklist.add(b);
                }
                c.setBookings(booklist);
                result.add(c);
                
                
            }
        }
         catch (SQLException e){
             e.printStackTrace();
         }
        return result;
      }
      
      
 //This method takes Customer object as parameter and inserts The Booking object contained
      //withing this Customer object into the Booking table
        public String insertBooking(Customer c){
         String result = "0";
         Connection con = null;
         //building the sql command
         String SQLCommand = "INSERT INTO booking " +   
                    "(id,created,created_confirmed,customer_id,event_id,cancelled,cancelled_confirmed) "  +
                    "VALUES (seq_booking_id.NEXTVAL,?,?,?,?,?,?)";  
         try{
               //obtain the database connection by calling getConn()
               con = getConn();
            //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
               //setting the parameters values
            ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
             ps.setLong(3,c.getId());
            ps.setLong(4,c.getBookings().get(0).getEvent().getId());
            ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
             //exceuting the insert or update operation   
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
        
  //This method returns a list of all customers and stores them in an array string
        //this method is called by the JFrame to fills the combo box or list control
        public String[] getCustomersList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
              //obtain the database connection by calling getConn()
             con = getConn();
                //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql that is constructed using the supplied parameters
             st = con.prepareStatement("select * from Customer");
              //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
               //loop through the result set and save the data in the datamodel objects
             while (rs.next()){
                 //System.out.println("inside while....");
                 list.add(rs.getString("id") + "," + rs.getString("FORENAME") + "," + rs.getString("SURNAME"));
                
             }
             result = new String[list.size()];
             for (int i = 0 ; i< list.size() ; i++){
                 result[i] = list.get(i);
                 
             }
         }
         catch (SQLException e){
             
         }
          //close the resultset,preparedstatement and connection to relase resources  
         if (rs != null){
             try{
             rs.close();
             rs = null;
             }
             catch (SQLException e){
                 
             }
         }
         if (st != null){
             try{
             st.close();
             st = null;
             }
             catch (SQLException e){
                 
             }
         }
         
               if (con != null){
             try{
             con.close();
             con = null;
             }
             catch (SQLException e){
                 
             }
         }
         
         return result;
    }
    
}
