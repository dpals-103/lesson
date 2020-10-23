package lesson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App { // 변수선언 (공유공간, 공통메모..), 실행x

	private Article[] articles;
	private int lastArticleId;
	private int articleSize;

	// 생성자 메서드 (자동으로 초기화)
	public App() {
		articles = new Article[32];
		lastArticleId = 0;
		articleSize = 0;

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
		}
	}

	private int articleSize() {
		return articleSize;
	}

	private Article getArticle(int id) {
		int index = getIndexOf(id);

		if (index == -1) {
			return null;
		}
		return articles[index];
	}

	// 게시물 정보 저장하는 함수
	private int add(String title, String body) {

		// 게시물 무한대로 저장하기 ( 새 저장소 업체와 계약하기 )
		// 배열은 증가할 수 없다! -> 증가한게 아니라 공간이 큰 새로운 저장소로 옮겨가는 것

		if (isArticlesFull()) {
			System.out.printf("==배열 사이즈 증가 (%d => %d) ==\n", articles.length, articles.length * 2);

			Article[] newArticles = new Article[articles.length * 2];

			for (int i = 0; i < articles.length; i++) {
				newArticles[i] = articles[i];
			}

			articles = newArticles;
		}

		Article article = new Article();
		// 무작위로 큰 공간을 만드는게 아니고, 추가되는만큼(필요한만큼)만 공간을 만든다

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;

		articles[articleSize] = article;
		// articles[0] = article;

		articleSize++;
		lastArticleId = article.id;

		return article.id;

	}

	private boolean isArticlesFull() {
		return articleSize == articles.length;
	}

	// 인덱스 찾기 함수
	private int getIndexOf(int id) {
		for (int i = 0; i < articleSize; i++) {
			if (id == articles[i].id) {
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

		for (int i = index + 1; i < articleSize; i++) {
			articles[i - 1] = articles[i];
		}

		articleSize--;
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
				System.out.println("==게시물리스트==");

				int page = Integer.parseInt(command.split(" ")[2]);

				if (articleSize() == 0) {
					System.out.println("==게시물이 존재하지 않습니다==");
					continue;
				}

				System.out.println("번호/제목");
			
				
				int itemsInAPage = 10; // 1페이지당 게시물 갯수
				int startPos = articleSize() - 1; // 시작 인덱스 초기값 
				startPos -= (page - 1)*itemsInAPage;   // 각 페이지 별  시작점 
				int endPos = startPos - (itemsInAPage - 1); // 각 페이지 별 끝점 
				
				if ( endPos < 0 ) {
					endPos = 0; 
				}
				
				if (startPos < 0 ) { // 데이터가 존재하지 않을 때 
					System.out.printf("%d 페이지는 존재하지 않습니다.\n",page);
					continue;
				}
				
				// 최신 게시글 순으로 리스팅
				for (int i = startPos; i >= endPos; i--) {

					Article article = articles[i];

					System.out.printf("%d / %s\n", article.id, article.title);
				}

			}

			// 게시물 검색 : 해당 문자열이 포함되는지를 확인하면 됨!
			if (command.startsWith("article serch ")) {
				System.out.println("==게시물검색==");

				String articleText = command.split(" ")[2];

				for (int i = 0; i < articleSize; i++) {
					Article article = articles[i];
					if (article.body.contains(articleText) || article.title.contains(articleText))
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
