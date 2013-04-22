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
import java.util.HashMap;
import java.util.Calendar;

 
public class ArtistDAO {
    //This method uses the ConnectionPar Class to obtain connection to database
    private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }

    //This method takes artist name as parameter , and returns its details in a Hashmap
 public HashMap getArtistDetails(String name){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        
         PreparedStatement eventst = null;
        ResultSet eventrs = null;
        
          PreparedStatement tourst = null;
        ResultSet tourrs = null;
        
        try {
            //obtain the database connection by calling getConn()
            con = getConn();
            String sql = "";
             
           //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql that is constructed using the supplied parameters
            st = con.prepareCall("select * from artist where name = '" + name + "'");
            
            //Calls executeQuery and the resulting data is returned in the resultset
            rs = st.executeQuery();
            
            //loop through the result set and save the data in the datamodel objects
            while (rs.next()){
                Artist a = new Artist(rs.getLong("id"),null,null,null,null,null);
                a.setName(rs.getString("name"));
                a.setBio((rs.getString("description")));
              
                result.put(0,a);
                
                sql = "select e.* from event e,billing b where b.event_id = e.id and b.artist_id=" + rs.getString("id");
                eventst = con.prepareCall(sql);
                eventrs = eventst.executeQuery();
                
                Vector eventlist = new Vector();
                while (eventrs.next()){
                    Event e = new Event(eventrs.getLong("id"),eventrs.getString("name"),eventrs.getDate("start_time"),eventrs.getDate("end_time"));                                
                     eventlist.add(e);
                }
                
                
                result.put(1, eventlist);
                
                sql = "select * from tour where artist_id = " + rs.getString("id");
                tourst = con.prepareCall(sql);
                tourrs = tourst.executeQuery();
                Vector tourlist = new Vector();
                while (tourrs.next()){
                   Tour t = new Tour(tourrs.getLong("id"),tourrs.getString("name"),null); 
                   tourlist.add(t);
                }
                result.put(2, tourlist);
          
                
            }
            
        }
         catch (SQLException e){
             e.printStackTrace();
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
         
         
         
           if (tourrs != null){
             try{
             tourrs.close();
             tourrs = null;
             }
             catch (SQLException e){
                 
             }
         }
         if (tourst != null){
             try{
             tourst.close();
             tourst = null;
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
  //This method returns a list of all artists in an Array of String, it is called by the
 //JFrame to fill the list of artists
 public String[] getArtistsList() {
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
             st = con.prepareStatement("select * from Artist");
              //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
             //loop through the result set and save the data in the datamodel objects
             while (rs.next()){
                 //System.out.println("inside while....");
                 list.add(rs.getString("NAME"));
                
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
 //This method takes Artist object as parameter and inserts it into the artist table
 public String insertArtist(Artist a){
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO artist " +   
                    "(id,name,description) " +
                    "VALUES (seq_artist_id.NEXTVAL,?,?)";  
         try{
              //obtain the database connection by calling getConn()
               con = getConn();
                 //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);  
            //setting the parameters values
            ps.setString(1,a.getName() );  
            ps.setString(2,a.getBio());
           
            //exceuting the insert or update operation    
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
 
  //This method takes Artist object as parameter and update the record in the artist table
 //which has the same id as the Artist parameter object.
 public String updateArtist(Artist a){
         String result = "0";
         Connection con = null;
         String SQLCommand = "UPDATE artist set name = ?,description = ? where id = ?";  
                    
         try{
              //obtain the database connection by calling getConn()
               con = getConn();
               
            //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand); 
            
            //setting the parameters values
            ps.setString(1,a.getName() );   
            ps.setString(2,a.getBio());
            ps.setLong(3, a.getId());
            
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