import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

	public static void main(String[] args) {
		
		String username = "test"; String password = "test";
		
		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("Database");
		
		taskValidator ldapTask = new taskValidator(ldapValidator, username, password);
		taskValidator dbTask = new taskValidator(dbValidator, username, password);

		List<taskValidator> taskList = new ArrayList<taskValidator>();
		taskList.add(ldapTask);
		taskList.add(dbTask);
		
		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		String result;
		
		try {
			result = executor.invokeAny(taskList);
			System.out.println("Main: Result: " + result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
		System.out.println("Main: End of the Execution");
	}

}
