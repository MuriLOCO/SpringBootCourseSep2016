package demo.dao;

import demo.domain.Word;


public interface WordDao {

	static final String ADJECTIVE = "ADJECTIVE";
	static final String NOUN = "NOUN";
	
	Word getWord();
	
}
