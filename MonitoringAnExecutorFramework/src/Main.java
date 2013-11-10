import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) throws Exception{
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
			Task task = new Task(random.nextInt(10000));
			executor.submit(task);
		}
		
		for (int i = 0; i < 5; i++) {
			showLog(executor);
			TimeUnit.SECONDS.sleep(1);
		}
		
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		System.out.println("Main: End of the program.");

	}

	private static void showLog(ThreadPoolExecutor executor) {
		
		System.out.println("**********************");
		System.out.println("Main: Executor Log");
		System.out.println("Main: Executor: Core Pool Size: " + executor.getCorePoolSize());
		System.out.println("Main: Executor: Pool Size: " + executor.getPoolSize());
		System.out.println("Main: Executor: Active Count: " + executor.getActiveCount());
		System.out.println("Main: Executor: Task Count: " + executor.getTaskCount());
		System.out.println("Main: Executor: Completed Task Count: " + executor.getCompletedTaskCount());
		System.out.println("Main: Executor: Shutdown: " + executor.isShutdown());
		System.out.println("Main: Executor: Terminating: " + executor.isTerminating());
		System.out.println("Main: Executor: Terminated: " + executor.isTerminated());
		System.out.println("**********************");
	}

}
