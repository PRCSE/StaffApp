/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

/**
 *
 * @author snowman
 */

import java.sql.*;
import com.prcse.datamodel.*;
import java.util.Calendar;
import java.util.Vector;
public class CustomerDAO {
    private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
    public Customer getCustomerDetails(String firstName,String LastName){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ResultSet bookrs = null; 
        PreparedStatement bookst = null;
         
    
         Customer result = null;
         try {
             con = getConn();
             String sql = "select c.* from Customer c where c.forename = '" + firstName 
                     + "' and c.surname = '" + LastName + "'" ;
             st = con.prepareStatement(sql);
             
             rs = st.executeQuery();
             while (rs.next()){
                 System.out.println("inside while....");
                
                 result = new Customer();
                 result.setTitle(rs.getString("TITLE"));
                 result.setForename(rs.getString("FORENAME"));
                 result.setSurname(rs.getString("SURNAME"));
                 result.setTelephone(rs.getString("TELEPHONE"));
                 result.setMobile(rs.getString("MOBILE"));
                 result.setAddr1(rs.getString("LINE_1"));
                 result.setAddr2(rs.getString("LINE_2"));
                 result.setTown(rs.getString("TOWN"));
                 result.setCounty(rs.getString("COUNTY"));
                 result.setCountry(rs.getString("COUNTRY"));
                 result.setCreated(rs.getDate("created"));
                 result.setPostcode(rs.getString("POSTCODE"));
                 result.setId(rs.getLong("id"));
                 
                  bookst = con.prepareCall("select * from booking where customer_id=" + rs.getString("id"));
                 bookrs = bookst.executeQuery();
                 java.util.ArrayList<Booking> bookList = new java.util.ArrayList<Booking>();
                 
                 while (bookrs.next()){
                     Booking book = new Booking(null);
                     book.setCreated(bookrs.getDate("CREATED"));
                     book.setConfirmed(bookrs.getDate("CREATED_CONFIRMED"));
                     book.setCancelled(bookrs.getDate("CANCELLED"));
                     book.setCancelConfirmed(bookrs.getDate("CANCELLED_CONFIRMED"));
                     bookList.add(book);
                     
                     
                     
                     
                 }
                
               result.setBookings(bookList);
                
             }
             
         }
         catch (SQLException e){
             e.printStackTrace();
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
                 list.add(rs.getString("TITLE") + " " + rs.getString("FORENAME") + " " + rs.getString("SURNAME"));
                
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
    
    
     public String[] getCountryList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             con = getConn();
             st = con.prepareStatement("select * from Country");
             rs = st.executeQuery();
             while (rs.next()){
                 System.out.println("inside while....");
                 list.add(rs.getString("Country"));
                
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
    
      public String[] getEventList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             con = getConn();
             st = con.prepareStatement("select * from Event");
             rs = st.executeQuery();
             while (rs.next()){
                 System.out.println("inside while....");
                 list.add(rs.getString("id") + "," + rs.getString("name"));
                 
                
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
    
    public String insertCustomer(Customer c){
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO customer " +   
                    "(id,title,forename,surname,telephone,mobile,line_1,line_2,town,county,"
                 + "postcode,country,created,login_id) " +   
                    "VALUES (seq_customer_id.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,1)";  
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,c.getTitle() );   
            ps.setString(2,c.getForename());
            ps.setString(3,c.getSurname());
            ps.setString(4,c.getTelephone());
            ps.setString(5,c.getMobile());
            ps.setString(6,c.getAddr1());
            ps.setString(7,c.getAddr2());
            ps.setString(8,c.getTown());
            ps.setString(9,c.getCounty());
            ps.setString(10, c.getPostcode());
            ps.setString(11, c.getCountry());
            ps.setDate(12, new java.sql.Date(new java.util.Date().getTime()));
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
    
    
    public String updateCustomer(Customer c){
         String result = "0";
         Connection con = null;
         String SQLCommand = "UPDATE customer set title=?,forename=?,surname=?,telephone=?,mobile=?,";
         SQLCommand+= "line_1=?,line_2=?,town=?,county=?,postcode=?,country=? where id=?" ;
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,c.getTitle() );   
            ps.setString(2,c.getForename());
            ps.setString(3,c.getSurname());
            ps.setString(4,c.getTelephone());
            ps.setString(5,c.getMobile());
            ps.setString(6,c.getAddr1());
            ps.setString(7,c.getAddr2());
            ps.setString(8,c.getTown());
            ps.setString(9,c.getCounty());
            ps.setString(10, c.getPostcode());
            ps.setString(11, c.getCountry());
            ps.setLong(12, c.getId());
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
}
