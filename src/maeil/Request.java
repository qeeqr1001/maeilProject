package maeil;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Scanner;
import org.snu.ids.kkma.ma.Eojeol; //형태소 분석기로 꼬꼬마 사용
import org.snu.ids.kkma.ma.MExpression;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;


public class Request  extends getWorddic { //getWorddic 상속받은 Request 클래스
	 public static ArrayList<String>  vocList = new ArrayList<String>(); //
	 public static ArrayList<String>  resultVoc = new ArrayList<String>(); //질문에서 명사,용언 등의 형태소만 뽑아낸 배열 resultVoc.

	 public Request() { //질문 입력 받고 형태소 분석.

		  Scanner sc=new Scanner (System.in);
		  System.out.println("궁금한 점을 물어보세요.");
		  
          String string = sc.nextLine();
  
          try { 
                MorphemeAnalyzer ma = new MorphemeAnalyzer();
                ma.createLogger(null);
               
                List<MExpression> ret = ma.analyze(string);
               
                ret = ma.postProcess(ret);
                ret = ma.leaveJustBest(ret);
                int[] count=new int[5];
                List<Sentence> stl = ma.divideToSentences(ret); 
                
                for( int i = 0; i < stl.size(); i++ ) {
                      Sentence st = stl.get(i); 
                      Spliterator<Eojeol> sp = st.spliterator();
                      count[i]+=1;
                      
//                      System.out.println("=============================================  " + st.getSentence());
                      for( int j = 0; j < st.size(); j++ ) {
//                            System.out.println(st.get(j));
//                            System.out.println(count[i]);
//                            System.out.println(sp);
                            vocList.add(String.valueOf(st.get(j)));
                      }
                      
                }
                for(int i=0;i< vocList.size();i++){
                    String str1 = vocList.get(i);
                    String[] spStr = str1.split("\\+");
         
                    for(int j=0;j<spStr.length;j++){
                        String[] spStr2 = spStr[j].split("/");
                        for(int k=0; k<spStr2.length;k++){ //내가 뽑아내고 싶은 형태소와 같을때 그 키워드?를 resultVoc 배열에 추가.
                            if(spStr2[k].equals("NNG") || spStr2[k].equals("NNG]")
                                    || spStr2[k].equals("NNP") || spStr2[k].equals("NNP]")
                                    || spStr2[k].equals("NNB") || spStr2[k].equals("NNB]")
                                    || spStr2[k].equals("NR") || spStr2[k].equals("NR]")
                                    || spStr2[k].equals("NNG") || spStr2[k].equals("NNG]")
                                    || spStr2[k].equals("VV") || spStr2[k].equals("VV]")
                                    || spStr2[k].equals("VA") || spStr2[k].equals("VA]")
                                    || spStr2[k].equals("VX") || spStr2[k].equals("VX]")
                                    || spStr2[k].equals("VCP") || spStr2[k].equals("VCP]")
                                    || spStr2[k].equals("VCN") || spStr2[k].equals("VCN]")|| spStr2[k].equals("MAG")
                            		|| spStr2[k].equals("MAG]")|| spStr2[k].equals("NP") || spStr2[k].equals("NP]"))
                            {
                                resultVoc.add(spStr2[k-1]);
                               
                            }
         
                        }
                        
                    }
                   
                }
                
                if (resultVoc.size()<1) {
                	System.out.println("잘못된 질문입니다. 다시 입력해주세요.(나오는 키워드X)");
                	System.exit(0);
                }
             
                System.out.println(resultVoc);
               
               
          } catch (Exception e) {
                e.printStackTrace();
          }
          
       
		 
          
    }
		
		



}