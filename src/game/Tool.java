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
abstract public int useTool(); 
}
