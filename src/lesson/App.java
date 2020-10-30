package lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	private List<Article> articles;
	private List<Member> members;
	private int lastArticleId;
	private int lastMemberId;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
		lastArticleId = 0;
		lastMemberId = 0;
	}

	// 회원관련기능 시작

	private int join(String loginId, String loginPw) {

		Member member = new Member();

		member.id = lastMemberId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;

		members.add(member);

		lastMemberId = member.id;

		return member.id;
	}

	private boolean isPossibleLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	// 회원관련기능 끝

	public void run() {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine();

			// 회원가입
			if (command.equals("member join")) {
				System.out.println("==회원가입==");

				String loginId = "";
				String loginPw;

				int loginIdCount = 0;
				int loginIdMaxCount = 3;
				boolean loginIdValid = false;

				// 아이디 만들기
				while (true) {

					if (loginIdCount >= loginIdMaxCount) {
						System.out.println("회원가입이 취소되었습니다");
						break;
					}

					System.out.printf("아이디 : ");
					loginId = sc.nextLine().trim();

					if (loginId.length() == 0) {
						loginIdCount++;
						continue;
					} else if (isPossibleLoginId(loginId) == false) {
						loginIdMaxCount++;
						System.out.printf("%s는 이미 사용중인 아이디 입니다\n", loginId);
						continue;
					}

					loginIdValid = true;
					break;
				}

				if (loginIdValid == false) {
					return;
				}

				// 비밀번호 만들기
				while (true) {
					System.out.printf("비밀번호 : ");
					loginPw = sc.nextLine().trim();

					if (loginPw.length() == 0) {
						continue;
					}
					
					break;
				}

				int id = join(loginId, loginPw);

				System.out.printf("%d번 회원으로 가입되셨습니다\n", id);

			}
			// 게시물등록
			// 게시물리스팅
			// 게시물상세보기
			// 게시물수정
			// 게시물삭제
			// 게시물검색

			// 프로그램종료
			if (command.equals("exit")) {
				System.out.println("==프로그램종료==");
				break;
			}

		}

		sc.close();

	}

}
