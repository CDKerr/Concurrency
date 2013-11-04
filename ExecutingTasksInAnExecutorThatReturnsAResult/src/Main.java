import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		
		List<java.util.concurrent.Future<Integer>> resultList = new ArrayList<java.util.concurrent.Future<Integer>>();

		Random random = new Random();
			
		for (int i = 0; i < 10; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			java.util.concurrent.Future<Integer> future = executor.submit(calculator);
			resultList.add(future);
		}
		
		do {
			System.out.println("Main: Number of Completed Tasks: " + executor.getCompletedTaskCount());
			
			for (int j = 0; j < resultList.size(); j++) {
				java.util.concurrent.Future<Integer> result = resultList.get(j);
				System.out.println("Main: Task " + j + " : " + result.isDone());
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount()<resultList.size());
		
		System.out.println("Main: Results");
		
		for (int i = 0; i < resultList.size(); i++) {
			java.util.concurrent.Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				number = result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			System.out.println("Main: Task " + i + " : " + number);
		}
			executor.shutdown();
	}

}
