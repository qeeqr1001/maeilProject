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


public class compareKeyword extends Request  { //Request Ŭ���� ��ӹ��� compareKeyword
	
	public static ArrayList<String> compareResults = new ArrayList<String>();
	public static ArrayList<String> FinalResults = new ArrayList<String>();
	public static ArrayList<Integer> ev = new ArrayList<Integer>();
	public static ArrayList<String> ek = new ArrayList<String>();
	public static ArrayList<String> ml = new ArrayList<String>();
	public HashMap map=new HashMap();
	public compareKeyword() { 
		//Ű���尡 word���̺��� ������ �����Ϳ� ������ Ȯ���ϰ� �´ٸ� sql������ �׿� ��ġ�ϴ� quest�� ã��
		//+Ű���� ��� ��ġ�ϴ� ������ ã�Ƴ���.
		
		System.out.println("comparKeyword�Լ� ����");
		
    	//�Է�Ű����(arr1)�� worddic(arr2) �� �Լ�
    	int count=0;
    	PreparedStatement pstmt=null;
    	
    	for (int i=0;i<resultVoc.size();i++) {
    		
    		for (int j=0;j<worddic.size();j++) {
    			
    			if (resultVoc.get(i).equals(worddic.get(j))==true) {
                    count+=1;
                    System.out.println("!");
                    System.out.println(resultVoc.get(i)+"Ű���尡 word���̺� �ֽ��ϴ�.");
                    
                    try{ 
                    conn = DriverManager.getConnection(url, user, password);
                    String sql3="SELECT quest_content FROM quest left JOIN connect ON quest.quest_id=connect.connect_questid left JOIN word ON connect.connect_wordid=word.word_id  WHERE word_content LIKE ?";
                   
                    pstmt = conn.prepareStatement(sql3);  
//                    String k = resultVoc.get(i);
                    pstmt.setString(1, '%'+resultVoc.get(i) + '%');
                    ResultSet rs = pstmt.executeQuery(); //SQL���� �����Ͽ� ����
        			

		 			
    			    while(rs.next()){
    			    	String compareResult = rs.getString("quest_content");
    			    
    			    	compareResults.add(compareResult);
    					
    					
    				}
    			    
    			    rs.close();
    			    
    			    pstmt.close();
    	    		conn.close();
    			} catch(Exception e){
    				e.printStackTrace();
    						//���� �߻� �� ó���κ�

    			} finally { //���ܰ� �ֵ� ���� ������ ����
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
    	 
    	
       //��ġ�� ������ üũ , ��ġ�� ������ ���� ���� ������ �̾Ƴ����� ����.
        for (int i = 0; i < compareResults.size(); i++) {
             
                 if (map.containsKey(compareResults.get(i))) {
                	 int count2=(int)map.get(compareResults.get(i));
                	 map.put(compareResults.get(i), count2+1);}
                 else {map.put(compareResults.get(i), 1);}
                	 // �ߺ� �˻�
                	 
                    
                 
             
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
