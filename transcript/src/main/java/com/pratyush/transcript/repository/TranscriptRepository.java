package com.pratyush.transcript.repository;

import com.pratyush.transcript.entity.TranscriptEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends CrudRepository<TranscriptEntity, Long> {

    TranscriptEntity save(TranscriptEntity transcriptEntity);

    TranscriptEntity getTranscriptEntityById(Long id);
}
