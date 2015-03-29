package com.libqa.web.controller;

import com.libqa.application.framework.ResponseData;
import com.libqa.application.util.StringUtil;
import com.libqa.web.domain.Space;
import com.libqa.web.domain.Wiki;
import com.libqa.web.service.WikiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by songanji on 2015. 3. 1..
 */
@RestController
@RequestMapping("/")
@Slf4j
public class WikiController {

    @Autowired
    WikiService wikiService;

    @RequestMapping("wiki/main")
    public ModelAndView main(Model model){
        ModelAndView mav = new ModelAndView("wiki/main");
        return mav;
    }

    @RequestMapping("wiki/write")
    public ModelAndView write(Model model){
        ModelAndView mav = new ModelAndView("wiki/write");
        return mav;
    }

    @RequestMapping(value = "/wiki/{wikiId}", method = RequestMethod.GET)
    public ModelAndView spaceDetail(@PathVariable Integer wikiId) {
        Wiki wiki = wikiService.findById(wikiId);

        log.info("# view : {}", wiki);
        ModelAndView mav = new ModelAndView("wiki/view");

        mav.addObject("wiki", wiki);
        return mav;
    }

    @RequestMapping(value = "wiki/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<?> save(@ModelAttribute Wiki wiki){
        try{
            log.info("####### WIKI SAVE Begin INFO ########");
            log.info("wiki = {}", wiki);
            wiki.setSpaceId(1);
            wiki.setPasswd("1234");
            wiki.setUserNick("하이");
            wiki.setUserId(0);
            wiki.setInsertDate(new Date());

            Wiki result = wikiService.saveWithKeyword(wiki);

            log.info("####### WIKI SAVE After INFO ########");
            log.info("result = {}", result);

            return ResponseData.createSuccessResult(result);
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.toString());
            return ResponseData.createFailResult(wiki);
        }

    }

    @RequestMapping("wiki/bestList")
    public ModelAndView bestList(Model model){
        ModelAndView mav = new ModelAndView("wiki/template/_bestList");

        List<Wiki> list = new ArrayList<Wiki>();
        for(int i=0; i<5; i++){
            Wiki wiki = new Wiki();
            wiki.setUserNick("테스트"+i);
            wiki.setInsertDate(new Date());
            wiki.setLikeCount(i);
            wiki.setContents("지금은 "+i+" 베스트위키 테스트중");
            list.add(wiki);
        }
        mav.addObject("bestList",list);

        return mav;
    }

    @RequestMapping("wiki/allList")
    public ModelAndView allList(Model model){
        ModelAndView mav = new ModelAndView("wiki/template/_allList");

        List<Wiki> list = new ArrayList<Wiki>();
        for(int i=0; i<5; i++){
            Wiki wiki = new Wiki();
            wiki.setUserNick("테스트"+i);
            wiki.setInsertDate(new Date());
            wiki.setLikeCount(i);
            wiki.setContents("지금은 "+i+" 모든위키 테스트중");
            list.add(wiki);
        }
        mav.addObject("allList",list);

        return mav;
    }

    @RequestMapping("wiki/recentList")
    public ModelAndView recentList(Model model){
        ModelAndView mav = new ModelAndView("wiki/template/_recentList");

        List<Wiki> list = new ArrayList<Wiki>();
        for(int i=0; i<5; i++){
            Wiki wiki = new Wiki();
            wiki.setUserNick("테스트"+i);
            wiki.setTitle("위키 타이틀");
            wiki.setInsertDate(new Date());
            wiki.setLikeCount(i);
            wiki.setContents("지금은 "+i+" 최근활동위키 테스트중");
            list.add(wiki);
        }
        mav.addObject("recentList",list);


        return mav;
    }




}
