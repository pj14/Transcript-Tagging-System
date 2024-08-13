package com.pratyush.transcript.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Blob;
import java.util.LinkedHashMap;
import java.util.Map;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranscriptEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] transcriptFile;

    //private int segments;

    //private LinkedHashMap<String, String> parsedSegments;

    private String url;

}
