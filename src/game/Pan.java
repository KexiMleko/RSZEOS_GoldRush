package game;

public class Pan extends Tool {

	public Pan(int _durability) {
		super(_durability);
	}

	@Override
	public int useTool() {
		return	getRndInt(0,60);
	}
	

}
