package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.AdjectiveClient;
import demo.dao.NounClient;
import demo.dao.WordDao;

/**
 * Build a sentence by assembling randomly generated subjects, verbs, 
 * articles, adjectives, and nouns.  The individual parts of speech will 
 * be obtained by calling the various DAOs.
 */
@Service
public class SentenceServiceImpl implements SentenceService {

	
	private NounClient nounService;
	private AdjectiveClient adjectiveClient;
	

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		String sentence = "There was a problem assembling the sentence!";
		sentence =  
			String.format("%s %s.",
				
				nounService.getWord().getString(),
				adjectiveClient.getWord().getString());
		
		return sentence;
	}



	@Autowired
	public void setNounService(NounClient nounService) {
		this.nounService = nounService;
	}

	@Autowired
	public void setAdjectiveClient(AdjectiveClient adjectiveClient) {
		this.adjectiveClient = adjectiveClient;
	}	
	
	
	
	
}
