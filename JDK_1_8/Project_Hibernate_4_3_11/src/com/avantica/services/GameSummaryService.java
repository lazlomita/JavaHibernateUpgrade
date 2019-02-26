package com.avantica.services;

import java.util.List;

import com.avantica.dao.GameSummaryDao;
import com.avantica.model.GameSummary;

public class GameSummaryService {

	private static GameSummaryDao gameSummaryDao;

	public GameSummaryService() {
		gameSummaryDao = new GameSummaryDao();
	}

	public void persist(GameSummary entity) {
		gameSummaryDao.openCurrentSessionwithTransaction();
		gameSummaryDao.persist(entity);
		gameSummaryDao.closeCurrentSessionwithTransaction();
	}

	public void update(GameSummary entity) {
		gameSummaryDao.openCurrentSessionwithTransaction();
		gameSummaryDao.update(entity);
		gameSummaryDao.closeCurrentSessionwithTransaction();
	}

	public GameSummary findById(Long id) {
		gameSummaryDao.openCurrentSession();
		GameSummary book = gameSummaryDao.findById(id);
		gameSummaryDao.closeCurrentSession();
		return book;
	}

	public void delete(Long id) {
		gameSummaryDao.openCurrentSessionwithTransaction();
		GameSummary book = gameSummaryDao.findById(id);
		gameSummaryDao.delete(book);
		gameSummaryDao.closeCurrentSessionwithTransaction();
	}

	public List<GameSummary> findAll() {
		gameSummaryDao.openCurrentSession();
		List<GameSummary> books = gameSummaryDao.findAll();
		gameSummaryDao.closeCurrentSession();
		return books;
	}

	public void deleteAll() {
		gameSummaryDao.openCurrentSessionwithTransaction();
		gameSummaryDao.deleteAll();
		gameSummaryDao.closeCurrentSessionwithTransaction();
	}

	public GameSummaryDao gameSummaryDao() {
		return gameSummaryDao;
	}
}
