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

/**
 *
 * @author snowman
 */
public class ArtistDAO {
    private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
    
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
            con = getConn();
            String sql = "";
             
            st = con.prepareCall("select * from artist where name = '" + name + "'");
            
            rs = st.executeQuery();
            while (rs.next()){
                Artist a = new Artist(rs.getString("name"));
                a.setDescription((rs.getString("description")));
                a.setArtistID(rs.getLong("id"));
                result.put(0,a);
                
                sql = "select e.* from event e,billing b where b.event_id = e.id and b.artist_id=" + rs.getString("id");
                eventst = con.prepareCall(sql);
                eventrs = eventst.executeQuery();
                
                Vector eventlist = new Vector();
                while (eventrs.next()){
                    Event e = new Event(eventrs.getString("name"),null,null);
                     Calendar cal = Calendar.getInstance();
                     cal.setTime(eventrs.getDate("start_time"));
                     e.setStartTime(cal);
                     cal.setTime(eventrs.getDate("end_time"));
                     e.setEndTime(cal);
                     eventlist.add(e);
                }
                
                
                result.put(1, eventlist);
                
                sql = "select * from tour where artist_id = " + rs.getString("id");
                tourst = con.prepareCall(sql);
                tourrs = tourst.executeQuery();
                Vector tourlist = new Vector();
                while (tourrs.next()){
                   Tour t = new Tour(tourrs.getString("name")); 
                   tourlist.add(t);
                }
                result.put(2, tourlist);
          
                
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
 public String[] getArtistsList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             con = getConn();
             st = con.prepareStatement("select * from Artist");
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
 
 public String insertArtist(Artist a){
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO artist " +   
                    "(id,name,description) " +
                    "VALUES (seq_artist_id.NEXTVAL,?,?)";  
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,a.getName() );   
            ps.setString(2,a.getDescription());
           
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
 
 
 public String updateArtist(Artist a){
         String result = "0";
         Connection con = null;
         String SQLCommand = "UPDATE artist set name = ?,description = ? where id = ?";  
                    
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,a.getName() );   
            ps.setString(2,a.getDescription());
            ps.setLong(3, a.getArtistID());
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
}  