package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Member;

public class MemberDao {
	
	private List<Member> members; 
	private int lastMemberId;
	
	public MemberDao() {
		members = new ArrayList<>(); 
		lastMemberId = 0;
		
		for(int i = 0; i < 3; i++ ) {
			join("유저"+i, "유저"+i, "이름"+i);
		}
	}

	public boolean isAvailableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
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

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberById(int id) {
		for (Member member : members) {
			if (member.id == id) {
				return member;
			}
		}
		return null;
	}
}
