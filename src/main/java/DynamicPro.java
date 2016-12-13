import java.util.stream.IntStream;

public class DynamicPro {

	static int array[] = new int[100];
	static int coins[] = new int[3];
	
	
	public static void main(String[] args) {
		for(int i = 0 ; i < array.length ; i++){
			array[i] = Integer.MAX_VALUE-1;
		}
		coins[0] = 2;
		coins[1] = 3;
		coins[2] = 5;
		
		array[0] = 0;
		for(int i = 1 ; i < 100 ; i++){
			for(int j =0 ; j< coins.length ; j++){
				if(coins[j] <= i && array[i-coins[j]] + 1<array[i] ){
					array[i] = array[i-coins[j]] + 1;
				}
			}
		}
		int i = 0;
		IntStream.of(array).forEach(action -> System.out.println(action));
		
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
