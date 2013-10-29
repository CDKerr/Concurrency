import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;


public class Main {
	
	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state){
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n" , thread.getState());
		pw.printf("Main : **************************************\n");
	}
	public static void main(String[] args) {
		
		Thread threads[] = new Thread[10];
		Thread.State status[] = new Thread.State[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if ((i%2) == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread "+i);
		}
		
		try {
			FileWriter file = new FileWriter("C:\\Users\\Christopher.Kerr\\Documents\\data\\log.txt"); 
			PrintWriter pw = new PrintWriter(file);
			for (int j = 0; j < 10; j++) {
				pw.println("Main : Status of Thread " + j +" : " + threads[j].getState());
				status[j]=threads[j].getState();
			}
			
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}
			
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState()!=status[i]) {
						writeThreadInfo(pw, threads[i], status[i]);
						status[i]=threads[i].getState();
					}
				}
				finish = true;
				for (int i = 0; i < 10; i++) {
					finish = finish &&(threads[i].getState()== State.TERMINATED);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
