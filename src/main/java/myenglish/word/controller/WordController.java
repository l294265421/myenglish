package myenglish.word.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import myenglish.common.IpUtil;
import myenglish.log.WordMDC;
import myenglish.word.po.Cetymology;
import myenglish.word.po.Eetymology;
import myenglish.word.service.ICetymologyService;
import myenglish.word.service.IEetymologyService;
import myenglish.word.service.IMeaningsService;
import myenglish.word.service.IPhoneticsService;

@Controller
public class WordController {
	@Autowired
	private IPhoneticsService phoneticServiceImpl;
	@Autowired
	private IMeaningsService meaningsServiceImpl;
	@Autowired
	private IEetymologyService eetymologyServiceImpl;
	@Autowired
	private ICetymologyService cetymologyServiceImpl;
	private Logger logger = LoggerFactory.getLogger(WordController.class);
	
	@RequestMapping(value = {"/home", "/"})
	public String home(Model model, HttpServletRequest request,
			HttpServletResponse response){
		return "home";
	}
	
	@RequestMapping(value = "/test")
	public String newHome(Model model, HttpServletRequest request,
			HttpServletResponse response){
		return "test";
	}
	
	@RequestMapping(value = "/word")
	public String word(Model model, HttpServletRequest request,
			HttpServletResponse response){
		String word = request.getParameter("word");
		String ip = IpUtil.getClientIp(request);
		WordMDC.putWordValues(ip, word);
		if (word == null || word.trim().length() == 0) {
			return "";
		}
		
		model.addAttribute("word", word);
		
		logger.info("查询音标");
		String phonetic = phoneticServiceImpl.getPhoneticByWord(word);
		if (org.apache.commons.lang.StringUtils.isBlank(phonetic)) {
			phonetic = "";
		}
		model.addAttribute("phonetic", phonetic);
		
		logger.info("查询中文意思");
		String meanings = meaningsServiceImpl.getMeaningsByWord(word);
		if (org.apache.commons.lang.StringUtils.isBlank(meanings)) {
			meanings = "";
		}
		model.addAttribute("meanings", meanings);
		
		logger.info("查询中文词源");
		Cetymology cetymology = cetymologyServiceImpl.getCetymologyByWord(word);
        if (cetymology == null) {
			cetymology = new Cetymology();
			cetymology.setParent("暂无");
			cetymology.setCetymology("暂无");
		}
		model.addAttribute("cetymology", cetymology);
        
		logger.info("查询英文词源");
		List<Eetymology> eetymologies = eetymologyServiceImpl.getEetymologyByWord(word);
		model.addAttribute("eetymologies", eetymologies);
		
		WordMDC.clearWordValues();
		
		return "word";
	}
	
	@RequestMapping(value = "/insertCE")
	@ResponseBody
	public String insertCE(HttpServletRequest request) {
		String word = request.getParameter("word");
		if (org.apache.commons.lang.StringUtils.isBlank(word)) {
			return "fail";
		}
		String parent = request.getParameter("parent");
		if (org.apache.commons.lang.StringUtils.isBlank(parent)) {
			return "fail";
		}
		String cetymology = request.getParameter("cetymology");
		if (org.apache.commons.lang.StringUtils.isBlank(cetymology)) {
			return "fail";
		}
		Cetymology cetymologyObject = new Cetymology(word, parent, cetymology);
		int intResult = cetymologyServiceImpl.insertCetymology(cetymologyObject);
		if (intResult == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/updateCE")
	@ResponseBody
	public String updateCE(HttpServletRequest request) {
		String word = request.getParameter("word");
		if (org.apache.commons.lang.StringUtils.isBlank(word)) {
			return "fail";
		}
		String parent = request.getParameter("parent");
		if (org.apache.commons.lang.StringUtils.isBlank(parent)) {
			return "fail";
		}
		String cetymology = request.getParameter("cetymology");
		if (org.apache.commons.lang.StringUtils.isBlank(cetymology)) {
			return "fail";
		}
		Cetymology cetymologyObject = new Cetymology(word, parent, cetymology);
		int intResult = cetymologyServiceImpl.updateCetymology(cetymologyObject);
		if (intResult == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/inputautocompletion")
	@ResponseBody
	public List<String> inputautocompletion(HttpServletRequest request) {
		return null;
	}
}
