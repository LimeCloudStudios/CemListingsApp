/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.presentation.rest;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.services.SearchSurname;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Zaakir
 */

@Controller
@RequestMapping(value = "api/SearchSurname") 
public class SearchSurnameController {
    
    @Autowired
    SearchSurname cs;
    
    @RequestMapping(value = "create",method = RequestMethod.POST) // This the uri e.g http://localhost:8084/askweb/api/club/create
    @ResponseBody //Converts output or response to JSON String
    public String create(@RequestBody PublishedDeceasedListing PDl) { // @RequestBody for converting incoming JSON call to Object
        cs.persist(PDl);
        System.out.println(" Create the Called ");
        return "Club created";
    }
    
    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public List<PublishedDeceasedListing> getAll(){
        return cs.findAll();
    }
    
    @RequestMapping(value = "update",method = RequestMethod.PUT) //This the uri e.g http://localhost:8084/askweb/api/club/update
    @ResponseBody
    public String update(@RequestBody PublishedDeceasedListing cemetery) {
        cs.merge(cemetery);
        System.out.println(" Update Called ");
        return "Club Update";
    }
    
    @RequestMapping(value = "id/{id}",method = RequestMethod.GET) //http://localhost:8084/askweb/api/club/1234
    @ResponseBody
    public PublishedDeceasedListing getClub(@PathVariable Long id) { //@PathVariable used to bind the id value
        
        System.out.println(" ID called ");
        return cs.find(id);
    }
    
}