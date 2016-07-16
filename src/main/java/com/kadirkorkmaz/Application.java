package com.kadirkorkmaz;

import com.kadirkorkmaz.database.repository.MediaReository;
import com.kadirkorkmaz.database.repository.NavigationRepository;
import com.kadirkorkmaz.database.repository.UserRepository;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    UserRepository userRepo;

    @Autowired
    MediaReository mediaRepo;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    /*
     // Markdown processor to convert from HTML to Markdown
     // and Markdown ot HTML 
     // https://github.com/sirthias/pegdown
     */
    @Bean
    public PegDownProcessor getPegDownProcessor() {
        return new PegDownProcessor();
    }

    @Autowired
    NavigationRepository navRepo;

    @Bean
    public Integer recordImageContent() {

//        String[] images_ = {"kadirkorkmaz.jpg", "castle.jpg", "bridge.jpg"};
//        List<String> images = new LinkedList<>();
//        Random generator = new Random();
//        for (int i = 1; i < 25; i++) {
//            int randomNumber = generator.nextInt(3);
//            MediaEntity m = new MediaEntity(MediaTypeEnum.IMAGE, images_[randomNumber], "Only Image", (String.valueOf(randomNumber) + ".jpg"));
//            mediaRepo.save(m);
//        }
        
        return 0;
    }

}
