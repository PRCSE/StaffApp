/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import com.prcse.datamodel.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author snowman
 */
public class EventDAO {
   private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
     
    public String updateEvent(Event e){
         //SeatingPlan sp = new VenueDAO().getLastSeatingPlan();
         
         String result = "0";
         Connection con = null;
         String SQLCommand = "Update event set name=?,start_time=?,end_time=? where id=?";
          
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1, e.getName());   
            ps.setDate(2, new java.sql.Date(e.getStartTime().getTime().getTime()));
            ps.setDate(3, new java.sql.Date(e.getEndTime().getTime().getTime())); 
            ps.setLong(4,e.getEventID());
            //ps.setLong(4, sp.getSeatingPlanID());
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
     public String indertEvent(Event e){
         //SeatingPlan sp = new VenueDAO().getLastSeatingPlan();
         
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO event " +   
                    "(id,name,start_time,end_time,seating_plan) " +   
                    "VALUES (seq_event_id.NEXTVAL,?,?,?,1)";  
         try{
               con = getConn();
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setString(1, e.getName());   
            ps.setDate(2, new java.sql.Date(e.getStartTime().getTime().getTime()));
            ps.setDate(3, new java.sql.Date(e.getEndTime().getTime().getTime())); 
            //ps.setLong(4, sp.getSeatingPlanID());
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
     
       public String addBilling(Tour t,Event e){
      
         
         String result = "0";
         Connection con = null;
         String SQLCommand = "INSERT INTO billing(id,lineup_order,event_id,artist_id,tour_id)" +
                 " values (seq_billing_id.NEXTVAL,?,?,?,?)";
              
         try{
               con = getConn();
               Billing b = t.getBills().get(0);
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            ps.setInt(1,b.getLineupOrder());
            ps.setLong(2,e.getEventID());
            ps.setLong(3, b.getBilling().getArtistID());
           ps.setLong(4, t.getTourID());
            
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
     
     public Vector<Event> getEventsInDay(Date par){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Vector<Event> result = new Vector<Event>();
         try {
            con = getConn();
            
           SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-YY");
         
              String sql =  "select * from event where to_date('" + format1.format(par) + "','DD-MM-YY') ";
              sql+= "between to_date(start_time,'DD-MM-YY') and to_date(end_time,'DD-MM-YY')";
                
              
            System.out.println(sql);
            st = con.prepareCall(sql);
          
 

            rs = st.executeQuery();
            while (rs.next()){
             
                 Event e = new Event(rs.getString("id") + "," + rs.getString("name"),null,null);
                     Calendar cal = Calendar.getInstance();
                     cal.setTime(rs.getDate("start_time"));
                     e.setStartTime(cal);
                     cal.setTime(rs.getDate("end_time"));
                     e.setEndTime(cal);
                     result.add(e);
                              
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
     public Event getEventDetails(String par){
         String[] arr = par.split(",");
           SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-YY");
            
           String sql = "select e.id,e.name,e.start_time,e.end_time,v.name as vname,v.line_1,v.postcode,v.description"
                   + ",v.id as vid from event e,seating_plan s,venue v where  "
                   + "to_date(start_time,'DD-MM-YY') = to_date('" + arr[0];
           sql = sql +  "','DD-MM-YY') and to_date(end_time,'DD-MM-YY') = to_date('" + arr[1] + "','DD-MM-YY') ";
           sql += "and e.seating_plan = s.id and s.venue_id = v.id";
           
           
            Connection con = null;
            PreparedStatement st = null;
             ResultSet rs = null;
             
              PreparedStatement stbill = null;
             ResultSet rsbill = null;
             
             Event result = null;
             
        try{
               con = getConn();
            
             st = con.prepareCall(sql);
             rs = st.executeQuery();
             Calendar cal1 = Calendar.getInstance();
             Calendar cal2 = Calendar.getInstance();
             while (rs.next()){
                  cal1.setTime(rs.getDate("start_time"));
                  cal2.setTime(rs.getDate("end_time"));
                  result = new Event(rs.getString("name"),cal1,cal2);
                  result.setEventID(rs.getLong(1));
                  Venue v = new Venue(rs.getString("vname"));
                  v.setVenueID(rs.getLong("vid"));
                  v.setAddr1(rs.getString("line_1"));
                  v.setDescription("description");
                  v.setPostcode(rs.getString("postcode"));
                  result.setVenue(v);
                  
                  sql = "select b.lineup_order,a.name,t.name as tname from billing b,artist a,tour t where "
                          + "b.event_id=" + rs.getString("id") + " and b.tour_id = t.id";
                  sql += " and b.artist_id = a.id";
                  stbill = con.prepareCall(sql);
                  rsbill = stbill.executeQuery();
                  ArrayList<Billing> billArr = new ArrayList<Billing>();
                  
                  while (rsbill.next()){
                      Artist a = new Artist(rsbill.getString("name") + "," + rsbill.getString("tname"));
                      Billing b = new Billing(a);
                      b.setLineupOrder(rsbill.getInt("lineup_order"));
                      billArr.add(b);
                  }
                  
                  result.setBillings(billArr);
                 
             }
             
                 
          
         }
         catch (SQLException ex){
             ex.printStackTrace();
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
 
         if (rsbill != null){
             try{
             rsbill.close();
             rsbill = null;
             }
             catch (SQLException e){
                 
             }
         }
         if (stbill != null){
             try{
             stbill.close();
             stbill = null;
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
     public String[] getEventsList(){
          Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
          Vector<String> list = new Vector<String>();
          String[] result = null;
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");
         try{
               con = getConn();
             String sql = "select name,to_date(start_time,'DD-MM-YY'),to_date(end_time,'DD-MM-YY') from event" ;
             st = con.prepareCall(sql);
             rs = st.executeQuery();
             while (rs.next()){
                 list.add(sdf.format(rs.getDate(2)) + "," + sdf.format(rs.getDate(3)) + "," + rs.getString("name"));
                 
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
                 list.add(rs.getString("id") + "," + rs.getString("NAME"));
                
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
                 list.add(rs.getString("id") + "," + rs.getString("NAME"));
                
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
                 list.add(rs.getString("id") + "," + rs.getString("NAME"));
                
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
