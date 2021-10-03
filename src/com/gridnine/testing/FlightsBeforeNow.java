package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//класс для фильтра 1
public class FlightsBeforeNow implements Filter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        System.out.println("Исключаются полеты с вылетом до текущего момента времени");
        List<Flight> adjustedFlights = new ArrayList<>();
        for (Flight f : flights) {
            List<Segment> segments = f.getSegments();
            boolean hasDepartureBeforeNow = false;
            LocalDateTime now = LocalDateTime.now();
            for (Segment s : segments) {
                if (s.getDepartureDate().isBefore(now)) {
                    hasDepartureBeforeNow = true;
                    break;
                }
            }
            if (hasDepartureBeforeNow) continue;
            adjustedFlights.add(f);
        }
        return adjustedFlights;
    }
}
