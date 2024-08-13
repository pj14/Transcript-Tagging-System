package com.pratyush.transcript.models;

import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranscriptResponse implements Serializable {
    private Long id;

    private int segments;

    private LinkedHashMap<String, String> segmentsMap;
}
