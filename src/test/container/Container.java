package test.container;

import test.controller.ArticleController;
import test.controller.MemberController;
import test.service.MemberService;
import test.session.Session;

public class Container {

	public static MemberController memberController;
	public static ArticleController articleController;
	public static MemberService memberService;
	public static Session session;

	static {
		
		memberService = new MemberService();
		memberController = new MemberController();
		articleController = new ArticleController();
		session = new Session();
	}
}
