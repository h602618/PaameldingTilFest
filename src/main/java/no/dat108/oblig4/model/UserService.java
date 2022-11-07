package no.dat108.oblig4.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public User addUser(Integer phone, String firstName, String lastName, String gender, String hash, String salt) {
        User user = new User();
        user.setPhone(phone);
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setGender(gender);
        user.setHash(hash);
        user.setSalt(salt);

        return userRepo.save(user);
    }

    public List<User> findAllSorted() {
        return findAll().stream().sorted(Comparator.comparing(User::getFirst_name).thenComparing(User::getLast_name)).collect(Collectors.toList());
    }
}
