package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);

        model.addAttribute("student", student);
        return "studentDetail";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/new")
    public String addStudent() {
        return "studentAdd";
    }

    @PostMapping("/save")
    public String saveStudent(Student student, Model model) {
        Student existingStudent = service.getById(student.getId());

        if (existingStudent != null) {
            model.addAttribute("errorMessage", "Lỗi: ID Sinh viên '" + student.getId() + "' đã tồn tại!");
            return "studentAdd";
        }

        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Student student = service.getById(id);

        if (student == null) {
            return "redirect:/students";
        }

        model.addAttribute("student", student);
        return "studentEdit";
    }

    @PostMapping("/update")
    public String updateStudent(Student student) {
        service.saveStudent(student);

        return "redirect:/students";
    }
}
