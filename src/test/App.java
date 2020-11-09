package test;

import java.util.Scanner;

import test.container.Container;
import test.controller.ArticleController;
import test.controller.MemberController;
import test.service.ArticleService;
import test.service.MemberService;

public class App {

	private MemberController memberController;
	private ArticleController articleController;

	public App() {
		memberController = Container.memberController;
		articleController = Container.articleController;

		makeTestData();
	}

	private void makeTestData() {
		// 회원 2명 생성
		MemberService memberService = Container.memberService;
		int firstMemberId = memberService.join("aaa", "aaa", "aaa");
		int secondMemberId = memberService.join("bbb", "bbb", "bbb");

		// 공지사항 게시판 생성
		ArticleService articleService = Container.articleService;
		int noticeBoardId = articleService.makeBoard("공지사항"); 
		
		// 공지사항 글 10개 생성
		int boardId = noticeBoardId;
		
		for (int i = 1; i <= 5; i++) {
			articleService.write(boardId ,1, "제목" + i, "내용" + i);
		}
		for (int i = 6; i <= 10; i++) {
			articleService.write(boardId, 2, "제목" + i, "내용" + i);
		}
	}

	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.startsWith("member ")) {
				memberController.doCommand(cmd);
			}
			if (cmd.startsWith("article ")) {
				articleController.doCommand(cmd);
			}
			// 프로그램종료
			if (cmd.equals("exit")) {
				System.out.println("프로그램종료");
				break;
			}
		}

		sc.close();
	}

}
