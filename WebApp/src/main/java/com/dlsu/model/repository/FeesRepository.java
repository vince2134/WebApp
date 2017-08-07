package com.dlsu.model.repository;

import com.dlsu.model.Fees;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by avggo on 7/30/2017.
 */
public interface FeesRepository extends CrudRepository<Fees, Integer> {

    Fees findByAppId(Integer appId);
}
