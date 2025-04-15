package com.example.atencionescovid19api.helpers;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVHelper {

    private final static Logger logger = LoggerFactory.getLogger(CSVHelper.class);

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static <T> List<T> loadObjectList(Class<T> type, InputStream inputStream) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
            CsvMapper mapper = new CsvMapper();
            mapper.findAndRegisterModules();
            
            MappingIterator<T> readValues
                    = mapper.reader(type).with(bootstrapSchema).readValues(inputStream);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file ", e);
            return Collections.emptyList();
        }
    }
}
