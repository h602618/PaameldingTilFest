package no.dat108.oblig4.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User findUserByPhone(Integer phone) {
        return userRepo.findUserByPhone(phone);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User addUser(Integer phone, String firstName, String lastName, String gender, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setGender(gender);
        user.setPassword(password);

        return userRepo.save(user);
    }

    public List<User> findAllSorted() {
        return findAll().stream().sorted(Comparator.comparing(User::getFirst_name).thenComparing(User::getLast_name)).toList();
    }
}
