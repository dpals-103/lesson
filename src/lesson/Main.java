package lesson;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		// 명령어 스캐너 기능 만들기
		Scanner scanner = new Scanner(System.in);

		// 게시물번호 초기값
		int lastArticleId = 0;

		// 1번 게시물 저장소
		Article article1 = new Article();

		article1.id = 0;
		article1.title = "";
		article1.body = "";

		// 2번 게시물 저장소
		Article article2 = new Article();

		article2.id = 0;
		article2.title = "";
		article2.body = "";

		// 루프 시작
		while (true) {
			System.out.printf("명령어 ) ");
			String command = scanner.nextLine();

			// 게시물등록
			if (command.equals("article add")) {
				System.out.println("==게시물등록==");

				int id = lastArticleId + 1;
				String title;
				String body;

				System.out.printf("제목 : ");
				title = scanner.nextLine();
				System.out.printf("내용 : ");
				body = scanner.nextLine();

				if (id == 1) {
					article1.id = id;
					article1.title = title;
					article1.body = body;
				}

				if (id == 2) {
					article2.id = id;
					article2.title = title;
					article2.body = body;
				}

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

				System.out.printf("%d / %s\n", article1.id, article1.title);
				System.out.printf("%d / %s\n", article2.id, article2.title);
			}

			// 게시물상세보기

			if (command.startsWith("article detail ")) {
				System.out.println("==게시물상세==");

				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (inputedId == 0 || inputedId > lastArticleId) {
					System.out.printf("%d번 글은 존재하지 않습니다\n", inputedId);
					continue;
				}

				if (inputedId == 1) {
					System.out.printf("번호 : %d\n", article1.id);
					System.out.printf("제목 : %s\n", article1.title);
					System.out.printf("내용 : %s\n", article1.body);
				}

				else if (inputedId == 2) {
					System.out.printf("번호 : %d\n", article2.id);
					System.out.printf("제목 : %s\n", article2.title);
					System.out.printf("내용 : %s\n", article2.body);
				}

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
