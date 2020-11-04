package test;

import java.util.Scanner;

import test.container.Container;
import test.controller.ArticleController;
import test.controller.MemberController;

public class App {

	MemberController memberControlloer = new MemberController();
	ArticleController articleContrller = new ArticleController();

	public void run() {
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.startsWith("member ")) {
				Container.memberController.run(sc, command);
			}
			if (command.startsWith("article ")) {
				Container.articleController.run(sc, command);
			}
			// 프로그램 종료
			if (command.equals("exit")) {
				System.out.println("프로그램을 종료합니다");
				break;
			}
		}
		sc.close();
	}

}
