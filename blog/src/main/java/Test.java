
public class Test {
	public static void main(String[] args) {
		int[][] initArray=  {  
		        {5 ,3 ,7 } ,  
		        {9, 1, 6 } ,  
		        {2 ,4 ,8}  
		    }; 

		   int length =initArray.length*initArray[0].length;
	       
	        int[] arrayL = new int[length];
	        int i = 0;
	        int hh = initArray[0].length-1;
	       for (int r = 0; r < initArray.length; r++) {
	    	   for (int h = 0; h< initArray[0].length; h++) {
	    		   if(r%2==1){
	    				   arrayL[i] = initArray[r][hh];
	    				   --hh;
	    				   i++;
	    		   }else {
	    			   arrayL[i] = initArray[r][h];
	    			   i++;
				}
			}
	    	   hh = initArray[0].length-1;
		}
//	       for(int kk = 0 ;kk<length;kk++){
//	    	   System.out.println(arrayL[kk]+"\n");
//	       }
	       
	       
	       
	       
	       
	       //ð������
	       int sum = 0;
	       int low = 0;   
	       int high= length -1; //���ñ����ĳ�ʼֵ  
	       int tmp,j;  
	       while (low < high) {  
	           for (j= low; j< high; ++j) //����ð��,�ҵ������  
	               if (arrayL[j]> arrayL[j+1]) {  
	            	   int begin = initArray[j/initArray[0].length][j%initArray[0].length];
	            	   int end = initArray[j+1/initArray[0].length][j+1%initArray[0].length];
	            	   //��ȡ��ά���������
	            	   if(j/initArray[0].length%2==1){
	            		   //��ʼ����
	            		    begin = initArray[j/initArray[0].length][initArray[0].length-(j%initArray[0].length)-1];
	            	   }
	            	   if(j+1/initArray[0].length%2==1){
	            		   //��ʼ����
	            		    end = initArray[j+1/initArray[0].length][initArray[0].length-(j+1%initArray[0].length)-1];
	            	   }
	            	   
	                   tmp = arrayL[j]; arrayL[j]=arrayL[j+1];arrayL[j+1]=tmp; 
	                   sum++;
	               }   
	           --high;                 //�޸�highֵ, ǰ��һλ  
	           for ( j=high; j>low; --j) //����ð��,�ҵ���С��  
	               if (arrayL[j]<arrayL[j-1]) {  
	            	   
	            	   int begin = initArray[j/initArray[0].length][j%initArray[0].length];
	            	   int end = initArray[j-1/initArray[0].length][j-1%initArray[0].length];
	            	   //��ȡ��ά���������
	            	   if(j/initArray[0].length%2==1){
	            		   //��ʼ����
	            		    begin = initArray[j/initArray[0].length][initArray[0].length-(j%initArray[0].length)-1];
	            	   }
	            	   if(j+1/initArray[0].length%2==1){
	            		   //��ʼ����
	            		    end = initArray[j-1/initArray[0].length][initArray[0].length-(j-1%initArray[0].length)-1];
	            	   }
	            	   
	                   tmp = arrayL[j]; arrayL[j]=arrayL[j-1];arrayL[j-1]=tmp;  
	                   sum++;
	               }  
	           ++low;                  //�޸�lowֵ,����һλ  
	       }   
	       
	       
	       for(int kk = 0 ;kk<length;kk++){
	    	   System.out.println(arrayL[kk]);
	    	   
	       }

	       System.out.println("\n"+"@@@@@@"+"   "+sum);
	}
	
	
	

}
