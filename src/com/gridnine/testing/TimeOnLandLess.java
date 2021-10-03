package com.gridnine.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//класс для фильтра 3
public class TimeOnLandLess implements Filter {
    private final long hours;

    TimeOnLandLess(long hours) {
        this.hours = hours;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        System.out.println("Исключаются полеты с общим временем," +
                "проведённом на земле, которое превышает " + hours + " час(а)");
        List<Flight> adjustedFlights = new ArrayList<>();
        for (Flight f : flights) {
            List<Segment> segments = f.getSegments();
            Iterator<Segment> iterator = segments.listIterator();
            long timeOnLand = 0;
            Segment current;
            if (iterator.hasNext()) {
                current = iterator.next();
            } else {
                continue;
            }
            while (iterator.hasNext()) {
                Segment temp = current;
                current = iterator.next();
                timeOnLand += Duration.between(temp.getArrivalDate(), current.getDepartureDate()).toHours();
            }
            if (timeOnLand <= hours) {
                adjustedFlights.add(f);
            }
        }
        return adjustedFlights;
    }
}
