package lesson;

import java.util.Scanner;

public class App { // 변수선언 (공유공간, 공통메모..), 실행x

	
	// 공공재생성
	
	Article[] articles = new Article[3]; 

	int articleSize = 0;
	int lastArticleId = 0;
	
	
	int articleSize() {
		return articleSize;
	}
	
	
	Article getArticle(int id) {
		int index = getIndexOf(id); 
		
		if (index == -1) {  
			return null;
		}
		return articles[index];
	}

	
	// 인덱스 찾기 함수 
	public int getIndexOf(int id) {
		for (int i = 0; i < articles.length; i++) {
			if (id == articles[i].id) {
				return i;
			}
		}

		return -1;
	}

	// 인덱스 지우기 함수 
	public void remove(int index) {
		for (int i = index + 1; i < articleSize; i++) {
			articles[i - 1] = articles[i];
		}

		articleSize--;
	}

	
	
	public void run() { // 실제 작동구간


		// 명령어 스캐너 기능 만들기
		Scanner scanner = new Scanner(System.in);

		// 게시물 수 제한하기
		int maxArticlesCount = articles.length;

		// 루프 시작
		while (true) {
			System.out.printf("명령어 ) ");
			String command = scanner.nextLine();

			
			
			// 게시물 삭제
			if (command.startsWith("article delete ")) {

				int inputedId = Integer.parseInt(command.split(" ")[2]);

				System.out.printf("%d번 게시글이 삭제되었습니다\n", inputedId);
				
				Article article = getArticle(inputedId);
				
				if(article == null) {
					System.out.printf("%d번 글이 존재하지 않습니다/n",inputedId);
				}
				
				int index = getIndexOf(inputedId);

				if (index != -1) {
					remove(index);
				}
			}
		
	
			
			
			// 게시물등록
			if (command.equals("article add")) {
				System.out.println("==게시물등록==");

				if (articleSize >= maxArticlesCount) {
					System.out.println("더 이상 생성할 수 없습니다");
					continue;
				}

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;

				System.out.printf("제목 : ");
				title = scanner.nextLine();
				System.out.printf("내용 : ");
				body = scanner.nextLine();

				Article article = new Article(); 
				//무작위로 큰 공간을 만드는게 아니고, 추가되는만큼(필요한만큼)만 공간을 만든다 

				article.id = id;
				article.title = title;
				article.body = body;

				System.out.printf("%d번 글이 생성되었습니다\n", id);
				
				articles[articleSize] = article;
				//articles[0] = article; 

				articleSize++;
			}
			
			
			

			// 게시물리스트
			if (command.equals("article list")) {
				System.out.println("==게시물리스트==");

				if ( articleSize() == 0 ) {
					System.out.println("==게시물이 존재하지 않습니다==");
					continue;
				}

				System.out.println("번호/제목");

				for ( int i = 0;  i < articleSize() ; i++ ) {
					
					Article article = articles[i];

					System.out.printf("%d / %s\n", article.id, article.title);

				}
			}

			
			
			
			// 게시물상세보기

			if (command.startsWith("article detail ")) {
				System.out.println("==게시물상세==");

				int inputedId = Integer.parseInt(command.split(" ")[2]);
				
				Article article = getArticle(inputedId);
				
				if ( article == null) {
					System.out.printf("%d번 글은 존재하지 않습니다\n", inputedId);
					continue;
				}

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
