package maeil;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class selectQuest extends compareKeyword {
	public selectQuest(){
		PreparedStatement pstmt=null;
	
	System.out.println(ml);
	Scanner sc=new Scanner (System.in);
	System.out.println("���ϴ� ������ ��ȣ�� �������ּ���.");
	int num = sc.nextInt();
	System.out.println(num);
	String FinalQuest = null;
	for (int i=0;i<ml.size();i++) {
		if (i==num-1) {
			System.out.println("����ڰ� ������ ����");
			System.out.println(ml.get(i));
			FinalQuest=ml.get(i);
		}
	
	}
	
	 try{ 
		 conn = DriverManager.getConnection(url, user, password);
     
   
		 String sql="SELECT ans_content FROM answer INNER JOIN quest ON answer.ans_id=quest.quest_id  WHERE quest_content like ?";
		 pstmt = conn.prepareStatement(sql);  

		 pstmt.setString(1, '%'+ FinalQuest + '%');
		 ResultSet rs = pstmt.executeQuery(); //SQL���� �����Ͽ� ����
		 
		 while(rs.next()){
		    	String QR = rs.getString("ans_content");
		    	System.out.println(QR);
		    	
				
				
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
}