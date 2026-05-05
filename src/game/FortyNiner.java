package game;

import java.util.ArrayList;
import java.util.Random;

public class FortyNiner {
private int endurance;
private int money;
private ArrayList<Tool> tools;
private Random rnd;
	public FortyNiner() {
		money=100;
		endurance=100;
		rnd=new Random();
		tools=new ArrayList<Tool>();
	}
	public FortyNiner(int _money, int _endurance, ArrayList<Tool> _tools) {
		money=_money;
		endurance=_endurance;
		tools=_tools;
		rnd=new Random();
	}
	public int getEndurance() {     return endurance; }  
	public void setEndurance(int endurance) {
		this.endurance = endurance>0?endurance:0; 
		}  
	public int getMoney() {     return money; }  
	public void setMoney(int money) {     this.money = money; }
	public void useTools() {
		if(endurance==0) {
			endurance=100;
			return;
		}
		for(Tool tool : tools) {
			int revenue=tool.useTool();	
			money+=revenue;
		}
	}
	public void buyFood() {
		int cost = rnd.nextInt(30,50);	
		money-=cost;
	}
	public void loseEndurance() {
		int enduranceLoss=rnd.nextInt(10,25);
		endurance-=enduranceLoss;
		if(endurance<0)endurance=0;
	}
	public void buyCradles(int numberToBuy) {
		int cost=numberToBuy*30;
		for(int i=0;i<numberToBuy;i++) {
			Cradle cradle=new Cradle(100);
			tools.add(cradle);
		}
		money-=cost;
	}
	public void itIsSundayAgain(SundayActivity activity) {
		switch(activity) {
			case NOTHING:
				break;
			case SALOON:
				goToSaloon();
				break;
			case FIX_SLUICE:
				fixSluice();
				break;
		}
	}
	private void fixSluice() {
		for(Tool tool:tools) {
			if(tool instanceof Sluice) {
				Sluice sluice= (Sluice)tool;
				sluice.repair();
				money-=100;
			}
		}
	}
	private void goToSaloon() {
		int enduranceGain=rnd.nextInt(5,50);
		int cost=rnd.nextInt(50,200);
		endurance+=enduranceGain;
		money-=cost;
	}
}
