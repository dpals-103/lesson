package test;

import java.util.Scanner;

import test.container.Container;
import test.controller.ArticleController;
import test.controller.MemberController;

public class App {

	private MemberController memberController;
	private ArticleController articleController;
	
	public App() {
		memberController = Container.memberController; 
		articleController = Container.articleController;
	}
	
	public void run() {
		Scanner sc = Container.scanner;
		
		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine(); 
			
			if(cmd.startsWith("member ")) {
				memberController.doCommand(cmd); 
			}
			if(cmd.startsWith("article ")) {
				articleController.doCommand(cmd); 
			}
			//프로그램종료
			if(cmd.equals("exit")) {
				System.out.println("프로그램종료");
				break;
			}
		}
		
		sc.close();
	}

}
