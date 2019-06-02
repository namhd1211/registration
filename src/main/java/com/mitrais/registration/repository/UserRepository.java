package com.mitrais.registration.repository;

import com.mitrais.registration.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phone);
}
