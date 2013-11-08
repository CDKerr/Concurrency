import java.util.concurrent.TimeUnit;


public class Main {
	
	public static void main(String[] args){
		
		MyLock lock = new MyLock();
		
		for (int i = 0; i < 10; i++) {
			Task task = new Task(lock, "Task - " + i);
			Thread thread = new Thread(task);
			thread.start();
		}
		
		boolean value;
		
		do {
			try {
				value = lock.tryLock(1, TimeUnit.SECONDS);
				if (!value) {
					System.out.println("Main: Trying to get the lock");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				value=false;
			}
		} while (value);
		
		System.out.println("Main: got the lock");
		
		lock.unlock();
		
		System.out.println("Main: End of the program");
	}
	

}
