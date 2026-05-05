package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;

public class GoldRush {
	private FortyNiner fortyNiner;
	private File savedGame;
	private Scanner scanner;
	private static final String SAVE_FILE = "savegame.txt";
	private int weekCount;
	public GoldRush() {
		scanner=new Scanner(System.in);
		 savedGame = new File(SAVE_FILE);
	}
	public void loadGame() {
		
	}
	public void saveGame() {
		List<String> lines = new ArrayList<>();
		lines.add("Week no. " + weekCount);
		lines.addAll(fortyNiner.serialize());
	    try {
			Files.write(savedGame.toPath(), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void survive() {
			if(fortyNiner==null) {
				fortyNiner=new FortyNiner();
			}
		while(weekCount<20) {		

			weekCount++;

			System.out.printf("Week No. %d has started\n",weekCount);

			SundayActivity activity= getSundayActivity();
			fortyNiner.itIsSundayAgain(activity);
			System.out.println("How many cradles do you want to buy");

			int cradlesToBuy = scanner.nextInt();
			scanner.nextLine();
			fortyNiner.buyCradles(cradlesToBuy);

			fortyNiner.useTools();
			fortyNiner.buyFood();
			fortyNiner.loseEndurance();

			System.out.println("Do you wish to stop playing? Type y for yes");
			String continueInput=scanner.nextLine();
			fortyNiner.printStats();
			if(continueInput.equalsIgnoreCase("y")) {		
				saveGame();
				return;
			}

		}
		System.out.println("20 Weeks have passed, the game has ended");
		System.out.println("Final money: $" + fortyNiner.getMoney());
	}
	private SundayActivity getSundayActivity() {
		
		boolean isValid=false;
			int userInput = 0;
			SundayActivity activity=SundayActivity.NOTHING;
		while(!isValid) {
			System.out.print("Choose sunday activity\n"
						+ "0 - Nothing\n"
						+ "1 - Fix sluice - durability: "+fortyNiner.getSluiceDurability()+"%\n"
						+ "2 - Go to saloon\n");
			userInput = scanner.nextInt();
			scanner.nextLine();
			if(userInput >=0 && userInput<=2) {

				 activity= switch (userInput) {
					case 0 -> SundayActivity.NOTHING;
					case 1 -> SundayActivity.FIX_SLUICE;
					case 2 -> SundayActivity.SALOON;
					default -> throw new IllegalArgumentException("Invalid input: " + userInput);
				};
				if(fortyNiner.hasEnoughMoneyFor(activity)) {		
					isValid=true;
				}
			}else {
				System.out.println("Activity input number must be 0 , 1 or 2");
			}
		}
		return activity;
	}

}
