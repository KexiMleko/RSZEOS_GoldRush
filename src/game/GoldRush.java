package game;

import java.io.File;
import java.util.Scanner;

public class GoldRush {
	private FortyNiner fortyNiner;
	private File savedGame;
	private Scanner scanner;
	private int weekCount;
	public GoldRush() {
		scanner=new Scanner(System.in);
	}
	public void loadGame() {
		
	}
	public void saveGame() {
		
	}
	public void survive() {
			if(fortyNiner==null) {
				fortyNiner=new FortyNiner();
			}
		while(weekCount<20) {		

			System.out.printf("Week No. %d has started\n",weekCount+1);
			fortyNiner.useTools();
			fortyNiner.buyFood();
			fortyNiner.loseEndurance();

			System.out.println("Do you wish to stop playing? Type y for yes");
			String continueInput=scanner.nextLine();
			if(continueInput=="y") {		
				saveGame();
				System.exit(0);
			}

			System.out.println("Activity input number must be 0 , 1 or 2");
			SundayActivity activity= getSundayActivity();
			fortyNiner.itIsSundayAgain(activity);

			System.out.println("How many cradles do you want to buy");

			int cradlesToBuy= scanner.nextInt();
			fortyNiner.buyCradles(cradlesToBuy);

			weekCount++;
		}
		System.out.println("20 Weeks have passed, the game has ended");
		System.out.println("Money");
	}
	private SundayActivity getSundayActivity() {
		
		boolean isValid=false;
			int userInput = 0;
		while(!isValid) {
			System.out.print("Choose sunday activity\n"
						+ "0 - Nothing\n"
						+ "1 - Fix sluice\n"
						+ "2 - Go to saloon\n");
			userInput= scanner.nextInt();
			if(userInput >=0 && userInput<=2) {
				isValid=true;
			}else {
				System.out.println("Activity input number must be 0 , 1 or 2");
			}
		}
		 return switch (userInput) {
	        case 0 -> SundayActivity.NOTHING;
	        case 1 -> SundayActivity.FIX_SLUICE;
	        case 2 -> SundayActivity.SALOON;
	        default -> throw new IllegalArgumentException("Invalid input: " + userInput);
	    };
	}
}
