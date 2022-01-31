package com.example.bookmanagement.controller;

import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.Vo.*;
import com.example.bookmanagement.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    /**
     * 判断teacher是否存在
     * @param teacherId
     * @return
     */
    @RequestMapping("/isTeacherExist")
    @ResponseBody
    public String adminIsExist(@Param("teacherId") int teacherId){
        boolean b = teacherService.teacherIsExist(teacherId);
        if(b){
            return "true";
        }else{
            return "false";
        }
    }

    /**
     * 教师登陆
     * @param teacherId
     * @param password
     * @return
     */
    @RequestMapping("/teacherLogin")
    public String teacherLogin(Model model, @Param("teacherId") int teacherId,@Param("password") String password,HttpServletRequest request) {
        boolean res = teacherService.teacherLogin(teacherId,password);
        if(res==false){
            return "teacherLogin";
        }
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherPassword(password);
        teacher.setTeacherInfo(teacherService.teacherInfo(teacherId));
        request.getSession().setAttribute("teacher", teacher);
        model.addAttribute("teacher", teacher);
        return "redirect:/teacher/classes";
    }

    @RequestMapping("/teacher/classes")
    public String showClassesNow(Model model, HttpServletRequest request) throws ParseException {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        List<OpenClassVo> openClassNow = teacherService.getOpenClassNow(teacher);
        model.addAttribute("openClassNow", openClassNow);
        System.out.println("default：" + model.getAttribute("openClassNow").toString());
        return "teacher/classes";
    }

    @RequestMapping(value = "/teacher/classes/{startYear}/{startTerm}")
    public String showClasses(Model model, HttpServletRequest request,
                             @PathVariable String startTerm, @PathVariable String startYear) throws ParseException {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        if (startYear.equals("0") & startTerm.equals("0")) {
            List<OpenClassVo> openClassNow = teacherService.getOpenClassNow(teacher);
            model.addAttribute("openClassNow", openClassNow);
            System.out.println("查询结果：" + model.getAttribute("openClassNow").toString());
        }else{
            List<OpenClassVo> openClassNow = teacherService.getOpenClass(teacher, startYear, startTerm);
            model.addAttribute("openClassNow", openClassNow);
        }
        return "teacher/classes";
    }

    @RequestMapping(value = "/teacher/classes/{openId}/showinfo")
    public String showClassInfo(Model model, HttpServletRequest request,
                                @PathVariable String openId) throws ParseException {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        if (openId != null) {
            OpenClassVo thisOpenClass = teacherService.getThisClass(openId);
            model.addAttribute("thisOpenClass", thisOpenClass);
            System.out.println("这一门课：" + model.getAttribute("thisOpenClass").toString());
        }
        return "teacher/classinfo";
    }

    @RequestMapping(value = "/teacher/classes/{openId}/{courseId}/changeintro",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult changeClassIntro(Model model, HttpServletRequest request, @PathVariable String openId,
                                   @PathVariable int courseId, @RequestBody String intro) {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        Class classInfo = new Class();
        classInfo.setCourseId(courseId);
        classInfo.setCourseName(null);
        classInfo.setCourseIntroduction(intro);
        System.out.println(courseId + intro);
        boolean res = teacherService.changeIntro(classInfo);
        System.out.println(res);
        ProcessResult ar;
        if (!res) {
            ar = new ProcessResult(false);
            return ar;
        }
        OpenClassVo thisOpenClass = teacherService.getThisClass(openId);
        model.addAttribute("thisOpenClass", thisOpenClass);
        ar = new ProcessResult(true);
        return ar;
    }


//
//    /**
//     * 返回添加类别页面
//     * @return
//     */
//    @RequestMapping("/addCategoryPage")
//    public String addCategoryPage(@RequestParam("pageNum") int pageNum, Model model){
//        Page<BookCategory> page=bookCategoryService.selectBookCategoryByPageNum(pageNum);
//        model.addAttribute("page",page);
//        return "admin/addCategory";
//    }
//
//    /**
//     * 返回查询状态页面
//     * @return
//     */
//    @RequestMapping("/showStausPage")
//    public String showStausPage(){
//        return "admin/showStaus";
//    }
//
//    /**
//     * 返回管理员首页
//     * @return
//     */
//    @RequestMapping("/adminIndex")
//    public String returnAdminIndexPage(){
//        return "admin/index";
//    }
//
//
//
//    /**
//     * 返回查询用户页面
//     * @return
//     */
//    @RequestMapping("/showUsersPage")
//    public String showUsersPage(Model model,@RequestParam("pageNum") int pageNum){
//        Page<User> page=userService.findUserByPage(pageNum);
//        model.addAttribute("page",page);
//        return "admin/showUsers";
//    }
//
//    /**
//     * 返回查询书籍页面
//     * @return
//     */
//    @RequestMapping("/showBooksPage")
//    public String showBooksPage(Model model){
//        Page<BookVo> page=new Page<BookVo>();
//        page.setPageCount(1);
//        page.setPageNum(1);
//        model.addAttribute("page",page);
//        return "admin/showBooks";
//    }
//
//
//
//    /**
//     * 管理员退出登陆
//     * @param request
//     * @return
//     */
//    @RequestMapping("/adminLogOut")
//    public String userLogOut(HttpServletRequest request){
//        request.getSession().invalidate();
//        return "adminLogin";
//    }
//
//    /**
//     * 返回新增用户页面
//     * @return
//     */
//    @RequestMapping("/addUserPage")
//    public String addUserPage(){
//        return "admin/addUser";
//    }

}
