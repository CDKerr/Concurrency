import java.util.concurrent.Phaser;


public class Main {

	public static void main(String[] args) {
		
		Phaser phaser = new Phaser(3);
		
		FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
		
		FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
		
		FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);
		
		Thread systemThread = new Thread(system, "System");
		systemThread.start();
		
		Thread appThread = new Thread(apps, "Apps");
		appThread.start();
		
		Thread documentThread = new Thread (documents, "Documents");
		documentThread.start();
		
		try {
			systemThread.join();
			appThread.join();
			documentThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Terminated: " + phaser.isTerminated());

	}

}
