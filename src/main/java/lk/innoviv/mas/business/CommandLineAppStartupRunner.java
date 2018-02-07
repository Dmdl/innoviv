package lk.innoviv.mas.business;

import lk.innoviv.mas.persistence.model.User;
import lk.innoviv.mas.persistence.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("--------------App start------------");
        userRepository.create();

        List<User> users = userRepository.getUsers();
        System.out.println(users);
    }
}
