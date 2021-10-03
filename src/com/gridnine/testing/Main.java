package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //генерация тестовых данных
        List<Flight> flights = FlightBuilder.createFlights();

        //список с фильтрами
        List<Filter> list = new ArrayList<>();

        //главный фильтр (применяет все переданные фильтры)
        FlightsFilter flightsFilter;
        //список с измененными полетами
        List<Flight> adjustedFlights;

        //фильтр 1 вылет до текущего момента времени
        flightsFilter = new FlightsFilter(new FlightsBeforeNow());
        adjustedFlights = flightsFilter.filter(flights);
        printFlights(adjustedFlights);

        //фильтр 2 имеются сегменты с датой прилёта раньше даты вылета
        flightsFilter = new FlightsFilter(new FlightHasSegmentWithArrivalBeforeDeparture());
        adjustedFlights = flightsFilter.filter(flights);
        printFlights(adjustedFlights);

        //фильтр 3 общее время, проведённое на земле превышает два часа
        // (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)
        flightsFilter = new FlightsFilter(new TimeOnLandLess(2));
        adjustedFlights = flightsFilter.filter(flights);
        printFlights(adjustedFlights);

        //возможные комбинации фильтров
        list.add(new FlightsBeforeNow());
        list.add(new FlightHasSegmentWithArrivalBeforeDeparture());
        list.add(new TimeOnLandLess(2));
        flightsFilter = new FlightsFilter(list);
        adjustedFlights = flightsFilter.filter(flights);
        printFlights(adjustedFlights);

    }

    private static void printFlights(List<Flight> flights) {
        for(Flight flight: flights) {
            System.out.println(flight);
        }
    }
}
