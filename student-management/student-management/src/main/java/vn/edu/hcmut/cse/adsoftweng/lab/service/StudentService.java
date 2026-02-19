package vn.edu.hcmut.cse.adsoftweng.lab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;


@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String name) {
        List<Student> allStudents = repository.findAll();
        List<Student> findedStudents = new ArrayList<>();
        String lowercaseName = name.toLowerCase();

        for (Student m : allStudents) {
            if (m.getName().toLowerCase().equals(lowercaseName)) {
                findedStudents.add(m);
            } 
        }

        return findedStudents;
    }
}
