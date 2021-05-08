package com.example.demo.student;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        if(studentRepository.findByEmail(student.getEmail()).isPresent()){
            throw new BadRequestException("Email " + student.getEmail() + " taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
        if(!studentRepository.findById(studentId).isPresent()){
            throw new StudentNotFoundException("Student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
}
