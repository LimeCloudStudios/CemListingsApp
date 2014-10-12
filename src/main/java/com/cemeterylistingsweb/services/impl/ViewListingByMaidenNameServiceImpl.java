/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.services.ViewListingByMaidenNameService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class ViewListingByMaidenNameServiceImpl implements ViewListingByMaidenNameService{
    @Autowired
    PublishedDeceasedListingRepository publishRepo;
    @Autowired
    RequiresApprovalDeceasedListingRepository waitRepo;
    
    @Override
    public PublishedDeceasedListing find(Long id) {
        return publishRepo.findOne(id);
    }

    @Override
    public PublishedDeceasedListing persist(PublishedDeceasedListing entity) {
        return publishRepo.save(entity);
    }

    @Override
    public PublishedDeceasedListing merge(PublishedDeceasedListing entity) {
        if(entity.getPublishedListingID()!=null){
            return publishRepo.save(entity);
        }
        return null;
    }

    @Override
    public void remove(PublishedDeceasedListing entity) {
        publishRepo.delete(entity);
    }

    @Override
    public List<PublishedDeceasedListing> findAll() {
        return publishRepo.findAll();
    }
    
    public List<PublishedDeceasedListing> findListingByMaidenName(String name){
         List<PublishedDeceasedListing> names = new ArrayList();
       
        
        List<PublishedDeceasedListing> all = publishRepo.findAll();
        
        if(name.isEmpty() || name.equals(""))
                return all;
       
        for (PublishedDeceasedListing all1 : all) {
            
            if (all1.getMaidenName().equals(name)) {
                names.add(all1);
            }
            else if(all1.getSurname().startsWith(name))
                names.add(all1);
            else if(all1.getSurname().contains(name))
                names.add(all1);
        }
            
                return names;
    }
}