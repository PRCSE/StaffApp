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
/**
 *
 * @author snowman
 */
public class BookingDAO {
   private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
      
      public Vector<Customer> getCancellations(){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
         PreparedStatement eventst = null;
        ResultSet eventrs = null;
        Vector<Customer> result = new Vector<Customer>();
        
        String sql = "select * from customer";
        con = getConn();
        
        try {
            st = con.prepareCall(sql);
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
                
                while (eventrs.next()){
                     Event e = new Event(eventrs.getString("name"),null,null);
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
      
      
      
        public String insertBooking(Customer c){
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO booking " +   
                    "(id,created,created_confirmed,customer_id,event_id,cancelled,cancelled_confirmed) "  +
                    "VALUES (seq_booking_id.NEXTVAL,?,?,?,?,?,?)";  
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
           
            ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
             ps.setLong(3,c.getCustomerID());
            ps.setLong(4,c.getBookings().get(0).getEvent().getEventID());
            ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
        
        
        public String[] getCustomersList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             con = getConn();
             st = con.prepareStatement("select * from Customer");
             rs = st.executeQuery();
             while (rs.next()){
                 System.out.println("inside while....");
                 list.add(rs.getString("id") + "," + rs.getString("FORENAME") + "," + rs.getString("SURNAME"));
                
             }
             result = new String[list.size()];
             for (int i = 0 ; i< list.size() ; i++){
                 result[i] = list.get(i);
                 
             }
         }
         catch (SQLException e){
             
         }
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
