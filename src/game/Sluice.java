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

		int revenue=0;
		if(!isBroken()) {
			revenue=getRndInt(0,501);
			reduceDurability(getRndInt(20,51));
		}
		printToolRevenue(revenue);
		return revenue;
	}
	public void repair() {
		restoreDurability();
	}

}
