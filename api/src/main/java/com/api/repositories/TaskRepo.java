package com.api.repositories;

import com.api.collections.tasks.TaskDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends MongoRepository<TaskDocument, String>{

    @Query("{$and: [{startingDate: {$gt: ?0}}, {endingDate: {$lt: ?1}}]}")
    List<TaskDocument> findAllByDate(LocalDateTime startingDate, LocalDateTime endingDate);

    @Query("{$and: [{startingDate: {$gt: ?0}}, {endingDate: {$lt: ?1}}, {status: ?2}]}")
    List<TaskDocument> findAllByDateAndStatus(LocalDateTime startingDate, LocalDateTime endingDate, Integer status);

}
