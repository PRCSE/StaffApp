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
 

public class VenueDAO {
     //This method uses the ConnectionPar Class to obtain connection to database
    private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
     
    //this method takes the veneue name as parameter, and returns the veneue details
    //in a Venue object
     public Venue getVenueDetails(String name){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        
         PreparedStatement eventst = null;
        ResultSet eventrs = null;
   
        Venue result = null;
        try {
            //obtain the database connection by calling getConn()
            con = getConn();
            String sql = "";
             
            //create the PreparedStatement from the Connection object by calling prepareStatment
            //method, passing it the sql that is constructed using the supplied parameters
            st = con.prepareCall("select * from venue where name = '" + name + "'");
            
              //Calls executeQuery and the resulting data is returned in the resultset
            rs = st.executeQuery();
            
            //loop through the result set and save the data in the datamodel objects
            while (rs.next()){
                result = new Venue(rs.getLong("id"),rs.getString("name"));
                result.setDescription((rs.getString("description")));
                result.setAddr1(rs.getString("line_1"));
                result.setPostcode(rs.getString("postcode"));
                
                
                sql = "select e.* from event e,seating_plan s where s.venue_id=" + rs.getString("id");
                sql+= " and e.seating_plan = s.id";
                eventst = con.prepareCall(sql);
                eventrs = eventst.executeQuery();
                
                   ArrayList<Event> eventList = new ArrayList<Event>();
                while (eventrs.next()){
                    Event e = new Event(eventrs.getLong("id"),eventrs.getString("name"),eventrs.getDate("start_time"),eventrs.getDate("end_time"));
                   
                     eventList.add(e);
                } 
                result.setEventList(eventList);
                
           
          
                
            }
            
        }
         catch (SQLException e){
             e.printStackTrace();
         }
     //close the resultset,prepared statement and the connection to release resources   
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
  //This method returns all venues in an array of strings, it is used by JFrame
     //to fill drop down lists and lists controls
    public String[] getVenueList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
            //obtain the database connection by calling getConn()
             con = getConn();
             
               //create the PreparedStatement from the Connection object by calling prepareStatment
            //method, passing it the sql 
             st = con.prepareStatement("select * from Venue");
             
             //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
             //loop through the result set and save the data in the datamodel objects
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
         
          //close the resultset,prepared statement and the connection to release resources  
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
    
    //this method returns the last seating plan in a SeatingPlan object
     public SeatingPlan getLastSeatingPlan(){
         
         Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         
         SeatingPlan result = null;
         try {
             //obtain the database connection by calling getConn()
             con = getConn();
             
                //create the PreparedStatement from the Connection object by calling prepareStatment
            //method, passing it the sql 
             st = con.prepareStatement("select max(nvl(id,0)) from seating_plan");
             
              //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
             //loop through the result set and save the data in the datamodel objects
             while (rs.next()){
                 System.out.println("inside while in getlast seating plan");
                  result = new SeatingPlan(rs.getLong(1),null,null);
                  
                 
                
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
     
     //this method takes Venue object as parameter, and inserts a new row in the
     //seating_plan table with venue_id contained in this object
     public String insertSeatingPlan(Venue v){
         System.out.println("insert seating plan....");
           String result = "0";
         Connection con = null;
         //construct sql
         String SQLCommand = "INSERT INTO seating_plan " +   
                    "(id,name,venue_id) " +
                    "VALUES (seq_seating_plan_id.NEXTVAL,?,?)";  
           try{
               //obtain the database connection by calling getConn()
               con = getConn();
               
               //create the PreparedStatement from the Connection object by calling prepareStatement
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);
            
            //setting the parameters values
            ps.setString(1, v.getName());
            ps.setLong(2,v.getId());
            
    
           
            //exceuting the insert or update operation       
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
     
     //this method takes Venue object as parameter, and inserts a new row in the
     //venue table with values contained in the venue object
     public String insertVenue(Venue v){
         String result = "0";
         Connection con = null;
         
         //build sql
         String SQLCommand = "INSERT INTO venue " +   
                    "(id,name,line_1,postcode,description) " +
                    "VALUES (seq_venue_id.NEXTVAL,?,?,?,?)";  
         try{
             
                 //obtain the database connection by calling getConn()
               con = getConn();
               
               
              //create the PreparedStatement from the Connection object by calling prepareStatement
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand); 
            
                //setting the parameters values
            ps.setString(1,v.getName());
            ps.setString(2,v.getAddr1());
            ps.setString(3,v.getPostcode());
            ps.setString(4,v.getDescription());
    
           
               //exceuting the insert or update operation      
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
     
   //this method takes Venue object as parameter, and  updates the matching row in venue tablr
   public String updateVenue(Venue v){
         String result = "0";
         Connection con = null;
         //build sql
         String SQLCommand = "UPDATE venue set name=?,line_1=?,postcode=?,description=? where id=? "; 
                  
         try{
              //obtain the database connection by calling getConn()
               con = getConn();
               
             //create the PreparedStatement from the Connection object by calling prepareStatement
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            
             //setting the parameters values
            ps.setString(1,v.getName());
            ps.setString(2,v.getAddr1());
            ps.setString(3,v.getPostcode());
            ps.setString(4,v.getDescription());
            ps.setLong(5, v.getId());
    
           
           //exceuting the insert or update operation       
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
 
 
}
