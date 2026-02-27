package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    // @GetMapping
    // public String getAllStudents(Model model) {
    // // 1. Xuống tầng Service để lấy danh sách sinh viên từ Database
    // List<Student> students = service.getAll();
    //
    // // 2. Đặt dữ liệu vào Model (như đặt đồ ăn lên khay)
    // // Đặt tên cho dữ liệu này là "dsSinhVien" để lát nữa HTML gọi ra dùng
    // // Key "dsSinhVien" se duoc su dung ben file HTML
    // model.addAttribute("dsSinhVien", students);
    //
    // // 3. Trả về tên file giao diện (không cần gõ đuôi .html)
    // // Spring Boot se tu dong tim file tai:
    // src/main/resources/templates/students.html
    // return "students";
    // }
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;

        // Nếu có nhập từ khóa thì tìm theo tên, không thì lấy hết
        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }

        model.addAttribute("dsSinhVien", students);
        return "students"; // Trả về file students.html
    }

    @GetMapping("/{id}")
    public String getStudentDetail(@org.springframework.web.bind.annotation.PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "detail";
    }

    @GetMapping("/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @org.springframework.web.bind.annotation.PostMapping
    public String saveStudent(@org.springframework.web.bind.annotation.ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String editStudentForm(@org.springframework.web.bind.annotation.PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "form";
    }

    @org.springframework.web.bind.annotation.PostMapping("/{id}/delete")
    public String deleteStudent(@org.springframework.web.bind.annotation.PathVariable String id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}