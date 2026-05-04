package game;

public class Sluice extends Tool{

	public Sluice(int _durability) {
		super(_durability);
	}

	@Override
	public int useTool() {
		reduceDurability(getRndInt(20,50));
		return getRndInt(0,500);
	}
	public void repair() {
		restoreDurability();
	}

}
