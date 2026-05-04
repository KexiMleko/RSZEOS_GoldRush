package game;

import java.util.Random;

public abstract class Tool {
private int durability;
private Random rnd;
public Tool(int _durability) {
	durability=_durability;
	rnd=new Random();
}
public int getDurability() {
	return durability;	
}
protected void reduceDurability(int value) {
	if(value<0) throw new IllegalArgumentException("Durability reduction value cant be negative");
int	newDurability=durability-value;
	durability=newDurability>0?newDurability:0;
}
protected void breakTool() {
	durability=0;
}
protected void restoreDurability() {
	durability = 100;
}
protected double getRndDouble(double min,double max) {
	return rnd.nextDouble(min,max);
}
protected int getRndInt(int min, int max) {	
	return rnd.nextInt(min,max);
}
abstract public int useTool(); 
}
