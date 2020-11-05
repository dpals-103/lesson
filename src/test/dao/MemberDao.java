package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Article;
import test.dto.Member;

public class MemberDao {
	private List<Member> members; 
	private int lastMemberId; 
	
	public MemberDao() {
		members = new ArrayList<>();
		lastMemberId = 0;
		
		makeTestData(); 
	}

	private void makeTestData() {
		join("aaa","aaa","aaa");
		join("bbb","bbb","bbb");
	}

	public boolean isAvailableLoginId(String loginId) {
	
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true; 
	}

	public int join(String loginId, String loginPw, String name) {
		
		Member member = new Member(); 
		
		member.id = lastMemberId + 1; 
		member.loginId = loginId; 
		member.loginPw = loginPw;
		member.name = name; 
		
		members.add(member);
		
		lastMemberId = member.id; 
		
		return member.id;
	}

	public Member isVaildLoginId(String loginId) {
		for(Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null; 
	}

	public Member getMemberById(int memberId) {
		for(Member member : members) {
			if(member.id == memberId) {
				return member;
			}
		}
		return null;
	}


}
