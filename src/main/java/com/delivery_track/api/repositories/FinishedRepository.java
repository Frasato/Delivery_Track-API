package com.delivery_track.api.repositories;

import com.delivery_track.api.models.Finished;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedRepository extends JpaRepository<Finished, String> {}
