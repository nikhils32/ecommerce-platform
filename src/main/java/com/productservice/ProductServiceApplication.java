package com.productservice;

import com.productservice.productservice.model.Product;
import com.productservice.productservice.repositories.CategoryRepository;
import com.productservice.productservice.repositories.PriceRepository;
import com.productservice.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

//@EnableDiscoveryClient

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

//    private final MentorRepository mentorRepository;
//    private final StudentRepository studentRepository;
//    private final UserRepository userRepository;
//
//    ProductServiceApplication(@Qualifier("tpc_mentorrepository") MentorRepository mentorRepository,
//                              StudentRepository studentRepository,
//                              UserRepository userRepository) {
//        this.mentorRepository = mentorRepository;
//        this.studentRepository = studentRepository;
//        this.userRepository = userRepository;
//    }

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    public ProductServiceApplication(CategoryRepository categoryRepository,
                                     ProductRepository productRepository,
                                     PriceRepository priceRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    // @Override
    // @Transactional
    public void run(String... args) throws Exception {
        // Category category = new Category();
        // category.setName("Apple Devices");

        // Category savedCategory = categoryRepository.save(category);


//        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString("513d108d-886d-451c-a3d7-1438be973ecb"));
//        if (optionalCategory.isEmpty()) {
//            throw new Exception("Category was null");
//        }
//
//        Category category = optionalCategory.get();
//
//        Product product = new Product();
//        product.setTitle("iPhone 15 pro");
//        product.setDescription("Best iPhone ever");
//        product.setCategory(optionalCategory.get());
////
////        Product savedProduct = productRepository.save(product);
//
////        List<Product> products =  category.getProducts();
////        for(Product product : products){
////            System.out.println(product.getTitle());
////        }

//        Price price = new Price();
//        price.setCurrency("INR");
//        price.setValue(100000);
//        // Price savedPrice = priceRepository.save(price);
//
//        Category category2 = new Category();
//        category2.setName("Apple Devices");
//        Category savedCategoy = categoryRepository.save(category2);
//
//        Product product1 = new Product();
//        product1.setTitle("iPhone 15 pro");
//        product1.setDescription("Best iPhone ever");
//        product1.setCategory(savedCategoy);
//        // product1.setPrice(savedPrice);
//        product1.setPrice(price);
//
//        Product savedProduct1 = productRepository.save(product1);

//        productRepository.deleteById(UUID.fromString("111bb398-e46e-49f8-a271-ca6869d16825"));
        List<Product> products = this.productRepository.findAll();
        for (Product product : products) {
            System.out.println(product.getTitle());
        }
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Mentor mentor = new Mentor();
//        mentor.setName("Deepak");
//        mentor.setEmail("deepak.kasera@scaler.com");
//        mentor.setAvgRating(4.8);
//        mentorRepository.save(mentor);
//
//        Student student = new Student();
//        student.setEmail("abhishek@scaler.com");
//        student.setName("Abhishek");
//        student.setPsp(90.0);
//        studentRepository.save(student);
//
//        User user = new User();
//        user.setEmail("arshi@gmail.com");
//        user.setName("Arshi");
//        userRepository.save(user);
//
//        //Get all the Users.
//        List<User> users = userRepository.findAll();
//
//        for (User user1 : users) {
//            System.out.println(user1.toString());
//        }
//    }

}
