package com.vasyl.summer.practice.database.repository;

import com.vasyl.summer.practice.database.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> getByEmail(String email);

    @Query("select u from User u")
    Page<User> getAllUsers(Pageable pageable);

    Page<User> getUsersByPartnerId(String partnerId, Pageable pageable);

    @Query("select u from User u "
            + "where LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    Page<User> getByEmailLikeIgnoreCase(@Param("email") String email, Pageable pageable);

    Boolean existsByEmail(String email);
    Optional<User> getByAccountId(Long accountId);
}
