import java.util.concurrent.TimeUnit;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		MyPriorityTansferQueue<Event> buffer = new MyPriorityTansferQueue<Event>();
		
		Producer producer = new Producer(buffer);
		
		Thread producerThreads[] = new Thread[100];
		
		for (int i = 0; i < producerThreads.length; i++) {
			producerThreads[i] = new Thread(producer);
			producerThreads[i].start();
		}
		
		Consumer consumer = new Consumer(buffer);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
		
		Event myEvent = new Event("Core Event", 0);
		buffer.transfer(myEvent);
		System.out.println("Main: My Event has been transfered");
		
		for (int i = 0; i < producerThreads.length; i++) {
			try {
				producerThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
		
		myEvent = new Event("Core Event 2", 0);
		buffer.transfer(myEvent);
		
		consumerThread.join();
		
		System.out.println("Main: End of the program");
	}

}
