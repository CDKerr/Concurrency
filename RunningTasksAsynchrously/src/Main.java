import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) {
		
		ForkJoinPool pool = new ForkJoinPool();
		
		FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
		FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
		FolderProcessor documents = new FolderProcessor("C:\\Documents and Settings", "log");
		
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);

		do {
			System.out.println("****************************************");
			System.out.println("Main: Parallelism: " + pool.getParallelism());
			System.out.println("Main: Active Threads: " + pool.getActiveThreadCount());
			System.out.println("Main: Task Count: " + pool.getQueuedTaskCount());
			System.out.println("Main: Steal Count: " + pool.getStealCount());
			System.out.println("****************************************");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));
		
		pool.shutdown();
		
		List<String> results;
		
		results = system.join();
		System.out.println("System: " + results.size() + " files found.");
		
		results = apps.join();
		System.out.println("Apps: " + results.size() + " files found.");
		
		results = documents.join();
		System.out.println("Documents: " + results.size() + " files found.");
	}

}
