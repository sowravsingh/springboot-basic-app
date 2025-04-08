package com.spring.components;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeateosLinks {

    List<Link> links =  new ArrayList<>();

    public void addLinks(Link link){
        this.links.add(link);
    }

}
