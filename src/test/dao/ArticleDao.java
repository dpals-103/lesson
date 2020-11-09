package test.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import test.container.Container;
import test.dto.Article;
import test.dto.Board;

public class ArticleDao {
	private List<Article> articles;
	private int lastArticleId;
	private int lastBoardId;
	private List<Board> boards;

	public ArticleDao() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		lastBoardId = 0;
		boards = new ArrayList<>();

	}

	public int write(int boardId, int memberId, String title, String body) {

		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		article.memberId = memberId;
		article.boardId = boardId;

		articles.add(article);

		lastArticleId = article.id;
		return article.id;

	}

	public List<Article> getArticles(int boardId) {
		List<Article> newArticles = new ArrayList<>();
		
		for( Article article : articles ) {
			if ( article.boardId == boardId ) {
				newArticles.add(article);
			}
		}
		Collections.reverse(newArticles);
		
		return newArticles;
	}

	public void modify(int id, String title, String body) {
		Article article = getArticle(id);

		article.title = title;
		article.body = body;
	}

	public Article getArticle(int inputedId) {
		for (Article article : articles) {
			if (article.id == inputedId) {
				return article;
			}
		}
		return null;
	}

	public void remove(int id) {
		int index = getIndexOf(id);

		if (index == -1) {
			return;
		}
		articles.remove(index);
	}

	private int getIndexOf(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id) {
				return i;
			}
		}
		return -1;
	}

	public int makeBoard(String boardTitle) {

		Board board = new Board();

		board.boardId = lastBoardId + 1;
		board.boardTitle = boardTitle;

		boards.add(board);

		lastBoardId = board.boardId;
		lastArticleId = 0; 
		return board.boardId;

	}

	public Board getBoardByBoardId(int boardId) {
		for (Board board : boards) {
			if (board.boardId == boardId) {
				return board;
			}
		}
		return null;
	}

}
