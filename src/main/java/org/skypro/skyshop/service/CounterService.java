package org.skypro.skyshop.service;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private int count = 0;

    public void countdown() {
        count++;
    }

    public int getCount() {
        return count;
    }
}