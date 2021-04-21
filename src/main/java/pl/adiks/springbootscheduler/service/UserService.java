package pl.adiks.springbootscheduler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.adiks.springbootscheduler.model.User;
import pl.adiks.springbootscheduler.repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //schedule a job to add object to database in DB (every 5 sec)
    @Scheduled(fixedRate = 5000)
    public void add2DbJob() throws NoSuchAlgorithmException {
        User user = new User(SecureRandom.getInstanceStrong().nextInt(), "Adrian");
        userRepository.save(user);
        System.out.println("add service call in " + new Date().toString());
    }

    @Scheduled(cron = "0/15 * * * * *")
    public void fetchDbJob() {
        List<User> users = userRepository.findAll();
        System.out.println("fetch service call in " + new Date().toString());
        System.out.println("number of records: " + users.size());
        logger.info("users: {}", users);
    }
}
