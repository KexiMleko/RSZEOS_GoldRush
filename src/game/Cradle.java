package game;

public class Cradle extends Tool{

	public Cradle(int _durability) {
		super(_durability);
	}

	@Override
	public int useTool() {
		if(getRndDouble(0,1)<0.2) {
			breakTool();
		}
		return getRndInt(0,30);
	}
}
