package com.example.bookmanagement.controller;

import com.example.bookmanagement.domain.Student;
import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.Teacher;
import com.example.bookmanagement.domain.Vo.ChooseclassVo;
import com.example.bookmanagement.domain.Vo.OpenClassVo;
import com.example.bookmanagement.service.IStudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class StudentController {

    @Resource
    private IStudentService studentService;

    /**
     * 判断student是否存在
     *
     * @param studentId
     * @return
     */
    @RequestMapping("/isStudentExist")
    @ResponseBody
    public String adminIsExist(@Param("studentId") int studentId) {
        boolean b = studentService.studentIsExist(studentId);
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 登陆
     *
     * @param studentId
     * @param password
     * @return
     */
    @RequestMapping("/studentLogin")
    public String studentLogin(Model model, @Param("studentId") int studentId, @Param("password") String password, HttpServletRequest request) throws ParseException {
        boolean res = studentService.studentLogin(studentId, password);
        if (res == false) {
            return "studentLogin";
        }
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentPassword(password);
        student.setStudentName(studentService.studentName(studentId));
        request.getSession().setAttribute("student", student);
        model.addAttribute("student", student);

        List<ChooseclassVo> ClasschooseNow = studentService.ClasschooseNow(student);
        model.addAttribute("ClasschooseNow", ClasschooseNow);
        return "student/index";
    }

    @RequestMapping("/student/index")
    public String studentLoginPage(Model model, HttpServletRequest request) throws ParseException {
        Student student = (Student) request.getSession().getAttribute("student");
        model.addAttribute("student", student);

        List<ChooseclassVo> ClasschooseNow = studentService.ClasschooseNow(student);
        model.addAttribute("ClasschooseNow", ClasschooseNow);
        return "/student/index";
    }

    @RequestMapping("student/classSel")
    public String studentclasssel(Model model, HttpServletRequest request) throws ParseException {
        Student student = (Student) request.getSession().getAttribute("student");
        model.addAttribute("student", student);

        List<OpenClassVo> allclasses = studentService.getAllClasses();
        model.addAttribute("allclasses", allclasses);
        return "student/classSel";
    }


    @RequestMapping(value = "/student/classSel/{grade}/{major}")
    public String showClasses(Model model, HttpServletRequest request,
                              @PathVariable Integer grade, @PathVariable String major) throws ParseException {
        Student student = (Student) request.getSession().getAttribute("student");
        model.addAttribute("student", student);

        if (grade != null & major != null) {
            List<OpenClassVo> allclasses = studentService.getClassSel(grade, major);
            model.addAttribute("allclasses", allclasses);
        }
        return "student/classSel";
    }

    @RequestMapping("student/classSearch")
    public String studentclasss(Model model, HttpServletRequest request) throws ParseException {
        Student student = (Student) request.getSession().getAttribute("student");
        model.addAttribute("student", student);

        List<OpenClassVo> allclassser = studentService.getAllClasses();
        model.addAttribute("allclassser", allclassser);
        return "student/classSearch";
    }

    @RequestMapping("student/classSearch/{searchContent}")
    public String studentclasssearch(Model model, HttpServletRequest request,
                                     @PathVariable String searchContent) throws ParseException {
        Student student = (Student) request.getSession().getAttribute("student");
        model.addAttribute("student", student);

        if (searchContent != null) {
            List<OpenClassVo> allclassser = studentService.getClassSer(searchContent);
            model.addAttribute("allclassser", allclassser);
            //System.out.println("查询结果：" + model.getAttribute("allclassser").toString());
        }
        return "student/classSearch";
    }



    @RequestMapping(value = "/student/{courseId}/showinfo")
    public String showClassInfo(Model model, HttpServletRequest request,
                                @PathVariable Integer courseId) throws ParseException {
        Student student = (Student) request.getSession().getAttribute("student");
        model.addAttribute("student", student);

        if (courseId != null) {
            Class classes =  new Class();

            classes.setCourseIntroduction(studentService.ClassInfo(courseId));

            //request.getSession().setAttribute("classes", classes);
            model.addAttribute("classes", classes);
            System.out.println("查询结果：" + model.getAttribute("classes").toString());

        }
        return "student/courseIntro";
    }
}





