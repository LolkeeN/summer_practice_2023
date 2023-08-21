package com.vasyl.summer.practice.database.repository;

import com.vasyl.summer.practice.database.entity.UserQuestion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserQuestionRepository extends CrudRepository<UserQuestion, String> {

    @Query("select uq from UserQuestion uq "
            + "where uq.isAnswered = false ")
    List<UserQuestion> getAllByAnsweredIsFalse();
}
