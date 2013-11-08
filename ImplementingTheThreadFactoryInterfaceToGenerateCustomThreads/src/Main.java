
public class Main {

	public static void main(String[] args) throws Exception {
		
		MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
		
		MyTask task = new MyTask();
		
		Thread thread = myThreadFactory.newThread(task);
		
		thread.start();
		thread.join();
		
		System.out.println("Main: Thread Information");
		System.out.println(thread);
		System.out.println("Main: End of the example");

	}

}
