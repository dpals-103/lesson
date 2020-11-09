package test.controller;

import java.util.List;
import java.util.Scanner;

import test.container.Container;
import test.dto.Article;
import test.dto.Board;
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
		if (cmd.startsWith("article modify ")) {
			modify(cmd);
		}
		if (cmd.startsWith("article delete ")) {
			remove(cmd);
		}
		if (cmd.startsWith("article makeBoard")) {
			makeBoard(cmd);
		}
		if (cmd.startsWith("article selectedBoard ")) {
			selectedBoard(cmd);
		}
		if (cmd.startsWith("article currentBoard")) {
			currentBoard(cmd);
		}

	}

	// 현재 선택된 게시판 확인하기
	private void currentBoard(String cmd) {
		int boardId = Container.session.selectedBoardId;
		Board board = articleService.getBoardByBoardId(boardId);

		if (boardId == 0) {
			System.out.println("선택된 게시판이 없습니다");
			return;
		}

		System.out.printf("현재 %s게시판이 선택되어 있는 상태입니다\n", board.boardTitle);
	}

	// 게시판 선택하기
	private void selectedBoard(String cmd) {
		int boardId = Integer.parseInt(cmd.split(" ")[2]);
		Board board = articleService.getBoardByBoardId(boardId);

		if (board == null) {
			System.out.println("생성된 게시판이 존재하지 않습니다");
			return;
		}

		System.out.printf("%s 게시판이 선택되었습니다.\n", board.boardTitle);

		Container.session.selectedBoardId(board.boardId);
	}

	// 게시판 생성하기
	private void makeBoard(String cmd) {
		Scanner sc = Container.scanner;

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		String boardTitle;

		System.out.println("==게시판생성==");

		System.out.printf("게시판 이름 : ");
		boardTitle = sc.nextLine();

		int boardId = articleService.makeBoard(boardTitle);

		System.out.printf("%s(%d번) 게시판이 생성되었습니다\n", boardTitle, boardId);

		Container.session.selectedBoardId = boardId;

	}

	// 게시물 삭제하기
	private void remove(String cmd) {
		Scanner sc = Container.scanner;

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		System.out.println("==게시물삭제==");

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);
		Article article = articleService.getArticle(inputedId);

		articleService.remove(inputedId);

		System.out.printf("%d번 게시글이 삭제되었습니다\n", inputedId);
	}

	// 게시물 수정하기
	private void modify(String cmd) {
		Scanner sc = Container.scanner;

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		System.out.println("==게시물수정==");

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);

		String title;
		String body;

		System.out.printf("새 제목: ");
		title = sc.nextLine();
		System.out.printf("새 내용: ");
		body = sc.nextLine();

		articleService.modify(inputedId, title, body);
		System.out.printf("%d번 게시글이 수정되었습니다\n", inputedId);
	}

	// 게시물 리스트
	private void list(String cmd) {
		Scanner sc = Container.scanner;

		System.out.println("==게시물리스트==");

		int boardId = Container.session.selectedBoardId;

		List<Article> articles = articleService.getArticles(boardId);

		if (boardId == 0) {
			System.out.println("선택된 게시판이 없습니다");
			return;
		}

		Board board = articleService.getBoardByBoardId(boardId);
		System.out.printf("==%s 게시판 글 리스트==\n", board.boardTitle);

		System.out.printf("번호 / 작성자 / 제목\n");

		for (Article article : articles ) {
			if (article.boardId == boardId) {
				Member member = memberService.getMemberById(article.memberId);
				System.out.printf(" %d / %s / %s\n", article.id, member.name, article.title);
			}
		}

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

		int boardId = Container.session.selectedBoardId;
		int memberId = Container.session.isLogindeMemberId;

		int id = articleService.write(boardId, memberId, title, body);
		System.out.printf("%d번 게시글이 생성되었습니다\n", id);
	}

}
