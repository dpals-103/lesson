package test.service;

import java.util.ArrayList;
import java.util.List;

import test.dao.MemberDao;
import test.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		memberDao = new MemberDao();
	}

	public boolean isAvailableLoginId(String loginId) {
		Member member = memberDao.getMemberByLoginId(loginId);

		if (member != null) {
			return false;
		}
		return true;
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);

	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}
}
