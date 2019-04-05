package utility;

public class Vectors {
	final static int[][] VECTOR = {
		{0,-1},{1,0},{0,1},{-1,0},//上下左右ベクトル
		{1,-1},{1,1},{-1,1},{-1,-1}//斜め方向ベクトル
	};
	int[] scale;
	public static int length = VECTOR.length; 
	public Vectors(){
		scale = new int[VECTOR.length];
	}
	public static int getuniY(int index){
		return VECTOR[index][0];
	}
	public static int getuniX(int index){
		return VECTOR[index][1];
	}
	public int getValue(int index){
		return scale[index];
	}
	public void setValue(int index , int value){
		scale[index] = value;
	}
	public int getSum(){
		int sum = 0;
		for(int n : scale){
			sum += n;
		}
		return sum;
	}
}
