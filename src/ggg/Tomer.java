package ggg;


public class Tomer {

	public static void main(String[] args) {

		int [] arr = new int [101];
		
		for (int a = 0; a <=100;a++ ) {
		
			arr[a]=0;
		
		
		for (int j = 1; j <= 100; j++) {
		
		for (int i = 0; i <=100 ; i=i+j) {
			if (arr[i]==0){
				arr[i]=1;
			}else if (arr[i]==1) {
				arr[i]=0;
			System.out.println(i);
			}
		}
		
		}
		}
	}

}
			
		


