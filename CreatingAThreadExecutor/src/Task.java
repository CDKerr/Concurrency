import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Task implements Runnable {

	private Date initDate;
	private String name;

	
	public Task(String name) {
		initDate = new Date();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": Task " + name + ": Created on: " + initDate);
		System.out.println(Thread.currentThread().getName() + ": Task " + name + ": Started on: " + new Date());
		
		try {
			Long duration = (long)(Math.random()*10);
			System.out.println(Thread.currentThread().getName() + ": Task " + name + ": Doing a task during " + duration + " seconds");
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + ": Task " + name + ": Finished on: " + new Date());
	}

}
