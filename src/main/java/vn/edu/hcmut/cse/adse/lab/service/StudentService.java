package vn.edu.hcmut.cse.adse.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    // Lấy toàn bộ danh sách sinh viên
    public List<Student> getAll() {
        return repository.findAll();
    }

    // Tìm sinh viên theo ID (MSSV)
    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }
}