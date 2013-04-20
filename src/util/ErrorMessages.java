/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author snowman
 */
public class ErrorMessages {
    public String getMessage(String sql){
        String sqlCode = sql.toLowerCase();
        String result = "";
        if(sqlCode.contains("customer_title_fk"))
            result =  "Please enter a valid title";
        
         if(sqlCode.contains("customer_forename_nn"))
            result =  "Please enter first name";
         
         if(sqlCode.contains("customer_forename_chk"))
            result =  "First name must begin with capital letter";
         
         if(sqlCode.contains("customer_surname_nn"))
            result =  "Enter Surname";
         
          if(sqlCode.contains("customer_surname_chk"))
            result =  "Surname must start swith capital letter";
          
           if(sqlCode.contains("customer_telephone_chk"))
            result =  "Wrong telephone number";
           
           if(sqlCode.contains("customer_mobile_chk"))
            result =  "Wrong mobile number";
           
           if(sqlCode.contains("customer_line_1_chk"))
            result =  "Address 1 must start with capital letter";
           
            if(sqlCode.contains("customer_line_2_chk"))
            result =  "Address 2 must start with capital letter";
            
            if(sqlCode.contains("customer_town_chk"))
            result =  "Town must start with capital letter";
            
            if(sqlCode.contains("customer_county_chk"))
            result =  "County must start with capital letter";
            
             if(sqlCode.contains("customer_postcode_chk"))
            result =  "Post code must be capital letters";
             
             
             if(sqlCode.contains("artist_name_nn"))
            result =  "Enter Artist Name";
             
             if(sqlCode.contains("artist_name_chk"))
            result =  "Artist Name should begin with capital letter";
             
           
             if(sqlCode.contains("venue_name_nn"))
            result =  "Enter venue name";
             
                if(sqlCode.contains("venue") && sqlCode.contains("null") )
            result =  "Enter venue name";
             
             
                if(sqlCode.contains("venue_name_chk"))
            result =  "Venue Name must start with capital letter";
                
               if(sqlCode.contains("venue") && sqlCode.contains("null") && sqlCode.contains("line_1") )
            result =  "Enter Address 1";
          
             if(sqlCode.contains("venue_line_1_nn"))
            result =  "Enter Address 1";
             
                if(sqlCode.contains("venue_line_1_chk"))
            result =  "Address shall start with capital letter";
                
             if(sqlCode.contains("venue") && sqlCode.contains("postcode") && sqlCode.contains("null"))
            result =  "Enter post code";
             
             if(sqlCode.contains("venue_postcode_nn"))
            result =  "Enter post code";
             
             if(sqlCode.contains("venue_postcode_chk"))
            result =  "Post Code must be capital letters";
             
               if(sqlCode.contains("event_name_nn"))
            result =  "Enter event name";
               
                 if(sqlCode.contains("event_name_chk"))
            result =  "Event name must start with capital letter";
                 
              
                 if(sqlCode.contains("event_start_time_nn"))
            result =  "Enter event start time";
                     
                 if(sqlCode.contains("event_end_time_nn"))
            result =  "Enter event end time";
                
                     if(sqlCode.contains("venue_name_nn"))
            result =  "Enter Venue Name";
                     
              
                       if(sqlCode.contains("venue_name_chk"))
            result =  "Venue Name must begin with capital letter";
                       
                             if(sqlCode.contains("venue_line_1_nn"))
            result =  "Enter Venue Address";
                             
                  if(sqlCode.contains("venue_line_1_chk"))
            result =  "Venue Address must begin with capital letter";
                  
                   if(sqlCode.contains("venue_postcode_nn"))
            result =  "Enter Venue post code";
                   
                 if(sqlCode.contains("venue_postcode_chk"))
            result =  "Post code must be capital letter";
                 
          if(sqlCode.contains("tour_name_nn"))
            result =  "Enter Tour Name";
          
            if(sqlCode.contains("tour_name_chk"))
            result =  "Tour name must start with capital letter";
            
           
       if(sqlCode.contains("tour") && sqlCode.contains("name") && sqlCode.contains("null"))
              result =  "Enter Tour Name";
            
            
                   
                   
                       
                       
                     
                 
                 
                     
                     
               
               
         
         
   
        
        return result;
        
    }
    
}
