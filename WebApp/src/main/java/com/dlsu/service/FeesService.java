package com.dlsu.service;

import com.dlsu.model.Fees;
import com.dlsu.model.repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by avggo on 7/30/2017.
 */

@Service("feesService")
public class FeesService {

    @Autowired
    private FeesRepository feesRepositoryRepository;

    public void saveFees(Fees fee){
        feesRepositoryRepository.save(fee);
    }

    public Fees findByAppId(Integer appId){
        return feesRepositoryRepository.findByAppId(appId);
    }
}
