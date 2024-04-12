package util.dto;

public class DtoEmail {
	private String managerName;
	private String employeeName;
	private String message;
	private String email;

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DtoEmail(String managerName, String employeeName, String message, String email) {
		super();
		this.managerName = managerName;
		this.employeeName = employeeName;
		this.message = message;
		this.email = email;
	}

	public DtoEmail() {
		super();
	}

	

}
