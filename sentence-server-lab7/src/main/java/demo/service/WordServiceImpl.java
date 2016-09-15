package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import demo.dao.AdjectiveClient;
import demo.dao.ArticleClient;
import demo.dao.NounClient;
import demo.dao.SubjectClient;
import demo.dao.VerbClient;
import demo.domain.Word;
import demo.domain.Word.Role;
import rx.Observable;

@Service
public class WordServiceImpl implements WordService {

	@Autowired VerbClient verbClient;
	@Autowired SubjectClient subjectClient;
	@Autowired ArticleClient articleClient;
	@Autowired AdjectiveClient adjectiveClient;
	@Autowired NounClient nounClient;
	
	
	@Override
	public Word getSubject() {
		return subjectClient.getWord();
	}
	
	@Override
	public Word getVerb() {	
		return verbClient.getWord();
	}
	
	@Override
	public Word getArticle() {
		return articleClient.getWord();
	}	
	
		
	@Override
	@HystrixCommand(fallbackMethod="getEmptyWord")
	public Observable<Word> getAdjective() {
	    return new ObservableResult<Word>() {
	        @Override
	        public Word invoke() {
	        	return new Word (adjectiveClient.getWord().getWord(), Role.adjective);
	        }
	    };
}
	
	@Override
	public Word getNoun() {
		return nounClient.getWord();
	}	
	
	public Word getEmptyWord(){
		return new Word("");
	}
}
