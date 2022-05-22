package maeil;

import java.sql.*;
import java.util.ArrayList;
 
 
public class getWorddic {
    public static String user = "dosly2";
    public static String password = "maeil123";
    public static String url = "jdbc:mysql://dosly2.cafe24.com/dosly2?useUnicode=true&serverTimezone=Asia/Seoul&autoReconnect=true";
    public static ArrayList<String> worddic = new ArrayList<String>();		
    public static Connection conn=null;
    public static Statement state=null;
    public getWorddic(){ //DB에서 word데이터 가져오는 함수. (worddic)
    	
      
        try{ //DB연결 후 DB에서 word데이터 가져오기.
        	 System.out.println("Connect 시작");

        	  Class.forName("com.mysql.jdbc.Driver");
    			conn = DriverManager.getConnection(url, user, password);
    			state = conn.createStatement();
    			String sql; //SQL문을 저장할 String
    			String sql2;
    			sql = "SELECT * FROM quest";
    			sql2="SELECT * FROM word";
    			ResultSet rs = state.executeQuery(sql2); //SQL문을 전달하여 실행
    			

    			 			
    			    while(rs.next()){
    					String content = rs.getString("word_content");
    					worddic.add(content);
    					
    					
    				}
    			    System.out.println(worddic);
    			    
    				rs.close();
    				state.close();
    				conn.close();
    			} catch(Exception e){
    				e.printStackTrace();
    						//예외 발생 시 처리부분

    			} finally { //예외가 있든 없든 무조건 실행
    				try{
    					if(state!=null)
    						state.close();
    				}catch(SQLException ex1){
    					//
    				}
    						
    				try{
    					if(conn!=null)
    						conn.close();
    				}catch(SQLException ex1){
    					//
    				}
    			}
        
      
       
    }
    
    
    
 
 
        
}
