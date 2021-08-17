package com.ben.languages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ben.languages.models.Language;
import com.ben.languages.repositories.LanguageRepository;

@Service
public class LanguageService {
	private final LanguageRepository languageRepository;
    
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }
    
    // reads all
    public List<Language> allLanguages() {
        return languageRepository.findAll();
    }
    
    // create
    public Language createLanguage(Language b) {
        return languageRepository.save(b);
    }
    
    // read one
    public Language findLanguage(Long id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if(optionalLanguage.isPresent()) {
            return optionalLanguage.get();
        } else {
            return null;
        }
    }
    
    // update
    public Language updateLanguage(Language b) {
    	return languageRepository.save(b);
    }
    
    // delete
    public void deleteLanguage(Long id) {
    	Optional<Language> optionalLanguage = languageRepository.findById(id);
        if(optionalLanguage.isPresent()) {
        	languageRepository.deleteById(id);
        }
    }
}