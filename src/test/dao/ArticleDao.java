package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Article;

public class ArticleDao {
	private List<Article> articles; 
	private int lastArticleId; 
	
	public ArticleDao() {
		articles = new ArrayList<>();
		lastArticleId = 0; 
		
		makeTestData(); 
	}

	private void makeTestData() {
		for(int i = 1; i <= 5; i++) {
			write(1, "제목"+ i,"내용"+ i);
		}
		for(int i = 6; i <= 10; i++) {
			write(2, "제목"+ i,"내용"+ i);
		}
	}

	public int write(int memberId, String title, String body) {
		Article article = new Article();
		
		article.id = lastArticleId + 1; 
		article.title = title;
		article.body = body;
		article.memberId = memberId;
		
		articles.add(article); 
		
		lastArticleId = article.id; 
		
		return article.id;
	}

	public List<Article> getArticles() {
		return articles; 
	}
	
}
