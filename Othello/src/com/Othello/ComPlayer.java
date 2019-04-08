package com.Othello;

import utility.Vectors;

public class ComPlayer extends Player{
	Boad priority;//優先順位を作り保存する

	//コンストラクタ
	public ComPlayer(Boad boad){
		this("プレイヤー" + (counter+1),boad);
	}
	public ComPlayer(String name , Boad boad){
		super(name , boad);
		priority = new Boad(boad.getSize());
		setPriority();
	}
	//メソッド

	//手番の標準行動
	public void play(){
		int ty = 0;
		int tx = 0;
		int take = autoChoice();
		if(take == 0){
			System.out.println("パスしました。");
			addPassCount();
		}else{
			resetPass();
			System.out.println(take + "に置きました。");
			ty = (take - 1) / boad.getSize();//take i
			tx = (take - 1) % boad.getSize();//take j
			boad.upset(ty , tx , boad.check(ty , tx , plNo) , plNo);
		}
	}
	//どこに駒を置くのか自動で判定し値を返す
	private int autoChoice(){
		int n = priority.getSize();
		//2,1,0,-1の順に入力を試す
		for(int k = 2 ; k >= -1 ; k--){
			int max = 0;
			int[] maxIJ = new int[2];
			for(int i = 0 ; i < n ; i++){
				for(int j = 0 ; j < n ; j++){
					if(priority.getFieldNum(i, j) == k){
						if(boad.getFieldNum(i, j) == 0){
							Vectors v = boad.check(i , j , plNo);
							int sum = v.getSum();
							if(max < sum || (max != 0 && max == sum && (int)(Math.random() * 2) == 1)){
								max = sum;
								maxIJ = new int[]{i , j};
							}
						}
					}
				}
			}
			//優先順位が↑で置ける場所があればreturn
			if(max > 0){
				return (maxIJ[0] * n + maxIJ[1] +1);
			}
		}
		//全部調べてmax == 0 なら0を返す(パス)
		return 0;
	}
	//盤の中で駒を置く優先順位を設定する。これを呼ぶか呼ばないかでcomの強さを変更可能になる予定
	private void setPriority(){
		int n = boad.getSize();
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < n ; j++){
				if(i == 0 || i == (n-1) || j == 0 || j == (n-1)){
					if( i == j || i == (n - 1 - j)){
						priority.setFieldNum(i, j,2 );
					}else priority.setFieldNum(i, j, 1);;
				}
				if(i == 1 || i == (n-2) || j == 1 || j == (n-2)){
					priority.setFieldNum(i, j, -1 );
				}
			}
		}
	}
}
