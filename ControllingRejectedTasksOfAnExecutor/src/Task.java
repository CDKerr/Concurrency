import java.util.concurrent.TimeUnit;


public class Task implements Runnable {
	
	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {

		System.out.println("Task " + name + ": Starting");
		
		try {
			long duration = (long)(Math.random()*10);
			System.out.println(" Task " + name + ": ReportGenerator: Generating a report during " + duration + "seconds");
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Task " + name + ": Ending");
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
