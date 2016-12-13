import java.util.stream.IntStream;

public class DynamicPro2 {

	static int array[] = new int[100];
	static int coins[] = new int[3];
	
	
	public static void main(String[] args) {
		int a[] ={ 1, 7, 4, 9, 2, 5 };
		System.out.println(longestZigZag(a));
		int b[] = { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
		System.out.println(longestZigZag(b));
		int c[] = 	{ 44 };
		System.out.println(longestZigZag(c));
		int d[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(longestZigZag(d));
		int e[] = 	
			{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
		System.out.println(longestZigZag(e));
		int f[] = 		{1, 7 ,10 ,8};
		System.out.println(longestZigZag(f));
		
	}
	
	static int longestZigZag(int[] array){
		 int ret = 0;
		if(array.length > 1){
			int e = array[0];
			int e1 = array[1];
			
			boolean inc = (Integer.compare(e,e1 )) < 0 ? true:false;
			
			for(int i = 1 ; i < array.length ; i++){
				if(inc && e < array[i]){
					ret++;
					inc = !inc;
				}else if(!inc && e > array[i]){
					ret++;
					inc = !inc;
				}
				e = array[i];
			}	
			return ret+1;
		}else{
			return 1;
		}
		
	}
	
	
//	Set Min[i] equal to Infinity for all of i
//	Min[0]=0
//
//	For i = 1 to S
//	For j = 0 to N - 1
//	   If (Vj<=i AND Min[i-Vj]+1<Min[i])
//	Then Min[i]=Min[i-Vj]+1
//
//	Output Min[S]
//	
	
}
