package test.container;

import java.util.Scanner;

import test.controller.ArticleController;
import test.controller.MemberController;
import test.dao.ArticleDao;
import test.dao.MemberDao;
import test.service.ArticleService;
import test.service.MemberService;
import test.session.Session;


public class Container {

	public static MemberController memberController;
	public static ArticleController articleController;
	public static Scanner scanner;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static Session session;
	public static ArticleService articleService;
	public static ArticleDao articleDao;

	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		articleService = new ArticleService();
		memberService = new MemberService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
		
	}
}
