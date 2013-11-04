import java.util.concurrent.Callable;


public class taskValidator implements Callable<String> {
	
	private UserValidator validator;
	private String user, password;

	public taskValidator(UserValidator validator, String user, String password) {
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	@Override
	public String call() throws Exception {
		if (!validator.validate(user, password)) {
			System.out.println(validator.getName() + ": The user has not been found");
			throw new Exception("Error validating user");
		}
		System.out.println(validator.getName() + ": The user has been found");
		return validator.getName();
	}

}
