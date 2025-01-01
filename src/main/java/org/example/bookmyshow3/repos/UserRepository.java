package org.example.bookmyshow3.repos;

import org.example.bookmyshow3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    public Optional<User> findById(Long aLong);

    public Optional<User> findByEmail(String email);

    public User save(User user);

}
