package test.service;

import java.util.List;

import test.container.Container;

import test.dao.MemberDao;
import test.dto.Article;
import test.dto.Member;

public class MemberService {
	
	private MemberDao memberDao; 

	public MemberService() {
		memberDao = Container.memberDao; 
	}

	public boolean isAvailableLoginId(String loginId) {
		return memberDao.isAvailableLoginId(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member isVaildLoginId(String loginId) {
		return memberDao.isVaildLoginId(loginId);
	}

	public Member getMemberById(int memberId) {
		return memberDao.getMemberById(memberId);
	}

	
}
