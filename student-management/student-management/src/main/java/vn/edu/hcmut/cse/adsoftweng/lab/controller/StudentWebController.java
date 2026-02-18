package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    @GetMapping
    public String getAllStudents(org.springframework.ui.Model model, @RequestParam(required = false) String keyword) {
        List<Student> allStudents = service.getAll();

        if (keyword != null && !keyword.isEmpty()) {
            allStudents = service.searchByName(keyword);
        } else {
            allStudents = service.getAll();
        }

        model.addAttribute("dsSinhVien", allStudents);
        return "students";
    }
}
