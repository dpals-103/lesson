package test.session;

public class Session {
	
	public int isLogindeMemberId;
	
	public Session() {
		isLogindeMemberId = 0; 
	}

	public boolean isLogined() {
		return isLogindeMemberId != 0; 
	}

	public void login(int id) {
		isLogindeMemberId = id; 
	}

	public void logout() {
		isLogindeMemberId = 0;
	}

}
