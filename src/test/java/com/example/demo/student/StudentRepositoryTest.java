package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown(){
        studentRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // given
        String email = "emiriam@gmail.com";
        Student student = new Student("Emiriam", email, Gender.FEMALE);
        studentRepository.save(student);

        // when
        Optional<Student> optionalUser = studentRepository.findByEmail(email);

        // then
        assertThat(optionalUser).isNotEmpty();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        // given
        String email = "emiriam@gmail.com";

        // when
        Optional<Student> optionalUser = studentRepository.findByEmail(email);

        // then
        assertThat(optionalUser).isEmpty();
    }

}