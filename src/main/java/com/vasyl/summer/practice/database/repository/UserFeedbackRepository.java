package com.vasyl.summer.practice.database.repository;

import com.vasyl.summer.practice.database.entity.UserFeedback;
import org.springframework.data.repository.CrudRepository;

public interface UserFeedbackRepository extends CrudRepository<UserFeedback, String> {

}
