package com.axonactive.quarkus.starting.dao.impl;

import com.axonactive.quarkus.starting.dao.TopicDao;
import com.axonactive.quarkus.starting.entity.TopicEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class topicDaoImpl implements TopicDao {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Override
    public List<TopicEntity> findAll() {
        return em.createQuery("SELECT t FROM TopicEntity t", TopicEntity.class).getResultList();
    }

    @Override
    public TopicEntity save(TopicEntity topicEntity) {
        return this.em.merge(topicEntity);
    }


    @Override
    public void delete(TopicEntity topicEntity) {
      this.em.remove(topicEntity);
    }

    @Override
    public TopicEntity findById(Integer topicId) {
        List<TopicEntity> topicEntities = em.createQuery("SELECT t FROM TopicEntity t WHERE t.id = :topicId", TopicEntity.class)
                .setParameter("topicId", topicId)
                .getResultList();
        if(!topicEntities.isEmpty()){
            return topicEntities.get(0);
        }
        return null;
    }


//
//    @Override
//    public TopicEntity save(TopicEntity topicEntity) {
//        return this.em.merge(topicEntity);
//    }
//
//    @Override
//    public TopicEntity findById(Integer topicId) {
//        List<TopicEntity> topicEntityList = em.createQuery("SELECT t FROM TopicEntity t WHERE t.id = :topicId", TopicEntity.class)
//                .setParameter("topicId", topicId).getResultList();
//        if (!topicEntityList.isEmpty())
//            return topicEntityList.get(0);
//        return null;
//    }

}