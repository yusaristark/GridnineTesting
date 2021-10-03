package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class FlightsFilter implements Filter {
    List<Filter> filters;

    //конструктор по умолчанию
    FlightsFilter() {
        filters = new ArrayList<>();
    }

    //конструктор для одного фильтра
    FlightsFilter(Filter filter) {
        this.filters = new ArrayList<>();
        this.filters.add(filter);
    }

    //конструктор для нескольких фильтров
    FlightsFilter(List<Filter> filters) {
        this.filters = filters;
    }

    //применение всех фильтров
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = flights;
        if (filters.isEmpty()) {
            System.out.println("Фильтры отсутствуют");
            return filteredFlights;
        }
        for (Filter filter : filters) {
            filteredFlights = filter.filter(filteredFlights);
        }
        return filteredFlights;
    }
}
