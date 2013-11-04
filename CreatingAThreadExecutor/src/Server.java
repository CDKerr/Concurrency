import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Server {

	private ThreadPoolExecutor executor;
	
	public Server(){
		executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		//To get a fixed number of threads in the thread executor use this - 
		//executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
		//This will set the maximum number of threads to 5 but can be set to any number.
	}
	
	public void executeTask(Task task) {
		System.out.println("Server: A new task has arrived");
		executor.execute(task);
		System.out.println("Server: Pool Size: " + executor.getPoolSize());
		System.out.println("Server: Active Count: " + executor.getActiveCount());
		System.out.println("Server: Completed Tasks: " + executor.getCompletedTaskCount());
	}
	
	public void endServer() {
		executor.shutdown();
	}
}
