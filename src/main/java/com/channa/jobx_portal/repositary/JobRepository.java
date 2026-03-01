package com.channa.jobx_portal.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.channa.jobx_portal.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // Custom Query for searching jobs by location
    @Query("SELECT j FROM Job j WHERE j.location = :location")
    List<Job> findByLocation(@Param("location") String location);
}
