package com.vasyl.summer.practice.database.repository;

import com.vasyl.summer.practice.database.entity.Activity;
import com.vasyl.summer.practice.database.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, String> {

    List<Activity> getAllByUsersContaining(User user);
}
