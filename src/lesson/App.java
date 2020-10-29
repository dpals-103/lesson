package lesson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App { // 변수선언 (공유공간, 공통메모..), 실행x

	private List<Article> articles;
	private int lastArticleId;

	// 생성자 메서드 (자동으로 초기화)
	public App() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
		}
	}

	private Article getArticle(int id) {
		int index = getIndexOf(id);

		if (index == -1) {
			return null;
		}
		return articles.get(index);
	}

	private int add(String title, String body) {

		// 게시물 무한대로 저장하기 ( 새 저장소 업체와 계약하기 )
		// 배열은 증가할 수 없다! -> 증가한게 아니라 공간이 큰 새로운 저장소로 옮겨가는 것

		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;

		articles.add(article);
		lastArticleId = article.id;

		return article.id;

	}

	// 인덱스 찾기 함수
	private int getIndexOf(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id) {
				return i;
			}
		}

		return -1;
	}

	// 인덱스 지우기 함수
	private void remove(int id) {

		int index = getIndexOf(id);

		if (index == -1) {
			return;
		}
		articles.remove(index);
	}

	// 게시물 수정하기 함수
	private void modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);
		article.title = title;
		article.body = body;
	}

	public void run() { // 실제 작동구간

		// 명령어 스캐너 기능 만들기
		Scanner scanner = new Scanner(System.in);

		// 루프 시작
		while (true) {
			System.out.printf("명령어 ) ");
			String command = scanner.nextLine();

			// 게시물 삭제
			if (command.startsWith("article delete ")) {

				int inputedId = Integer.parseInt(command.split(" ")[2]);

				System.out.printf("%d번 게시글이 삭제되었습니다\n", inputedId);

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 글이 존재하지 않습니다/n", inputedId);
				}

				remove(inputedId);

			}

			// 게시물등록
			if (command.equals("article add")) {
				System.out.println("==게시물등록==");

				String title;
				String body;

				Date regDate = new Date();
				SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				System.out.printf("제목 : ");
				title = scanner.nextLine();
				System.out.printf("내용 : ");
				body = scanner.nextLine();
				System.out.println(Date.format(regDate));

				int id = add(title, body);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			}

			// 게시물리스트
			if (command.startsWith("article list ")) {

				int page = Integer.parseInt(command.split(" ")[2]);

				if (page <= 1) {
					page = 1;
				}

				System.out.println("==게시물리스트==");

				if (articles.size() == 0) {
					System.out.println("==게시물이 존재하지 않습니다==");
					continue;
				}

				System.out.println("번호/제목");

				int itemsInAPage = 10; // 1페이지당 게시물 갯수
				int startPos = articles.size() - 1; // 시작 인덱스 초기값
				startPos -= (page - 1) * itemsInAPage; // 각 페이지 별 시작점
				int endPos = startPos - (itemsInAPage - 1); // 각 페이지 별 끝점

				if (endPos < 0) {
					endPos = 0;
				}

				if (startPos < 0) { // 데이터가 존재하지 않을 때
					System.out.printf("%d 페이지는 존재하지 않습니다.\n", page);
					continue;
				}

				// 최신 게시글 순으로 리스팅
				for (int i = startPos; i >= endPos; i--) {

					Article article = articles.get(i);

					System.out.printf("%d / %s\n", article.id, article.title);
				}

			}

			if (command.startsWith("article search ")) {

				String[] commandBits = command.split(" ");

				String searchKeyword = commandBits[2];

				int page = 1;

				if (commandBits.length >= 4) {
					page = Integer.parseInt(command.split(" ")[3]);
				}

				if (page <= 1) {
					page = 1;
				}

				System.out.println("==게시물검색==");

				List<Article> searchResultArticles = new ArrayList<>(); 

				// 검색된 결과의 수를 먼저 구하기
				for (Article article : articles) {
					if (article.title.contains(searchKeyword)) {
						searchResultArticles.add(article);
					}
				}

				if (searchResultArticles.size() == 0) {
					System.out.println("검색결과가 존재하지 않습니다");
					continue;
				}

				System.out.println("번호/제목");

				int itemsInAPage = 10; // 1페이지당 게시물 갯수
				int startPos = searchResultArticles.size() - 1; // 시작 인덱스 초기값
				startPos -= (page - 1) * itemsInAPage; // 각 페이지 별 시작점
				int endPos = startPos - (itemsInAPage - 1); // 각 페이지 별 끝점

				if (endPos < 0) {
					endPos = 0;
				}

				if (startPos < 0) { // 데이터가 존재하지 않을 때
					System.out.printf("%d 페이지는 존재하지 않습니다.\n", page);
					continue;
				}

				// 최신 게시글 순으로 리스팅
				for (int i = startPos; i >= endPos; i--) {

					Article article = searchResultArticles.get(i);

					System.out.printf("%d / %s\n", article.id, article.title);
				}
			}

			// 게시물상세보기

			if (command.startsWith("article detail ")) {
				System.out.println("==게시물상세==");

				int inputedId = Integer.parseInt(command.split(" ")[2]);

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 글은 존재하지 않습니다\n", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

			}

			// 게시물 수정하기
			if (command.startsWith("article modify ")) {
				System.out.println("==게시물 수정하기==");

				int inputedId = Integer.parseInt(command.split(" ")[2]);

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물을 존재하지 않습니다.", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				modify(inputedId, title, body);

				System.out.printf("%d번 게시글이 수정되었습니다\n", inputedId);

			}

			// 시스템 종료
			if (command.equals("article exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}

		}
		scanner.close();

	}

}
