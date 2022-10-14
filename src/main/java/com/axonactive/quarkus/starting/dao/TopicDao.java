package com.axonactive.quarkus.starting.dao;


import com.axonactive.quarkus.starting.entity.TopicEntity;
import com.axonactive.quarkus.starting.rest.client.model.Topic;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface TopicDao {
    List<TopicEntity> findAll();

    TopicEntity save(TopicEntity topicEntity);

    void delete(TopicEntity topicEntity);

    TopicEntity findById(Integer topicId);
//
//    TopicEntity save(TopicEntity topicEntity);
//
//    TopicEntity findById(Integer topicId);


}
