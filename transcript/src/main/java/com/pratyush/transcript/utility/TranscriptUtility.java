package com.pratyush.transcript.utility;

import com.pratyush.transcript.entity.TranscriptEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TranscriptUtility {

    public LinkedHashMap<String, String> parseTranscriptFile(File tempFile) throws IOException {
        LinkedHashMap<String, String> segmentsMap = new LinkedHashMap<>();
        String regex = "(\\d{2}:){2}(\\d{2},)(\\d{3})\\s(-->)\\s(\\d{2}:){2}(\\d{2},)(\\d{3})";

        Pattern pattern = Pattern.compile(regex);
        String key = "";

        //File tempFile = new File("C:\\Users\\Pratyush Joshi\\Documents\\Study\\Applications\\transcript\\src\\main\\resources\\tempFile.temp");
        //tempFile.createNewFile();
        //transcriptFile.transferTo(tempFile);

        LineIterator it = FileUtils.lineIterator(tempFile, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                if(!StringUtils.hasText(line) || org.apache.commons.lang3.StringUtils.isNumeric(line)) {
                    continue;
                }
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    key = line;
                } else {
                    segmentsMap.put(key, line);
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        //tempFile.delete();

        return segmentsMap;
    }

    public LinkedHashMap<String, String> getSegmentsInfo(byte[] transcriptFile) throws IOException {
        File tempFile = new File("C:\\Users\\Pratyush Joshi\\Documents\\Study\\Applications\\transcript\\src\\main\\resources\\tempFile.temp");
        FileUtils.writeByteArrayToFile(tempFile, transcriptFile);

        LinkedHashMap<String, String> segmentsMap = null;

        segmentsMap = parseTranscriptFile(tempFile);

        tempFile.delete();

        return segmentsMap;
    }
}
