package com.Othello;

import utility.Vectors;

public class Boad {
	static char[] token = {'●' , '○'};
	private int size;
	int[][] field;
	Boad checkBoad;//チェック用に使用する checkメソッド内で作成
	//コンストラクタ
	
	public Boad(){
		this(4*2);
	}
	public Boad(int size){
		this.size = size;
		makeBoad();
	}
	//メソッド
	
	//盤の状態を表示するメソッド
	public void show(){
		for(int i = 0 ; i < size ; i++){
			for(int j = 0 ; j < size ; j++){
				if(field[i][j] == 1){
					System.out.print(" " + token[0]);
				}else if(field[i][j] == 2){
					System.out.print(" " + token[1]);
				}else{
					System.out.printf("%3d", i*size + j+1);
				}
			}
			System.out.println();
		}
	}
	//tNumで与えられた駒をyxに置いた場合にとることのできるコマ数をVectors型で返す
	public Vectors check(int y , int x , int tNum){
		if(checkBoad == null) checkBoad = new Boad(1+size+1);
		
		for(int i = 0 ; i < size ; i++){
			for(int j = 0 ; j < size ; j++){
				checkBoad.field[i+1][j+1]= field[i][j];
			}
		}
		Vectors vec = new Vectors();
		for(int i = 0 ; i < Vectors.length ; i++){
			//チェック用に合わせて1増やす
			int yNow = y+1;
			int xNow = x+1;
			int count = 0;
			while(true){
				yNow += Vectors.getuniY(i);
				xNow += Vectors.getuniX(i);
				int tmp = checkBoad.field[yNow][xNow];
				if (tmp == 0){
						break;
				}else if(tmp != tNum){
					count++;
				}else {
					if(count > 0){
						vec.setValue(i, count);
					}
					break;
				}
			}
		}	
		return vec;
	}
	int interpose(int yNow , int xNow , int tNum , int vectIndex , int count){
		int tmp = checkBoad.field[yNow][xNow];
		int ans = 0;
		if (tmp == 0){//空のマスに当たったら0を返す
		}else if(tmp != tNum){
			ans = interpose(yNow+Vectors.getuniY(vectIndex) , xNow+Vectors.getuniY(vectIndex) ,
								tNum,vectIndex,count+1);
		}else {
			if(count > 0){
				ans = count;
			}
		}
		return ans;
	}
	void upset(int y , int x ,Vectors vect , int tNum){
		setFieldNum(y, x,tNum);
		for(int i = 0 ; i < Vectors.length ; i++){
			int yNow = y;
			int xNow = x;
			for(int j = 0 ; j < vect.getValue(i) ; j++){
				yNow += Vectors.getuniY(i);//方向に１マス動いて
				xNow += Vectors.getuniX(i);
				setFieldNum(yNow, xNow, tNum);//tNumの色になる
			}
		}
	}
	int[] pointCount(int tNum1,int tNum2){//フィールドの中のtNum1とtNum2それぞれの数をカウント
		int p1 = 0;
		int p2 = 0;
		for(int i = 0 ; i < size ; i++){
			for(int j = 0 ; j < size ; j++){
				if(field[i][j] == tNum1) p1++;
				if(field[i][j] == tNum2) p2++;
			}
		}
		return new int[]{p1,p2};
	}
	void makeBoad(){//sizeと同じフィールドを新規作成
		field = new int[size][size];
	}
	void playable(){//プレイ準備 中央に駒を配置
		int n = size/2;
		field[n-1][n-1] = 1;
		field[n-1][n] = 2;
		field[n][n-1] = 2;
		field[n][n] = 1;
	}
	//アクセサ
	void setFieldNum(int y , int x , int value){
		field[y][x] = value;
	}
	int getFieldNum(int y , int x){//フィールドの値を得る
		return field[y][x];
	}
	int getSize(){
		return size;
	}

}
