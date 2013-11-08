import java.util.concurrent.ForkJoinPool;


public class Main {

	public static void main(String[] args) throws Exception{
		
		int array[] = new int[10000];
		
		ForkJoinPool pool = new ForkJoinPool();
		
		Task task = new Task("Task", array, 0, array.length);
		
		pool.invoke(task);
		
		pool.shutdown();
		
		System.out.println("Main: End of the program");

	}

}
