/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.prcse.datamodel.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

 
public class TourDAO {
       //This method uses the ConnectionPar Class to obtain connection to database
  private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
  
  //This method takes Tour object as parameter and update the corresponding
  //row in the tour table
      public String updateTour(Tour par){
         String result = "0";
         Connection con = null;
         //building the sql command
         String SQLCommand =  "update tour set name=?,artist_id=? where id=?";
         try{
             //obtain the database connection by calling getConn()
               con = getConn();
               
               //create the PreparedStatement from the Connection object by calling prepareStatement
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            
             //setting the parameters values
            ps.setString(1,par.getName() );   
            ps.setLong(2,par.getArtist().getId());
            ps.setLong(3, par.getId());
           
            
           //exceuting the insert or update operation 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
        
    }
      
      
     //This method takes Tour object as parameter and  inserts a new row in the 
      //tours table with the data values contained in the Tour object
    public String insertTour(Tour par){
         String result = "0";
         Connection con = null;
         
           //building the sql command
         String SQLCommand = "INSERT INTO tour " +   
                    "(id,name,artist_id) " +
                    "VALUES (seq_tour_id.NEXTVAL,?,?)";  
         try{
             
                 //obtain the database connection by calling getConn()
               con = getConn();
               
             //create the PreparedStatement from the Connection object by calling prepareStatement
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            
            //setting the parameters values
            ps.setString(1,par.getName() );   
            ps.setLong(2,par.getArtist().getId());
           
           //exceuting the insert or update operation       
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
        
    }
    
        //This method takes the tour name as parameter and returns the matching tour row
    //into a Tour object return parameter

    public Tour getTourDetails(String par){
            Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Tour result = null;
        
        try {
             //obtain the database connection by calling getConn()
            con = getConn();
            //building the sql command
            String sql = "select t.*,a.id as aid,a.name  as aname from tour t,artist a where " +
                    "t.name='" + par + "' and t.artist_id = a.id";
             
               //create the PreparedStatement from the Connection object by calling prepareStatment
            //method, passing it the sql that is constructed using the supplied parameters
             st = con.prepareStatement(sql);
             
             //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
             //loop through the result set and save the data in the datamodel objects
             while (rs.next()){
                System.out.println("inside while in getTour Details...");     
                 Artist a = new Artist(rs.getLong("aid"),rs.getString("aname"),null,null,null,null);
                 
                 result = new Tour(rs.getLong("id"),rs.getString("name"),a);
                 
                 
             }
            
        }
        catch (SQLException ex){
            ex.printStackTrace();
            
        }
        return result;
    }
    
    //this method takes tour name as parameter, and returns a Vector of Event objects
    //for all events associated with this tour
    public Vector<Event> getTourEvents(String par){
            Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        Vector<Event> result = new Vector<Event>();
          try {
              //obtain the database connection by calling getConn()
             con = getConn();
             //construct sql
             String sql = "select e.* from tour t,event e,billing b where t.name = '" + par + "' and ";
             sql+= "e.id = b.event_id and b.tour_id = t.id";
             
               //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql that is constructed using the supplied parameters
             st = con.prepareStatement(sql);
             
             //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
              //loop through the result set and save the data in the datamodel objects
             while (rs.next()){
                  Event e = new Event(rs.getLong("id"),rs.getString("name"),rs.getDate("start_time"),rs.getDate("end_time"));
                  
                    result.add(e);
                
             }
            
         }
         catch (SQLException e){
             
         }
        return result;
    }
    
    //this method returns all the events in an array of strings, it is used by Jframe
    //to fill drop down list
     public String[] getEventsList(){
          Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
          Vector<String> list = new Vector<String>();
          String[] result = null;
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");
         try{
              //obtain the database connection by calling getConn()
               con = getConn();
               
               //construct sql
             String sql = "select id,name from event" ;
             
             
              //create the PreparedStatement from the Connection object by calling prepareStatement
            //method, passing it the sql  
             st = con.prepareCall(sql);
             
             //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
                  //loop through the result set and save the data in the datamodel objects
             while (rs.next()){
                 list.add(rs.getString("id") + "," + rs.getString("name"));
                 
             }
             
                 result = new String[list.size()];
             for (int i = 0 ; i< list.size() ; i++){
                 result[i] = list.get(i);
                 
             }
         }
         catch (SQLException ex){
             ex.printStackTrace();
         }
         return result;
     }
  
      //this method returns all the tours in an array of strings, it is used by Jframe
    //to fill drop down list
    public String[] getToursList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
              //obtain the database connection by calling getConn()
             con = getConn();
             
              //create the PreparedStatement from the Connection object by calling prepareStatement
            //method, passing it the sql  
             st = con.prepareStatement("select * from Tour");
             
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
