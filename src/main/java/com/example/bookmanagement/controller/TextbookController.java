package com.example.bookmanagement.controller;

import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.domain.Vo.*;
import com.example.bookmanagement.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TextbookController {
    @Resource
    private ITextbookService textbookService;

    @RequestMapping("/teacher/findbookinfo")
    public String showAllTextbooks(Model model, HttpServletRequest request) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        List<Textbook> textbooks = textbookService.getAllTextbook();
        model.addAttribute("textbooks", textbooks);

        return "teacher/findTextbookInfo";
    }

    @RequestMapping(value = "/teacher/findbookinfo/{searchMethod}/{searchContent}")
    public String findTextbooks(Model model, HttpServletRequest request,@PathVariable String searchMethod,
                              @PathVariable String searchContent) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        System.out.println(searchMethod + ' ' + searchContent);
        if (searchMethod.equals("0") & searchContent.equals("0")) {
            List<Textbook> textbooks = textbookService.getAllTextbook();
            model.addAttribute("textbooks", textbooks);
        }else{
            List<Textbook> textbooks = textbookService.findTextbooksBySearchType(searchMethod, searchContent);
            model.addAttribute("textbooks", textbooks);
        }
        return "teacher/findTextbookInfo";
    }

    @RequestMapping("/teacher/findebook")
    public String showAllEBooks(Model model, HttpServletRequest request) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        List<EBookVo> eBookVos = textbookService.getAllEbook();
        model.addAttribute("ebooks", eBookVos);

        return "teacher/findEBookInfo";
    }

    @RequestMapping(value = "/teacher/findebook/{searchMethod}/{searchContent}")
    public String findEBooks(Model model, HttpServletRequest request,@PathVariable String searchMethod,
                                @PathVariable String searchContent) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        System.out.println(searchMethod + ' ' + searchContent);
        if (searchMethod.equals("0") & searchContent.equals("0")) {
            List<EBookVo> textbooks = textbookService.getAllEbook();
            model.addAttribute("textbooks", textbooks);
        }else{
            List<EBookVo> textbooks = textbookService.findEBooksBySearchType(searchMethod, searchContent);
            model.addAttribute("textbooks", textbooks);
        }
        return "teacher/findEBookInfo";
    }

    @RequestMapping(value = "/teacher/buyapplication")
    public String showApplicationForm(Model model, HttpServletRequest request) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        return "teacher/buyApplication";
    }

    @RequestMapping(value = "teacher/buyapplication",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult addBuyApplication(Model model, HttpServletRequest request, @RequestBody BuyBook buyBook) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        buyBook.setTeacherId(teacher.getTeacherId());
        System.out.println(buyBook.getBookName()+' '+buyBook.getPublishDate());
        String publishDate = buyBook.getPublishDate().substring(0,4);
        buyBook.setPublishDate(publishDate);
        System.out.println(buyBook.getPublishDate());
        boolean res = textbookService.addBuyApplication(buyBook);
        ProcessResult ar;
        if (!res) {
            ar = new ProcessResult(false);
            return ar;
        }
        ar = new ProcessResult(true);
        return ar;
    }


    @RequestMapping(value = "teacher/classes/{openId}/showthistextbook")
    public String retunAddTextbook(Model model, HttpServletRequest request, @PathVariable String openId) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        List<Textbook> textbooks = textbookService.returnThisTextbook(openId);
        model.addAttribute("textbooks", textbooks);
        System.out.println("教参查询结果：" + model.getAttribute("textbooks").toString());
        return "/teacher/showThisTextbook";
    }

    @RequestMapping(value = "teacher/classes/{openId}/showthistextbook",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult deleteTextbook(Model model, HttpServletRequest request, @PathVariable String openId,
                                        @RequestBody ChooseTextbookKey chooseTextbookKey) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        boolean res = textbookService.deleteTextbook(chooseTextbookKey);
        ProcessResult ar;
        if (!res) {
            ar = new ProcessResult(false);
            return ar;
        }
        ar = new ProcessResult(true);
        return ar;
    }

    @RequestMapping(value = "teacher/classes/{openId}/addtextbook")
    public String showAllTextbook(Model model, HttpServletRequest request, @PathVariable String openId) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        List<Textbook> textbooks = textbookService.getAllTextbook();
        model.addAttribute("textbooks", textbooks);
        return "/teacher/addTextbook";
    }

    @RequestMapping(value = "teacher/classes/{openId}/addtextbook/{searchMethod}/{searchContent}")
    public String findTextbookBySearchContent(Model model, HttpServletRequest request, @PathVariable String openId,
                                  @PathVariable String searchMethod, @PathVariable String searchContent) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        if (searchMethod.equals("0") & searchContent.equals("0")) {
            List<Textbook> textbooks = textbookService.getAllTextbook();
            model.addAttribute("textbooks", textbooks);
        }else{
            List<Textbook> textbooks = textbookService.findTextbooksBySearchType(searchMethod, searchContent);
            model.addAttribute("textbooks", textbooks);
        }
        return "/teacher/addTextbook";
    }


    @RequestMapping(value = "teacher/classes/{openId}/addtextbook",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult addChooseTextbook(Model model, HttpServletRequest request, @RequestBody ChooseTextbookKey chooseTextbookKey,
                                           @PathVariable String openId) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        boolean res = textbookService.addTextBook(chooseTextbookKey);
        ProcessResult ar;
        if (!res) {
            ar = new ProcessResult(false);
            return ar;
        }
        ar = new ProcessResult(true);
        return ar;
    }

    @RequestMapping(value = "teacher/classes/{openId}/addtextbook/{searchMethod}/{searchContent}",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult addChooseTextbook(Model model, HttpServletRequest request, @RequestBody ChooseTextbookKey chooseTextbookKey,
                                           @PathVariable String openId, @PathVariable String searchMethod,
                                           @PathVariable String searchContent) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        chooseTextbookKey.setOpenId(openId);
        boolean res = textbookService.addTextBook(chooseTextbookKey);
        ProcessResult ar;
        if (!res) {
            ar = new ProcessResult(false);
            return ar;
        }
        ar = new ProcessResult(true);
        return ar;
    }

}
