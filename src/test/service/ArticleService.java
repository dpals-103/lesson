package test.service;

import java.util.List;

import test.container.Container;
import test.dao.ArticleDao;
import test.dto.Article;
import test.dto.Board;

public class ArticleService {
	
	private ArticleDao articleDao; 

	public ArticleService() {
		articleDao = Container.articleDao; 
	}
	
	public int write(int boardId, int memberId, String title, String body) {
		return articleDao.write(boardId, memberId, title, body);
	}

	public List<Article> getArticles(int boardId) {
		return articleDao.getArticles(boardId);
	}

	public void modify(int id, String title, String body) {
		articleDao.modify(id, title, body);
	}

	public Article getArticle(int inputedId) {
		return articleDao.getArticle(inputedId);
	}

	public void remove(int id) {
		articleDao.remove(id);
	}
	public int makeBoard(String boardTitle) {
		return articleDao.makeBoard(boardTitle);
	}

	public Board getBoardByBoardId(int boardId) {
		return articleDao.getBoardByBoardId(boardId);
	}
	
}
