import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

		MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(12);
		
		Task task = new Task();
		System.out.println("Main: " + new Date());

		executor.schedule(task, 1, TimeUnit.SECONDS);
		
		TimeUnit.SECONDS.sleep(3);
		
		task = new Task();
		System.out.println("Main: " + new Date());
		
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
		
		TimeUnit.SECONDS.sleep(10);
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		System.out.println("Main: End of the program");
	}

}
