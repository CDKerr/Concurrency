import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) throws Exception{
		
		Phaser phaser = new Phaser(3);
		
		for (int i = 0; i < 3; i++) {
			Task task = new Task(i+1, phaser);
			Thread thread = new Thread(task);
			thread.start();
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("******************");
			System.out.println("Main: Phaser log");
			System.out.println("Main: Phaser: Phase: " + phaser.getPhase());
			System.out.println("Main: Phaser: Registered Parties: " + phaser.getRegisteredParties());
			System.out.println("Main: Phaser: Arrived Parties: " + phaser.getArrivedParties());
			System.out.println("Main: Phaser: UnArrived Parties: " + phaser.getUnarrivedParties());
			System.out.println("******************");
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
