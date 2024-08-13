package com.pratyush.transcript.service;

import com.pratyush.transcript.entity.TranscriptEntity;
import com.pratyush.transcript.repository.TranscriptRepository;
import com.pratyush.transcript.utility.TranscriptUtility;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TranscriptService {

    @Autowired
    private TranscriptRepository transcriptRepository;

    @Autowired
    private TranscriptUtility transcriptUtility;

    public String saveTranscript(MultipartFile transcriptFile) {

        LinkedHashMap<String, String> segmentsMap = null;
        byte[] bytes = null;
        try {
            //segmentsMap = transcriptUtility.parseTranscriptFile(transcriptFile);

            bytes = transcriptFile.getBytes();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        TranscriptEntity transcriptEntity = TranscriptEntity.builder().transcriptFile(bytes).build();

        System.out.println(transcriptEntity);
        transcriptRepository.save(transcriptEntity);


        //System.out.println(segmentsMap);

        return "URL";
    }

    public TranscriptEntity getTranscriptById(Long id) {
        TranscriptEntity transcriptEntity = null;

        transcriptEntity = transcriptRepository.getTranscriptEntityById(id);

        return transcriptEntity;
    }

    public LinkedHashMap<String, String> getSegmentsForTranscript(byte[] transcriptFile) {

        if(transcriptFile.length == 0) {
            return null;
        }

        LinkedHashMap<String, String> segments = null;
        try {
            segments = transcriptUtility.getSegmentsInfo(transcriptFile);
        } catch (IOException e) {
            System.out.println(e);
        }

        return segments;
    }
}
