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
 
public class EventDAO {
       //This method uses the ConnectionPar Class to obtain connection to database
   private Connection getConn(){
    
        return new util.ConnectionPar().getConn();
    }
 
   //This method takes Event object as a parameter, and update the corresponding event
   //row in the event table
    public String updateEvent(Event e){
       
         
         String result = "0";
         Connection con = null;
          //building the sql command
         String SQLCommand = "Update event set name=?,start_time=?,end_time=? where id=?";
          
         try{
             //obtain the database connection by calling getConn() 
               con = getConn();
                 //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            
            //setting the parameters values
            ps.setString(1, e.getName());   
            ps.setDate(2, new java.sql.Date(e.getStartTime().getTime()));
            ps.setDate(3, new java.sql.Date(e.getEndTime().getTime())); 
            ps.setLong(4,e.getId());
            //ps.setLong(4, sp.getSeatingPlanID());
            
            
             //exceuting the insert or update operation 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
    
     //This method takes Event object as a parameter, and  insert a new row in the event table
    //with the data contained in the Event object
     public String indertEvent(Event e){
         
         
         String result = "0";
         Connection con = null;
         //building the sql
         String SQLCommand = "INSERT INTO event " +   
                    "(id,name,start_time,end_time,seating_plan_id) " +   
                    "VALUES (seq_event_id.NEXTVAL,?,?,?,1)";  
         try{
              //obtain the database connection by calling getConn() 
               con = getConn();
               
             //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);  
            
             //setting the parameters values
            ps.setString(1, e.getName());   
            ps.setDate(2, new java.sql.Date(e.getStartTime().getTime()));
            ps.setDate(3, new java.sql.Date(e.getEndTime().getTime())); 
            //ps.setLong(4, sp.getSeatingPlanID());
            
            
              //exceuting the insert or update operation 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
 
     //takes Tour object and Event object as paraemters, and insert a new row in billing table
     //using the values contained in these objects
       public String addBilling(Tour t,Event e){
      
         
         String result = "0";
         Connection con = null;
         //build the sql
         String SQLCommand = "INSERT INTO billing(id,lineup_order,event_id,artist_id,tour_id)" +
                 " values (seq_billing_id.NEXTVAL,?,?,?,?)";
              
         try{
              //obtain the database connection by calling getConn() 
               con = getConn();
               Billing b = t.getBills().get(0);
               
               
             //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql , parameters are represented by ? in the sql
            PreparedStatement ps = con.prepareStatement(SQLCommand);   
            
            //setting the parameters values
            ps.setInt(1,b.getLineupOrder());
            ps.setLong(2,e.getId());
            ps.setLong(3, b.getArtist().getId());
           ps.setLong(4, t.getId());
          
           //exceuting the insert or update operation 
            ps.executeUpdate(); 
         }
         catch (SQLException ex){
             ex.printStackTrace();
             result = ex.getMessage();
         }
         return result;
     }
  
   //This method takes a Date as parameter, and returns all events which will take place during this date
       //it returns a Vector of Event objects
     public Vector<Event> getEventsInDay(Date par){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Vector<Event> result = new Vector<Event>();
         try {
             //obtain the database connection by calling getConn()
            con = getConn();
            
           SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-YY");
         
           //build the sql statement
              String sql =  "select * from event where to_date('" + format1.format(par) + "','DD-MM-YY') ";
              sql+= "between to_date(start_time,'DD-MM-YY') and to_date(end_time,'DD-MM-YY')";
                
              
            System.out.println(sql);
             //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql that is constructed using the supplied parameters
            st = con.prepareCall(sql);
          
 
           //Calls executeQuery and the resulting data is returned in the resultset
            rs = st.executeQuery();
            
             //loop through the result set and save the data in the datamodel objects
            while (rs.next()){
             
                 Event e = new Event(rs.getLong("id"),rs.getString("id") + "," + rs.getString("name"),rs.getDate("start_time"),rs.getDate("end_time"));
                   ;
                     result.add(e);
                              
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
     
     //takes start and end date as parameters,  and returns the corresponding event in the Event object
     public Event getEventDetails(String par){
         String[] arr = par.split(",");
           SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-YY");
            //build sql
           String sql = "select e.id,e.name,e.start_time,e.end_time,v.name as vname,v.line_1,v.postcode,v.description"
                   + ",v.id as vid from event e,seating_plan s,venue v where  "
                   + "to_date(start_time,'DD-MM-YY') = to_date('" + arr[0];
           sql = sql +  "','DD-MM-YY') and to_date(end_time,'DD-MM-YY') = to_date('" + arr[1] + "','DD-MM-YY') ";
           sql += "and e.seating_plan_id = s.id and s.venue_id = v.id";
           
           
            Connection con = null;
            PreparedStatement st = null;
             ResultSet rs = null;
             
              PreparedStatement stbill = null;
             ResultSet rsbill = null;
             
             Event result = null;
             
        try{
             //obtain the database connection by calling getConn()
               con = getConn();
            
                //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql that is constructed using the supplied parameters
             st = con.prepareCall(sql);
             
              //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             Calendar cal1 = Calendar.getInstance();
             Calendar cal2 = Calendar.getInstance();
             
             //loop through the result set and save the data in the datamodel objects
             while (rs.next()){
                  
                  result = new Event(rs.getLong("id"),rs.getString("name"),rs.getDate("start_time"),rs.getDate("end_time"));
                  Venue v = new Venue(rs.getString("vname"),0d,0d);
                  v.setId(rs.getLong("vid"));
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
                      Artist a = new Artist(0l,rsbill.getString("name") + "," + rsbill.getString("tname"),
                              null,null,null,null);
                      Billing b = new Billing(0l,a,null,rsbill.getInt("lineup_order"));
                      billArr.add(b);
                  }
                  
                  result.setBillings(billArr);
                 
             }
             
                 
          
         }
         catch (SQLException ex){
             ex.printStackTrace();
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
     
     
  //returns a list of all events, it resturns an array of strings, this method is called
     //by the JFrame in order to fill List or table controls
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
                //build sql
             String sql = "select name,to_date(start_time,'DD-MM-YY'),to_date(end_time,'DD-MM-YY') from event" ;
             
              //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql 
             st = con.prepareCall(sql);
             
             //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
               //loop through the result set and save the data in the datamodel objects
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
    
     //returns a list of all tours, it resturns an array of strings, this method is called
     //by the JFrame in order to fill List or table controls
        public String[] getToursList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             //obtain the database connection by calling getConn()
             con = getConn();
             
             //create the PreparedStatement from the Connection object by calling prepareCall
            //method, passing it the sql 
             st = con.prepareStatement("select * from Tour");
             
              //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
             //loop through the result set and save the data in the datamodel objects
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
     
      //returns a list of all artists, it resturns an array of strings, this method is called
     //by the JFrame in order to fill List or table controls
     public String[] getArtistsList() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
         Vector<String> list = new Vector<String>();
         String[] result = null;
         try {
             //obtain the database connection by calling getConn()
             con = getConn();
             //create the PreparedStatement from the Connection object by calling prepareStatementl
            //method, passing it the sql 
             st = con.prepareStatement("select * from Artist");
             
              //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
              //loop through the result set and save the data in the datamodel objects
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
  //returns a list of all venues, it resturns an array of strings, this method is called
     //by the JFrame in order to fill List or table controls    
      public String[] getVenueList() {
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
             st = con.prepareStatement("select * from Venue");
             
              //Calls executeQuery and the resulting data is returned in the resultset
             rs = st.executeQuery();
             
             //loop through the result set and save the data in the datamodel objects
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
