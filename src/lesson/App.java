package lesson;

import java.util.Scanner;

public class App { // 변수선언 (공유공간, 공통메모..), 실행x 

	// 공공재 article 클래스 생성
	Article article1 = new Article();
	Article article2 = new Article();

	// 자주쓰는 함수 빼놓기
	Article getArticle(int id) {
		if (id == 1) {
			return article1;
			
		} 
		else if (id == 2) {
			return article2;
			
		}
		return null;
		
	}

	public void run() {  // 실제 작동구간

		// 명령어 스캐너 기능 만들기
		Scanner scanner = new Scanner(System.in);

		// 게시물번호 초기값
		int lastArticleId = 0;

		// 게시물 수 제한하기
		int maxArticlesCount = 2;

		// 루프 시작
		while (true) {
			System.out.printf("명령어 ) ");
			String command = scanner.nextLine();

			// 게시물등록
			if (command.equals("article add")) {
				System.out.println("==게시물등록==");

				if (lastArticleId > maxArticlesCount) {
					System.out.println("더 이상 생성할 수 없습니다");
					continue;
				}

				int id = lastArticleId + 1;
				String title;
				String body;

				System.out.printf("제목 : ");
				title = scanner.nextLine();
				System.out.printf("내용 : ");
				body = scanner.nextLine();
				
				Article article = getArticle(id);

				article.id = id;
				article.title = title;
				article.body = body;
				
				System.out.printf("%d번 글이 생성되었습니다\n", id);

				lastArticleId = id;
			}
			
			

			// 게시물리스트
			if (command.equals("article list")) {
				System.out.println("==게시물리스트==");

				if (lastArticleId == 0) {
					System.out.println("==게시물이 존재하지 않습니다==");
					continue;
				}

				System.out.println("번호/제목");
				
				for ( int i = 1; i <= lastArticleId; i++) {
					Article article = getArticle(i);
			
					System.out.printf("%d / %s\n", article.id, article.title);

				}
			}

			// 게시물상세보기

			if (command.startsWith("article detail ")) {
				System.out.println("==게시물상세==");

				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (inputedId == 0 || inputedId > lastArticleId) {
					System.out.printf("%d번 글은 존재하지 않습니다\n", inputedId);
					continue;
				}
				
				Article article = getArticle(inputedId);
				
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
				

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
