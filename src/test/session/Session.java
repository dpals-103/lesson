package test.session;

public class Session {
	
	public int isLogindeMemberId;
	public int selectedBoardId;
	
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

	public void selectedBoardId(int boardId) {
		selectedBoardId = boardId; 
	}

}