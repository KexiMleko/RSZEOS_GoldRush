package game;

public class Cradle extends Tool{

	public Cradle() {
		super(100,"Cradle");
	}
	public Cradle(int _durability) {
		super(_durability,"Cradle");
	}

	@Override
	public int useTool() {
		if(getRndDouble(0,1)<0.2) {
			breakTool();
		}
		int revenue=getRndInt(0,30);
		printToolRevenue(revenue);
		return revenue;
	}
}
