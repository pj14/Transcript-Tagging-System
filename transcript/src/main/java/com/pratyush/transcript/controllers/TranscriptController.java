package com.pratyush.transcript.controllers;

import com.pratyush.transcript.entity.TranscriptEntity;
import com.pratyush.transcript.models.TranscriptRequest;
import com.pratyush.transcript.models.TranscriptResponse;
import com.pratyush.transcript.service.TranscriptService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class TranscriptController {

    @Autowired
    private TranscriptService transcriptService;

    @PostMapping(value = "/transcript", consumes = "multipart/form-data")
    public ResponseEntity addTranscriptFile(MultipartFile transcript) {
        if (transcript != null) {
        System.out.println(transcript.getName());

            String url = transcriptService.saveTranscript(transcript);

            return new ResponseEntity(url, HttpStatus.CREATED);
        }


        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/transcript/{id}")
    public ResponseEntity getTranscriptById(@PathVariable Long id) {
        if(id != null) {
            System.out.println("id" + id);
            TranscriptEntity transcriptEntity = null;

            transcriptEntity = transcriptService.getTranscriptById(id);

            if(transcriptEntity == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            LinkedHashMap<String, String> segmentsMap = transcriptService
                    .getSegmentsForTranscript(transcriptEntity.getTranscriptFile());

            TranscriptResponse transcriptResponse = TranscriptResponse.builder().id(id)
                    .segments(segmentsMap.size()).segmentsMap(segmentsMap).build();

            return new ResponseEntity(transcriptResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
