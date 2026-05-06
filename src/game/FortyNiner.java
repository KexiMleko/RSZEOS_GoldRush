package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
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
		tools.add(new Sluice());
		tools.add(new Pan());
	}
	public FortyNiner(int _money, int _endurance, ArrayList<Tool> _tools) {
		money=_money;
		endurance=_endurance;
		tools=_tools;
		tools.add(new Pan());
		rnd=new Random();
	}
public List<String> serialize(){
    List<String> lines = new ArrayList<>();
    lines.add("49er endurance: " + endurance + "%");
    lines.add("49er money: $" + money);
    for (Tool t : tools) {
    	if(!(t instanceof Pan)) { 	
    		lines.add(t.serialize());
    	}
    }
    return lines;
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
		Iterator<Tool> it = tools.iterator();
		while (it.hasNext()) {
		    Tool tool = it.next();
		    money += tool.useTool();
		    if (tool.isBroken() && tool instanceof Cradle) {
		        it.remove();
		    }
		}

	}
	public void buyFood() {
		int cost = rnd.nextInt(30,50);	
		money-=cost;
		if(money<0)money=0;
		System.out.println("Money spent on food: $"+cost);
	}
	public void loseEndurance() {
		int enduranceLoss=rnd.nextInt(10,25);
		endurance-=enduranceLoss;
		if(endurance<0)endurance=0;
		System.out.println("Endurance reduced by: "+enduranceLoss+"%");
	}

	public boolean buyCradles(int numberToBuy) {
		int cost=numberToBuy*30;
		if(money<cost)return false;
		for(int i=0;i<numberToBuy;i++) {
			Cradle cradle=new Cradle(100);
			tools.add(cradle);
		}
		money-=cost;
		System.out.println("Money spent on cradles: "+cost);
		return true;
	}
	public boolean itIsSundayAgain(SundayActivity activity) {
		switch(activity) {
			case NOTHING:
				return true;
			case SALOON:
				
				goToSaloon();
				return true;
			case FIX_SLUICE:	
				fixSluice();
				return true;
		}
		return false;
	}
	public boolean hasEnoughMoneyForCradles(int cradleCount) {
		boolean canBuy	= money>=(cradleCount*30);
		if(!canBuy)System.out.println("Not enough money for "+cradleCount+" cradles, current money balance: "+money);
		return canBuy;
	}
	public boolean hasEnoughMoneyFor(SundayActivity activity) {
		switch(activity) {
		case SALOON: {
			if(money<50) {					
				System.out.println("Not enough money to go to Saloon, current balance: "+money);
				return false;
			}	
			break;
		}
		case FIX_SLUICE:{
			if(money<100) {			
				System.out.println("Not enough money to fix sluice, current balance: "+money);
				return false;
			}	
			break;
		}
		default:
			break;
		}
		return true;
	}
	private void fixSluice() {
		for(Tool tool:tools) {
			if(tool instanceof Sluice) {
				Sluice sluice= (Sluice)tool;
				sluice.repair();
				money-=100;
				System.out.println("Spent 100$ to fix sluice");
			}
		}
	}
	private void goToSaloon() {
		int budget = money<200?money:200;
		int enduranceGain=rnd.nextInt(5,50);
		int cost=rnd.nextInt(50,budget);
		endurance+=enduranceGain;
		money-=cost;
		System.out.println("Saloon restored "+enduranceGain+"%"+" and cost $"+cost);
	}
	public void printStats() {
		   System.out.println("---------------------------------------------");
		    System.out.printf("Money:     $%d%n", money);
		    System.out.printf("Endurance: %d%%%n", endurance);
		    System.out.println("---------------------------------------------");
	}
	public int getSluiceDurability() {
		for(Tool tool: tools) {
			if(tool instanceof Sluice) {
				return tool.getDurability();
			}
		}
		throw new NoSuchElementException("No sluice found");
	}
}
