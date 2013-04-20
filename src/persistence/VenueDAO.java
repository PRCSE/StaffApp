/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.prcse.datamodel.SeatingPlan;
import com.prcse.datamodel.Venue;
import com.prcse.datamodel.Event;
import com.prcse.datamodel.Tour;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author snowman
 */

public class VenueDAO {
    private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
     
     public Venue getVenueDetails(String name){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        
         PreparedStatement eventst = null;
        ResultSet eventrs = null;
   
        Venue result = null;
        try {
            con = getConn();
            String sql = "";
             
            st = con.prepareCall("select * from venue where name = '" + name + "'");
            
            rs = st.executeQuery();
            while (rs.next()){
                result = new Venue(rs.getString("name"));
                result.setDescription((rs.getString("description")));
                result.setAddr1(rs.getString("line_1"));
                result.setPostcode(rs.getString("postcode"));
                result.setVenueID(rs.getLong("id"));
                
                sql = "select e.* from event e,seating_plan s where s.venue_id=" + rs.getString("id");
                sql+= " and e.seating_plan = s.id";
                eventst = con.prepareCall(sql);
                eventrs = eventst.executeQuery();
                
                 
                while (eventrs.next()){
                    Event e = new Event(eventrs.getString("name"),null,null);
                     Calendar cal = Calendar.getInstance();
                     cal.setTime(eventrs.getDate("start_time"));
                     e.setStartTime(cal);
                     cal.setTime(eventrs.getDate("end_time"));
                     e.setEndTime(cal);
                     result.addEvent(e);
                } 
                
                
           
          
                
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
         
         
           if (eventrs != null){
             try{
             eventrs.close();
             eventrs = null;
             }
             catch (SQLException e){
                 
             }
         }
         if (eventst != null){
             try{
             eventst.close();
             eventst = null;
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
     
    public String[] getVenueList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             con = getConn();
             st = con.prepareStatement("select * from Venue");
             rs = st.executeQuery();
             while (rs.next()){
                 System.out.println("inside while....");
                 list.add(rs.getString("NAME"));
                
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
     public SeatingPlan getLastSeatingPlan(){
         
         Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         
         SeatingPlan result = null;
         try {
             con = getConn();
             st = con.prepareStatement("select max(nvl(id,0)) from seating_plan");
             rs = st.executeQuery();
             while (rs.next()){
                 System.out.println("inside while in getlast seating plan");
                  result = new SeatingPlan(null,null);
                  result.setSeatingPlanID(rs.getLong(1));
                 
                
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
     public String insertSeatingPlan(Venue v){
         System.out.println("insert seating plan....");
           String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO seating_plan " +   
                    "(id,name,venue_id) " +
                    "VALUES (seq_seating_plan_id.NEXTVAL,?,?)";  
           try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);
            ps.setString(1, v.getName());
            ps.setLong(2,v.getVenueID());
            
    
           
                 
            ps.executeUpdate(); 
            System.out.println("after insert....");
         }
         catch (SQLException ex){
             System.out.println(ex.getMessage());
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
         
     }
     public String insertVenue(Venue v){
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO venue " +   
                    "(id,name,line_1,postcode,description) " +
                    "VALUES (seq_venue_id.NEXTVAL,?,?,?,?)";  
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,v.getName());
            ps.setString(2,v.getAddr1());
            ps.setString(3,v.getPostcode());
            ps.setString(4,v.getDescription());
    
           
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
   public String updateVenue(Venue v){
         String result = "0";
         Connection con = null;
         String SQLCommand = "UPDATE venue set name=?,line_1=?,postcode=?,description=? where id=? "; 
                  
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,v.getName());
            ps.setString(2,v.getAddr1());
            ps.setString(3,v.getPostcode());
            ps.setString(4,v.getDescription());
            ps.setLong(5, v.getVenueID());
    
           
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
 
 
}
