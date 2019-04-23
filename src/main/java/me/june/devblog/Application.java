package me.june.devblog;

import javafx.geometry.Pos;
import me.june.devblog.posts.Post;
import me.june.devblog.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
