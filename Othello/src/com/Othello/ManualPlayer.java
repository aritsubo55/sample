package com.Othello;
import utility.SimpleReader;
import utility.Vectors;
//プレイヤーが操作する場合のクラス
public class ManualPlayer extends Player{
	//コンストラクタ
	
	public ManualPlayer(Boad boad){
		super(boad);
	}
	public ManualPlayer(String name , Boad boad){
		super(name , boad);
	}
	
	//メソッド
	
	//手番の標準行動
	void play(){
		int ty = 0;
		int tx = 0;
		boolean upsetOk = false;
		Vectors takeVec =null;
		while(true){
			System.out.print("0でパス  置く場所 :");
			int take = SimpleReader.intSystemRead();
			if(take == 0){
				addPassCount();
				break;//手番終了
			}
			int bsize = boad.getSize();
			ty = (take - 1) / bsize;//take i
			tx = (take - 1) % bsize;//take j
			if(take > 0 && ty < bsize && boad.getFieldNum(ty, tx) ==0){
				takeVec = boad.check(ty , tx , plNo);
				if(takeVec.getSum() > 0){
					upsetOk = true;
					break;//置けたらbreak
				}
			}
			System.out.println("そこには置けません");
		}
		if(upsetOk){//ひっくり返せたらパスをリセットしupsetを行う
			resetPass();
			boad.upset(ty , tx , takeVec , plNo);
		}
	}
}
