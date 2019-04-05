package com.Othello;
//プレイヤー系の抽象スーパークラス
public abstract class Player {
	protected static int passCount = 0;
	static int counter = 0;
	static String[] color = {"黒","白"};
	protected int plNo;
	private String name;
	private int point;
	Boad boad;
	//コンストラクタ
	
	Player(Boad boad){
		this("プレイヤー" + (counter+1) , boad);
	}
	Player(String name , Boad boad){
		this.name = name;
		this.boad = boad;
		this.plNo = counter+1;
		counter++;
	}
	
	//メソッド
	
	abstract void play();//手番について考えプレイする
	
	//Player同志のポイントを比較し勝者を表示する
	public void gameEnd(Player pl2){
		Player winner = this;
		System.out.println("\t" + name + ": "+ point +" 対 "+ pl2.getName() + ": " + pl2.getPoint());
		if(point == pl2.getPoint()){
			System.out.println("引き分けです");
		}else{
			if(point < pl2.getPoint()) winner = pl2;
			System.out.println(winner.getName() + "の勝利です！！");
		}
		
	}
	
	//アクセサ
	
	public  String getName(){
		return name;
	}
	public String getColor(){
		return color[(plNo-1)%2];
	}
	public void setPoint(int point){
		this.point = point;
	}
	public int getPoint(){
		return point;
	}
	public int getPlNo(){
		return plNo;
	}
	static public int getPassCount(){
		return passCount;
	}
	protected void resetPass(){
		passCount = 0;
	}
	protected void addPassCount(){
		passCount++;
	}

}
