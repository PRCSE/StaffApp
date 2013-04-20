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

/**
 *
 * @author snowman
 */
public class TourDAO {
  private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
      public String updateTour(Tour par){
         String result = "0";
         Connection con = null;
         String SQLCommand =  "update tour set name=?,artist_id=? where id=?";
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,par.getName() );   
            ps.setLong(2,par.getArtist().getId());
            ps.setLong(3, par.getId());
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
        
    }
    public String insertTour(Tour par){
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO tour " +   
                    "(id,name,artist_id) " +
                    "VALUES (seq_tour_id.NEXTVAL,?,?)";  
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1,par.getName() );   
            ps.setLong(2,par.getArtist().getId());
           
                 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
        
    }
    public Tour getTourDetails(String par){
            Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Tour result = null;
        
        try {
            con = getConn();
            String sql = "select t.*,a.id as aid,a.name  as aname from tour t,artist a where " +
                    "t.name='" + par + "' and t.artist_id = a.id";
                    
             st = con.prepareStatement(sql);
             rs = st.executeQuery();
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
    public Vector<Event> getTourEvents(String par){
            Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        Vector<Event> result = new Vector<Event>();
          try {
             con = getConn();
             String sql = "select e.* from tour t,event e,billing b where t.name = '" + par + "' and ";
             sql+= "e.id = b.event_id and b.tour_id = t.id";
             st = con.prepareStatement(sql);
             rs = st.executeQuery();
             while (rs.next()){
                  Event e = new Event(rs.getLong("id"),rs.getString("name"),rs.getDate("start_time"),rs.getDate("end_time"));
                  
                    result.add(e);
                
             }
            
         }
         catch (SQLException e){
             
         }
        return result;
    }
     public String[] getEventsList(){
          Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
          Vector<String> list = new Vector<String>();
          String[] result = null;
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");
         try{
               con = getConn();
             String sql = "select id,name from event" ;
             st = con.prepareCall(sql);
             rs = st.executeQuery();
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
     
    public String[] getToursList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             con = getConn();
             st = con.prepareStatement("select * from Tour");
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
    
}
