package demo.service;

import demo.domain.Word;
import rx.Observable;

public interface WordService {

	Word getSubject();
	Word getVerb();
	Word getArticle();
	Observable<Word> getAdjective();
	Word getNoun();
	
}
