package com.sudarshan.coronavirusapptracker.services;

import com.sudarshan.coronavirusapptracker.models.LocationStates;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {

    private static String VIRUS_DATA_URI = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStates> allStates = new ArrayList<>();

    public List<LocationStates> getAllStates() {
        return allStates;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData(){
        List<LocationStates> newStates = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URI))
                .build();

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(httpResponse.body());
            StringReader reader  = new StringReader(httpResponse.body());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                LocationStates locationStates = new LocationStates();
                locationStates.setState(record.get("Province/State"));
                locationStates.setCountry(record.get("Country/Region"));
                locationStates.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
                //System.out.println(state);
                System.out.println(locationStates);
                newStates.add(locationStates);
            }
            this.allStates = newStates;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
