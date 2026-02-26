package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import vn.edu.hcmut.cse.adse.lab.entity.Student;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(Model model) {
        // 1. Xuống tầng Service để lấy danh sách sinh viên từ Database
        List<Student> students = service.getAll();

        // 2. Đặt dữ liệu vào Model (như đặt đồ ăn lên khay)
        // Đặt tên cho dữ liệu này là "dsSinhVien" để lát nữa HTML gọi ra dùng
        model.addAttribute("dsSinhVien", students);

        // 3. Trả về tên file giao diện (không cần gõ đuôi .html)
        return "students";
    }
}