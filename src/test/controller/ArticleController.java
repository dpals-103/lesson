package test.controller;

import java.util.List;
import java.util.Scanner;

import test.container.Container;
import test.dto.Article;
import test.dto.Member;
import test.service.ArticleService;
import test.service.MemberService;

public class ArticleController {

	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.equals("article add")) {
			add(cmd);
		}
		if (cmd.equals("article list")) {
			list(cmd);
		}
	}

	// 게시물 리스트
	private void list(String cmd) {
		System.out.println("==게시물리스트==");
		System.out.println("번호 / 작성자 / 제목");

		List<Article> article = articleService.getArticles();

		Member member = memberService.getMemberById(Container.session.isLogindeMemberId);

		System.out.printf("%d / %s / %s\n", article.id, member.name, article.title);
	}

	// 게시물 등록
	private void add(String cmd) {

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		Scanner sc = Container.scanner;

		System.out.println("==게시물등록==");

		String title;
		String body;

		System.out.printf("제목 : ");
		title = sc.nextLine();
		System.out.printf("내용 : ");
		body = sc.nextLine();

		int id = articleService.write(Container.session.isLogindeMemberId, title, body);
		System.out.printf("%d번 게시글이 생성되었습니다\n", id);

	}

}
