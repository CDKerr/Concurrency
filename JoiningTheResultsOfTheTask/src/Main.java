import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) {
		
		Document mock = new Document();
		String[][] document = mock.generateDocument(100, 1000, "the");
		
		DocumentTask task = new DocumentTask(document, 0, 100, "the");
		
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		
		do {
			System.out.println("************************************");
			System.out.println("Main: Parallelism: " + pool.getParallelism());
			System.out.println("Main: Active Threads: " + pool.getActiveThreadCount());
			System.out.println("Main: Task Count: " + pool.getQueuedTaskCount());
			System.out.println("Main: Steal Count: " + pool.getStealCount());
			System.out.println("************************************");
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		
		pool.shutdown();
		
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Main: The word appears " + task.get() + " in the document.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

}
