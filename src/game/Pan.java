package game;

public class Pan extends Tool {

	public Pan() {
		super(100,"Pan");
	}
	public Pan(int _durability) {
		super(_durability,"Pan");
	}

	@Override
	public int useTool() {
		int revenue=getRndInt(0,61);
		printToolRevenue(revenue);
		return revenue;
	}
	

}
