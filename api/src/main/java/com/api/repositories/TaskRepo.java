package com.api.repositories;

import com.api.collections.tasks.TaskDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends MongoRepository<TaskDocument, String>{

    @Query("{$and: [{startingDate: {$gt: ?0}}, {endingDate: {$lt: ?1}}, {deviceId: ?2}]}")
    List<TaskDocument> findAllByDate(LocalDateTime startingDate, LocalDateTime endingDate, String deviceId);

    @Query("{$and: [{startingDate: {$gt: ?0}}, {endingDate: {$lt: ?1}}, {status: ?2}, {deviceId: ?3}]}")
    List<TaskDocument> findAllByDateAndStatus(LocalDateTime startingDate, LocalDateTime endingDate, Integer status, String deviceId);

    @Query("{deviceId: ?0}")
    List<TaskDocument> findAllByDeviceId(String deviceId);
}

