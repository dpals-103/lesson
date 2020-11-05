package test.service;

import java.util.List;

import test.container.Container;
import test.dao.ArticleDao;
import test.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao; 

	public ArticleService() {
		articleDao = Container.articleDao; 
	}

	public int write(int memberId, String title, String body) {
		return articleDao.write(memberId, title, body) ;
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
}
