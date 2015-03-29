package ua.kture.peresunko.Lab1;

public class Lab1_2part {
	
		public static void printArray(int[][] array,int N){
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
						System.out.print(array[i][j]+ " ");
				}
					System.out.println();
			}
		}
		
		public static void main(String[] args) {
			int N=4;
//			int[][] array=new int[N][N];
//			
//			for(int i=0;i<N;i++){
//				for(int j=0;j<N;j++){
//					array[i][j]=(int) (Math.random()*10);
//				}
//			}
			
			int[][] array={
					{0,1,2,4},
					{1,0,3,5},
					{2,3,0,6},
					{4,5,6,0}
			};
			
			printArray(array,N);
			
			boolean isSymmetric=true;
			for(int i=0;i<N && isSymmetric==true;i++){
				for(int j=0;j<N && isSymmetric;j++){
					if(i==j) continue;
					if(array[i][j]==array[j][i]){
						isSymmetric=true;
						continue;
					}
					else{
						isSymmetric=false;					
					}
				}
			}
			if(isSymmetric==true){
				System.out.println("The matrix is symmetric with respect to the main diagonal");
			}
			else{
				System.out.println("The matrix is not symmetric with respect to the main diagonal");
			}

		}
}