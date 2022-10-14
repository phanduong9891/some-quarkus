package com.axonactive.quarkus.starting.rest;

import com.axonactive.quarkus.starting.entity.TopicEntity;
import com.axonactive.quarkus.starting.rest.client.model.Topic;
import com.axonactive.quarkus.starting.service.TopicService;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/api/topics")
public class TopicResource {

    @Inject
    TopicService topicService;
    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(topicService.getAll()).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Topic topicRequest){
        TopicEntity createTopic = topicService.save(topicRequest);
        return Response.created(URI.create("/api/topics" + "/" + createTopic.getId())).entity(createTopic).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(TopicEntity topicEntity){
        TopicEntity updateTopic = new TopicEntity();
        try{
            updateTopic = topicService.update(topicEntity);
        } catch (Exception e) {
            logger.error("Topic not found");
            Response.status(Response.Status.NOT_FOUND)
                            .entity(e.getMessage()).build();

        }
        return Response.ok(updateTopic).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id")Integer topicId){
        try{
            topicService.delete(topicId);

        } catch (Exception e) {
           logger.error("Topic not found");
        }
        return Response.ok().build();
    }

}