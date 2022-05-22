package maeil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class compareKeyword extends Request  { //Request 클래스 상속받은 compareKeyword
	
	public static ArrayList<String> compareResults = new ArrayList<String>();
	public static ArrayList<String> FinalResults = new ArrayList<String>();
	public static ArrayList<Integer> ev = new ArrayList<Integer>();
	public static ArrayList<String> ek = new ArrayList<String>();
	public static ArrayList<String> ml = new ArrayList<String>();
	public HashMap map=new HashMap();
	public compareKeyword() { 
		//키워드가 word테이블에서 가져온 데이터와 같은지 확인하고 맞다면 sql문으로 그에 일치하는 quest를 찾음
		//+키워드 모두 일치하는 질문을 찾아내기.
		
		System.out.println("comparKeyword함수 실행");
		
    	//입력키워드(arr1)와 worddic(arr2) 비교 함수
    	int count=0;
    	PreparedStatement pstmt=null;
    	
    	for (int i=0;i<resultVoc.size();i++) {
    		
    		for (int j=0;j<worddic.size();j++) {
    			
    			if (resultVoc.get(i).equals(worddic.get(j))==true) {
                    count+=1;
                    System.out.println("!");
                    System.out.println(resultVoc.get(i)+"키워드가 word테이블에 있습니다.");
                    
                    try{ 
                    conn = DriverManager.getConnection(url, user, password);
                    String sql3="SELECT quest_content FROM quest left JOIN connect ON quest.quest_id=connect.connect_questid left JOIN word ON connect.connect_wordid=word.word_id  WHERE word_content LIKE ?";
                   
                    pstmt = conn.prepareStatement(sql3);  
//                    String k = resultVoc.get(i);
                    pstmt.setString(1, '%'+resultVoc.get(i) + '%');
                    ResultSet rs = pstmt.executeQuery(); //SQL문을 전달하여 실행
        			

		 			
    			    while(rs.next()){
    			    	String compareResult = rs.getString("quest_content");
    			    
    			    	compareResults.add(compareResult);
    					
    					
    				}
    			    
    			    rs.close();
    			    
    			    pstmt.close();
    	    		conn.close();
    			} catch(Exception e){
    				e.printStackTrace();
    						//예외 발생 시 처리부분

    			} finally { //예외가 있든 없든 무조건 실행
    				try{
    					if(pstmt!=null) {
    						pstmt.close();}
    				
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
                    
                else {
                    continue;
    		}
    	}
    		
    }
    	 
    	
       //겹치는 질문을 체크 , 겹치는 질문이 제일 많은 질문을 뽑아내려는 과정.
        for (int i = 0; i < compareResults.size(); i++) {
             
                 if (map.containsKey(compareResults.get(i))) {
                	 int count2=(int)map.get(compareResults.get(i));
                	 map.put(compareResults.get(i), count2+1);}
                 else {map.put(compareResults.get(i), 1);}
                	 // 중복 검사
                	 
                    
                 
             
         }
        Iterator it=map.entrySet().iterator();
        
        while(it.hasNext()) {
        	Map.Entry entry=(Map.Entry)it.next();
        	ev.add((Integer) entry.getValue());
        	ek.add((String) entry.getKey());
        	System.out.println(entry.getKey()+" "+entry.getValue());
    		}
        int max=ev.get(0);
        String mq=ek.get(0);
    	for(int i=0 ; i<ev.size() ; i++) {
    		
			if(max>=ev.get(i)) {
				max=max;
				mq=mq;
				
			}
		
			else {
				max=ev.get(i);
				
			}
        	
			
        }
    	for(int i=0 ; i<ev.size() ; i++) {
    	if (ev.get(i)==max) {
    		mq=ek.get(i);
			ml.add(mq);
    	}
    	}
		
    	System.out.println(max);
    	System.out.println(ml);
    }

	
	
}
