package com.ben.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ben.languages.models.Language;
import com.ben.languages.services.LanguageService;

@Controller
public class LanguageController {
	private final LanguageService languageService;
    
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }
    @RequestMapping("/")
    public String homeDirect() {
    	return "redirect:/languages";
    }
    @RequestMapping("/languages")
    public String index(Model model,@ModelAttribute("language") Language language) {
    	List<Language> languages = languageService.allLanguages();
    	model.addAttribute("languages", languages);
    	return "/languages/index.jsp";
    }
    @RequestMapping("/languages/new")
    public String newLanguage(@ModelAttribute("language") Language language) {
    	return "/languages/new.jsp";
    }
    @RequestMapping(value="/languages",method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("language") Language language, BindingResult result, Model model) {
    	if(result.hasErrors()) {
        	List<Language> languages = languageService.allLanguages();
        	model.addAttribute("languages", languages);
    		return "/languages/index.jsp";
    	} else {
    		languageService.createLanguage(language);
    		return "redirect:/languages";
    	}
    }
    @RequestMapping("/languages/{id}")
    public String show(Model model,@PathVariable("id") Long id) {
    	Language language = languageService.findLanguage(id);
    	model.addAttribute("language", language);
    	return "/languages/show.jsp";
    }
    @RequestMapping("/languages/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
    	Language language = languageService.findLanguage(id);
        model.addAttribute("language", language);
        return "/languages/edit.jsp";
    }
    @RequestMapping(value="/languages/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("language") Language language, BindingResult result) {
    	if (result.hasErrors()) {
            return "/languages/edit.jsp";
        } else {
        	languageService.updateLanguage(language);
            return "redirect:/languages";
        }
    }
    @RequestMapping(value="/languages/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
    	languageService.deleteLanguage(id);
        return "redirect:/languages";
    }
}