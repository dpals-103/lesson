package test.controller;

import java.util.Scanner;

import test.container.Container;
import test.dto.Member;
import test.service.MemberService;

public class MemberController {

	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.equals("member join")) {
			join(cmd);
		}
		if (cmd.equals("member login")) {
			login(cmd);
		}
		if (cmd.equals("member whoami")) {
			whoami(cmd);
		}
		if (cmd.equals("member logout")) {
			logout(cmd);
		}

	}

	//로그아웃 
	private void logout(String cmd) {
		if(Container.session.isLogined() == false) {
			System.out.println("로그인 되어있지 않습니다");
			return;
		}
		
		System.out.println("로그아웃 되었습니다");
		Container.session.logout(); 
	}

	
	//회원 현재 상태 
	private void whoami(String cmd) {
		if(Container.session.isLogined() == false) {
			System.out.println("로그아웃 상태입니다. 로그인해주세요");
			return;
		}
		
		System.out.println("==유저정보==");
		
		int isLoginedMembeId= Container.session.isLogindeMemberId;
		
		Member isLoginedMember = memberService.getMemberById(isLoginedMembeId);
		
		System.out.printf("로그인 아이디 : %s\n",isLoginedMember.loginId);
		System.out.printf("사용자 이름 : %s\n",isLoginedMember.name);
	}

	// 로그인

	private void login(String cmd) {

		Scanner sc = Container.scanner;

		System.out.println("==로그인==");

		String loginId;
		String loginPw;

		int loginIdCount = 0;
		int loginIdMaxCount = 3;

		Member member = null;

		// 아이디 입력
		while (true) {

			if (Container.session.isLogined()) {
				System.out.println("이미 로그인 되어있습니다");
				return;
			}

			if (loginIdCount >= loginIdMaxCount) {
				System.out.println("로그인에 실패하였습니다");
				return;
			}

			System.out.printf("아이디를 입력하세요 : ");
			loginId = sc.nextLine();

			if (loginId.length() == 0) {
				loginIdCount++;
				continue;
			}

			member = memberService.isVaildLoginId(loginId);
			if (member == null) {
				System.out.printf("%s(은)는 등록되지 않은 아이디입니다\n", loginId);
				loginIdCount++;
				continue;
			}
			break;
		}

		
		int loginPwCount = 0;
		int loginPwMaxCount = 3;

		
		// 비밀번호 입력
		while (true) {
			
			if (loginPwCount >= loginPwMaxCount) {
				System.out.println("로그인에 실패하였습니다");
				return;
			}

			System.out.printf("비밀번호를 입력하세요 : ");
			loginPw = sc.nextLine();

			if (loginPw.length() == 0) {
				loginPwCount++;
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				System.out.printf("비밀번호가 일치하지 않습니다\n");
				loginIdCount++;
				continue;
			}
			break;
		}
		
		System.out.printf("%s님 환영합니다. 로그인되었습니다\n", loginId);
		Container.session.login(member.id);

	}

	private void join(String cmd) {

		Scanner sc = Container.scanner;

		System.out.println("==회원가입==");

		String loginId;
		String loginPw;
		String name;

		int loginIdCount = 0;
		int loginIdMaxCount = 3;

		// 아이디 설정
		while (true) {

			if (loginIdCount >= loginIdMaxCount) {
				System.out.println("회원가입에 실패하였습니다");
				return;
			}

			System.out.printf("아이디 설정 : ");
			loginId = sc.nextLine();

			if (loginId.length() == 0) {
				loginIdCount++;
				continue;
			}
			if (memberService.isAvailableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다\n", loginId);
				loginIdCount++;
				continue;
			}
			break;
		}

		int loginPwCount = 0;
		int loginPwMaxCount = 3;

		// 비밀번호 설정
		while (true) {

			if (loginPwCount >= loginPwMaxCount) {
				System.out.println("회원가입에 실패하였습니다");
				return;
			}

			System.out.printf("비밀번호 설정 : ");
			loginPw = sc.nextLine();

			if (loginPw.length() == 0) {
				loginPwCount++;
				continue;
			}

			break;
		}

		int loginNameCount = 0;
		int loginNameMaxCount = 3;

		// 사용자이름 설정
		while (true) {

			if (loginNameCount >= loginNameMaxCount) {
				System.out.println("회원가입에 실패하였습니다");
				return;
			}

			System.out.printf("사용자 이름 : ");
			name = sc.nextLine();

			if (name.length() == 0) {
				loginNameCount++;
				continue;
			}

			break;
		}

		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("축하합니다. %d번 회원으로 가입되었습니다.\n", id);

	}

}
