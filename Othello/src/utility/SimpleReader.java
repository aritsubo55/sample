package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleReader{
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

/*	標準入力を要求しString型を返すメソッド*/
	public static String strSystemRead(){
		String line = "";
		while(true){//String line = "";
			try{
				line = reader.readLine();
				if(line != null && !line.isEmpty()){ 
					return line;
				}
			}catch(IOException e){
				System.out.println(e);
			}
			System.out.println("不正な入力です。再入力してください。");
		}//return line;
	}

/*	標準入力を要求し入力されたString型をint型に変換して返すメソッド
	数値以外が入力された場合、標準出力で警告を発し再入力を求める*/
	public static int intSystemRead(){
		while(true){
			try{
				return Integer.parseInt(strSystemRead());
			}catch(NumberFormatException e){
				System.out.println("不正な入力です。数値を入力してください。");
				//continue;
			}
		}
	}
}
