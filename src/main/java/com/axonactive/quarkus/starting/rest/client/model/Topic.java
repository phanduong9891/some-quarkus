package com.axonactive.quarkus.starting.rest.client.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@Data
@ApplicationScoped
@NoArgsConstructor
public class Topic {

    private String name;

    private String color;
}
