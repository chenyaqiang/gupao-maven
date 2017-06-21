package algorithm.basic;



public class BasicAlgorithm {
	public static void main(String[] args) {
	}


	public static String abc(int n){
		if (n<=0) {
			return "";
		}
		return abc(n-3)+ n + abc(n-2)+n;
	}
	
	public static int aaa(int a,int b){
		if (b==0) {
			return 0;
		}
		if (b%2==0) {
			return aaa(a+a, b/2);
		}
		return aaa(a+a, b/2)+a;
	}

	/**
	 * &&��||ִ��˳��ͬ�������Դ�������˳��ִ��
	 * @author wy
	 */
	private static void booleantest() {
		System.out.println(true&&false||true&&true);
	}
	
	
	

	/**
	 * ʵ�ֶ���
	 * @author wy
	 */
	private static void log() {
		int n=10000;
		int count=0;
		while (n>=2) {
			n=n/2;
			count++;
		}
		System.out.println(count);
	}

	/**
	 * ������ת��
	 * @author wy
	 */
	private static void arr() {
		String arr[][]={{"0,0","0,1","0,2"},{"1,0","1,1","1,2"}};
		String arr2[][]=new String[arr[0].length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr2[j][i]=arr[i][j];
			}
		}
		
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				System.out.println(arr2[i][j]);
			}
		}
	}

	/**
	 * ������ת��Ϊ�������ַ���
	 * @author wy
	 */
	private static void toBinaryString() {
		int n=999;
		StringBuffer sbBuffer=new StringBuffer();
		while(n>0){
			sbBuffer=sbBuffer.append(n%2);	
			n=n/2;
		}	
		System.out.println(sbBuffer);
	}

	/**
	 * ��1+...999
	 * @author wy
	 */
	private static void test1() {
		int sum=0;
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < i; j++) {
				sum++;
			}
		}
		System.out.println(sum);
	}
}
