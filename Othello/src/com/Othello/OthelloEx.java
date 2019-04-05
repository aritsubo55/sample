package com.Othello;

import utility.SimpleReader;
//Othelloの実行クラス
public class OthelloEx {

	public static void main(String[] args) {
		Boad playBoad = new Boad();//boad作成
		playBoad.playable();
		Player[] players = new Player[2];
		for(int i = 0 ; i < players.length ; i++){
			System.out.println("プレイヤー" + (i+1) + "をコンピュータにしますか? Yes:0  No:それ以外 ");
			if(SimpleReader.intSystemRead() == 0){
				players[i] = new ComPlayer(playBoad);
				System.out.println(players[i].getName() + "はコンピューターです。");
			}else{
				players[i] = new ManualPlayer(playBoad);
			}
		}
		int turn = 0;//ターンカウント
		//ターン処理passが2回続けばゲーム終了
		while(Player.passCount<2){
			Player pl = players[turn % players.length];
			playBoad.show();//現在の盤表示
			System.out.println(pl.getColor() + "の番です");
			pl.play();//プレイヤーの手番を行う
			turn++;
		}
		//試合終了処理
		int[] points = playBoad.pointCount(players[0].getPlNo(),players[1].getPlNo());
		for(int i = 0 ; i < players.length ;i++){
			players[i].setPoint(points[i]);
		}
		players[0].gameEnd(players[1]);

	}

}
