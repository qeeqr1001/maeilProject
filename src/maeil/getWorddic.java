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
    public getWorddic(){ //DB���� word������ �������� �Լ�. (worddic)
    	
      
        try{ //DB���� �� DB���� word������ ��������.
        	 System.out.println("Connect ����");

        	  Class.forName("com.mysql.jdbc.Driver");
    			conn = DriverManager.getConnection(url, user, password);
    			state = conn.createStatement();
    			String sql; //SQL���� ������ String
    			String sql2;
    			sql = "SELECT * FROM quest";
    			sql2="SELECT * FROM word";
    			ResultSet rs = state.executeQuery(sql2); //SQL���� �����Ͽ� ����
    			

    			 			
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
    						//���� �߻� �� ó���κ�

    			} finally { //���ܰ� �ֵ� ���� ������ ����
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
