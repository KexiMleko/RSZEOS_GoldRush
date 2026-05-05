package game;

public class Sluice extends Tool{

	public Sluice() {
		super(100,"Sluice");
	}
	public Sluice(int _durability) {
		super(_durability,"Sluice");
	}

	@Override
	public int useTool() {
		reduceDurability(getRndInt(20,50));
		int revenue=getRndInt(0,500);
		printToolRevenue(revenue);
		return revenue;
	}
	public void repair() {
		restoreDurability();
	}

}
