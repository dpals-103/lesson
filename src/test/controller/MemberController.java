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

	public void run(Scanner sc, String command) {

		// 회원가입
		if (command.equals("member join")) {
			System.out.println("==회원가입==");

			String loginId = "";
			String loginPw = "";
			String name = "";

			int loginIdCount = 0;
			int loginIdMaxCount = 3;

			boolean isVaildLoginId = false;

			// 아이디 설정
			while (true) {

				if (loginIdCount >= loginIdMaxCount) {
					System.out.println("회원가입에 실패하였습니다");
					break;
				}

				System.out.printf("아이디 설정 : ");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					loginIdCount++;
					continue;
				} else if (memberService.isAvailableLoginId(loginId) == false) {
					loginIdCount++;
					System.out.printf("%s(은) 사용중인 아이디 입니다\n", loginId);
					continue;
				}
				isVaildLoginId = true;
				break;
			}
			if (isVaildLoginId == false) {
				return;
			}

			// 비밀번호 설정
			
			int loginPwCount = 0;
			int loginPwMaxCount = 3;

			while (true) {

				if(loginPwCount >= loginPwMaxCount) {
					System.out.println("회원가입에 실패하였습니다");
					return;
				}
				
				System.out.printf("비밀번호 설정 : ");
				loginPw = sc.nextLine().trim();

				if (loginPw.length() == 0) {
					loginPwCount++;
					continue;
				}
				break;
			}

			// 사용자 이름 등록하기

			int nameCount = 0;
			int nameMaxCount = 3;

			while (true) {

				if (nameCount >= nameMaxCount) {
					System.out.println("회원가입에 실패하였습니다");
					return;
				}

				System.out.printf("사용자 이름 : ");
				name = sc.nextLine().trim();

				if (name.length() == 0) {
					nameCount++;
					continue;
				}
	
				break;
			}
			

			int id = Container.memberService.join(loginId, loginPw, name);
			System.out.printf("축하합니다. %d번 회원으로 가입되었습니다\n", id);

		}

		// 로그인

		if (command.equals("member login")) {
			System.out.println("==로그인==");

			String loginId = "";
			String loginPw = "";
			String name = "";

			int loginIdCount = 0;
			int loginIdMaxCount = 3;

			boolean isVaildLoginId = false;

			Member member = null;

			// 아이디 입력
			while (true) {

				if (Container.session.isLogined()) {
					System.out.println("이미 로그인 되어있습니다");
				}

				if (loginIdCount >= loginIdMaxCount) {
					System.out.println("로그인에 실패하였습니다");
					loginIdCount++;
					break;
				}

				System.out.printf("아이디를 입력하세요 : ");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					loginIdCount++;
					continue;
				}

				member = memberService.getMemberByLoginId(loginId);

				if (member == null) {
					System.out.println("등록되지 않은 아이디 입니다");
					loginIdCount++;
					continue;
				}

				isVaildLoginId = true;
				break;
			}
			if (isVaildLoginId == false) {
				return;
			}

			// 비밀번호 설정

			int loginPwCount = 0;
			int loginPwMaxCount = 3;

			while (true) {
				if (loginPwCount >= loginPwMaxCount) {
					System.out.println("로그인에 실패하였습니다");
					return;
				}

				System.out.printf("비밀번호를 입력하세요 : ");
				loginPw = sc.nextLine().trim();

				if (loginPw.length() == 0) {
					loginPwCount++;
					continue;
				}
				if (member.loginPw.equals(loginPw) == false) {
					System.out.println("비밀번호가 일치하지 않습니다");
					loginPwCount++;
					continue;
				}
				break;
			}


			Container.session.loginedMemberId = member.id;
			System.out.printf("%s님 환영합니다. 로그인 되었습니다\n", member.name);
		}
		
		
		//회원현재상태
		if(command.equals("member whoami")) {
			if(Container.session.isLogout()) {
				System.out.println("로그인해주세요");
				return;
			}
			
			int loginedMemberId = Container.session.loginedMemberId; 
			Member loginedMember = memberService.getMemberById(loginedMemberId); 
					
			System.out.println("==유저 정보==");
			System.out.printf("회원번호 : %s\n",loginedMember.id);
			System.out.printf("사용자 이름 : %s\n", loginedMember.name);
			
		}

	}
}
