package com.productservice.productservice;

import com.productservice.productservice.inheritancerelations.tableperclass.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication implements CommandLineRunner {

    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    ProductServiceApplication(@Qualifier("tpc_mentorrepository") MentorRepository mentorRepository,
                              StudentRepository studentRepository,
                              UserRepository userRepository) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Mentor mentor = new Mentor();
        mentor.setName("Deepak");
        mentor.setEmail("deepak.kasera@scaler.com");
        mentor.setAvgRating(4.8);
        mentorRepository.save(mentor);

        Student student = new Student();
        student.setEmail("abhishek@scaler.com");
        student.setName("Abhishek");
        student.setPsp(90.0);
        studentRepository.save(student);

        User user = new User();
        user.setEmail("arshi@gmail.com");
        user.setName("Arshi");
        userRepository.save(user);

        //Get all the Users.
        List<User> users = userRepository.findAll();

        for (User user1 : users) {
            System.out.println(user1.toString());
        }
    }

}
