package game;

import java.util.Random;

public abstract class Tool {
private int durability;
private Random rnd;
private String toolName;
public Tool(int _durability, String _toolName) {
	durability=_durability;
	toolName=_toolName;
	rnd=new Random();
}
public String getToolName() {
	return toolName;
}
public int getDurability() {
	return durability;	
}
protected void reduceDurability(int value) {
	if(value<0) throw new IllegalArgumentException("Durability reduction value cant be negative");
	int	newDurability=durability-value;
	durability=Math.max(newDurability, 0);
	System.out.println(toolName + " durability reduced to " + durability);
}
protected void breakTool() {
	durability=0;
	System.out.println(toolName + " has been broken");
}
protected void restoreDurability() {
	durability = 100;
	System.out.println(toolName + " has been restored");
}
protected void printToolRevenue(int revenue) {	
		System.out.println(getToolName()+" made $"+revenue);
}
protected double getRndDouble(double min,double max) {
	return rnd.nextDouble(min,max);
}
protected int getRndInt(int min, int max) {	
	return rnd.nextInt(min,max);
}
public boolean isBroken() {
	return durability==0;
}
public String serialize() {
    return toolName + " durability: " + durability + "%";
}
abstract public int useTool(); 
}
