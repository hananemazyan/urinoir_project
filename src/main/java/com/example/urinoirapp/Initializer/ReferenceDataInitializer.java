package com.example.urinoirapp.Initializer;


import com.example.urinoirapp.Model.ReferenceData;
import com.example.urinoirapp.Service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ReferenceDataInitializer {

    @Autowired
    private ReferenceDataService referenceDataService;

    @Bean
    public CommandLineRunner initializeReferenceData() {
        return args -> {
            List<ReferenceData> referenceDataList = Arrays.asList(
                    new ReferenceData(0.0, 0.0),
                    new ReferenceData(1.2, 1.0),
                    new ReferenceData(2.5, 2.0),
                    new ReferenceData(3.8, 3.0),
                    new ReferenceData(4.9, 4.0),
                    new ReferenceData(5.7, 5.0),
                    new ReferenceData(6.5, 6.0),
                    new ReferenceData(7.3, 7.0),
                    new ReferenceData(8.1, 8.0),
                    new ReferenceData(8.8, 9.0),
                    new ReferenceData(9.5, 10.0),
                    new ReferenceData(10.1, 11.0),
                    new ReferenceData(10.6, 12.0),
                    new ReferenceData(11.0, 13.0),
                    new ReferenceData(11.3, 14.0),
                    new ReferenceData(11.5, 15.0),
                    new ReferenceData(11.6, 16.0),
                    new ReferenceData(11.7, 17.0),
                    new ReferenceData(11.8, 18.0),
                    new ReferenceData(11.9, 19.0),
                    new ReferenceData(11.10, 20.0),
                    new ReferenceData(11.11, 21.0),
                    new ReferenceData(11.12, 22.0),
                    new ReferenceData(11.13, 23.0),
                    new ReferenceData(11.14, 24.0),
                    new ReferenceData(11.15, 25.0),
                    new ReferenceData(11.16, 26.0),
                    new ReferenceData(11.17, 27.0),
                    new ReferenceData(11.18, 28.0),
                    new ReferenceData(11.19, 29.0),
                    new ReferenceData(11.20, 30.0),
                    new ReferenceData(11.21, 31.0),
                    new ReferenceData(11.22, 32.0),
                    new ReferenceData(11.23, 33.0),
                    new ReferenceData(11.24, 34.0),
                    new ReferenceData(11.25, 35.0),
                    new ReferenceData(11.7, 36.0),
                    new ReferenceData(11.6, 37.0),
                    new ReferenceData(11.5, 38.0),
                    new ReferenceData(11.3, 39.0),
                    new ReferenceData(11.0, 40.0),
                    new ReferenceData(10.7, 41.0),
                    new ReferenceData(10.3, 42.0),
                    new ReferenceData(9.8, 43.0),
                    new ReferenceData(9.3, 44.0),
                    new ReferenceData(8.7, 45.0),
                    new ReferenceData(8.1, 46.0),
                    new ReferenceData(7.4, 47.0),
                    new ReferenceData(6.7, 48.0),
                    new ReferenceData(5.9, 49.0),
                    new ReferenceData(5.1, 50.0),
                    new ReferenceData(4.2, 51.0),
                    new ReferenceData(3.3, 52.0),
                    new ReferenceData(2.4, 53.0),
                    new ReferenceData(1.5, 54.0),
                    new ReferenceData(0.6, 55.0),
                    new ReferenceData(0.0, 56.0),
                    new ReferenceData(0.0, 57.0),
                    new ReferenceData(0.0, 58.0),
                    new ReferenceData(0.0, 59.0)
            );
            for (ReferenceData referenceData : referenceDataList) {
                referenceDataService.saveReferenceData(referenceData);
            }
        };
    }
}