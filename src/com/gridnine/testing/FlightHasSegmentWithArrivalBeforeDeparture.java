package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;


//класс для фильтра 2
public class FlightHasSegmentWithArrivalBeforeDeparture implements Filter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        System.out.println("Исключаются полеты с сегментами с датой прилёта раньше даты вылета");
        List<Flight> adjustedFlights = new ArrayList<>();
        for (Flight f : flights) {
            List<Segment> segments = f.getSegments();
            boolean hasSegmentWithArrivalBeforeDeparture = false;
            for (Segment s : segments) {
                if (s.getArrivalDate().isBefore(s.getDepartureDate())) {
                    hasSegmentWithArrivalBeforeDeparture = true;
                }
            }
            if (hasSegmentWithArrivalBeforeDeparture) continue;
            adjustedFlights.add(f);
        }
        return adjustedFlights;
    }
}
