import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class MyExecutor extends ThreadPoolExecutor {
	
	private ConcurrentHashMap<String, Date> startTimes;
	
	public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		startTimes = new ConcurrentHashMap<String, Date>();
	}

	@Override
	public void shutdown() {
		System.out.printf("My Executor: Going to shutdown.\n");
		System.out.printf("My Executor: Executed Tasks: %d\n", getCompletedTaskCount());
		System.out.printf("My Executor: Running Tasks: %d\n", getActiveCount());
		System.out.printf("My Executor: Pending Tasks: %d\n", getQueue().size());
		super.shutdown();
	}
	
	@Override
	public List<Runnable> shutdownNow() {
		System.out.printf("My Executor: Going to immediately shutdown.\n");
		System.out.printf("My Executor: Executed Tasks: %d\n", getCompletedTaskCount());
		System.out.printf("My Executor: Running Tasks: %d\n", getActiveCount());
		System.out.printf("My Executor: Pending Tasks: %d\n", getQueue().size());
		return super.shutdownNow();
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.printf("My Executor: A task is beginning: %s : %s\n", t.getName(), r.hashCode());
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		java.util.concurrent.Future<?> result = (java.util.concurrent.Future<?>)r;
		try {
			System.out.println("********************************************");
			System.out.printf("My Executor: A task is finishing.\n");
			System.out.printf("My Executor: Result: %s\n", result.get());
			Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
			Date finishDate = new Date ();
			long diff = finishDate.getTime() - startDate.getTime();
			System.out.printf("My Executor: Duration: %d\n", diff);
			System.out.printf("My Executor: Result: %s\n", result.get());
			System.out.println("********************************************");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	

}
