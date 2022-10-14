package com.axonactive.quarkus.starting.service;

import com.axonactive.quarkus.starting.dao.TopicDao;
import com.axonactive.quarkus.starting.entity.TopicEntity;
import com.axonactive.quarkus.starting.rest.client.model.Topic;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TopicService {

    @Inject
    private TopicDao topicDao;

    @Inject
    private Logger logger;


    public List<TopicEntity> getAll() {
        logger.info("Get all topics");
        return topicDao.findAll();
    }

    @Transactional
    public TopicEntity save(Topic topicRequest){
        //need to search name first to see if duplicated, but this is just a demo
        TopicEntity newTopic = new TopicEntity();
        newTopic.setName(topicRequest.getName());
        newTopic.setColor(topicRequest.getColor());

        return topicDao.save(newTopic);
    }

    @Transactional
    public TopicEntity update(TopicEntity topicEntity) throws Exception {
        TopicEntity updatedTopic = topicDao.findById(topicEntity.getId());
        if(updatedTopic != null){
            updatedTopic.setName(topicEntity.getName());
            updatedTopic.setColor(topicEntity.getColor());
        }
        else throw new Exception("Not found");
        return updatedTopic;
    }

    @Transactional
    public void delete(Integer topicId) throws Exception {
        TopicEntity deletedTopic = topicDao.findById(topicId);
        if(deletedTopic != null){
            topicDao.delete(deletedTopic);
        }
        else throw new Exception("Not found");
    }
}
