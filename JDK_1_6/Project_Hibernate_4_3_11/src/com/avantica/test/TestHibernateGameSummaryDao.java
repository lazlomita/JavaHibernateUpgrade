package com.avantica.test;

import java.util.List;

import com.avantica.model.GameSummary;
import com.avantica.services.GameSummaryService;

public class TestHibernateGameSummaryDao {

	public static void main(String[] args) {
		GameSummaryService gameSummaryService = new GameSummaryService();
		
		GameSummary gameSummary1 = new GameSummary();
		gameSummary1.setClientGesture("rock");
		gameSummary1.setServerGesture("rock");
		gameSummary1.setResult("tie");
		gameSummary1.setDate(new java.util.Date());

		GameSummary gameSummary2 = new GameSummary();
		gameSummary2.setClientGesture("paper");
		gameSummary2.setServerGesture("paper");
		gameSummary2.setResult("tie");
		gameSummary2.setDate(new java.util.Date());

		GameSummary gameSummary3 = new GameSummary();
		gameSummary3.setClientGesture("scissors");
		gameSummary3.setServerGesture("scissors");
		gameSummary3.setResult("tie");
		gameSummary3.setDate(new java.util.Date());		
		
		System.out.println("*** Persist - start ***");
		gameSummaryService.persist(gameSummary1);
		gameSummaryService.persist(gameSummary2);
		gameSummaryService.persist(gameSummary3);
		List<GameSummary> gameSummaries1 = gameSummaryService.findAll();
		System.out.println("Books Persisted are :");
		for (GameSummary b : gameSummaries1) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** Persist - end ***");
		System.out.println("*** Update - start ***");
		gameSummary1.setResult("WIN");
		gameSummaryService.update(gameSummary1);
		System.out.println("Book Updated is =>" +gameSummaryService.findById(gameSummary1.getId()).toString());
		System.out.println("*** Update - end ***");
		System.out.println("*** Find - start ***");
		Long id1 = gameSummary1.getId();
		GameSummary another = gameSummaryService.findById(id1);
		System.out.println("Book found with id " + id1 + " is =>" + another.toString());
		System.out.println("*** Find - end ***");
		System.out.println("*** Delete - start ***");
		Long id3 = gameSummary3.getId();
		gameSummaryService.delete(id3);
		System.out.println("Deleted book with id " + id3 + ".");
		System.out.println("Now all books are " + gameSummaryService.findAll().size() + ".");
		System.out.println("*** Delete - end ***");
		System.out.println("*** FindAll - start ***");
		List<GameSummary> books2 = gameSummaryService.findAll();
		System.out.println("Books found are :");
		for (GameSummary b : books2) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** FindAll - end ***");
		System.out.println("*** DeleteAll - start ***");
		gameSummaryService.deleteAll();
		System.out.println("Books found are now " + gameSummaryService.findAll().size());
		System.out.println("*** DeleteAll - end ***");

		System.out.println("********** END OF TEST **********");
		System.exit(0);
	}
}