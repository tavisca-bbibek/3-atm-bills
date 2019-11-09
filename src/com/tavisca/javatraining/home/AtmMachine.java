package com.tavisca.javatraining.home;

import com.tavisca.javatraining.home.exception.BillCountExceedsTwentyException;
import com.tavisca.javatraining.home.exception.InsufficientCashException;
import com.tavisca.javatraining.home.exception.InvalidAmountException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AtmMachine {
    Map<Integer, Integer> billMap;

    public AtmMachine() {
        billMap = new HashMap<>();
        billMap.put(2000, 50);
        billMap.put(500, 50);
        billMap.put(100, 50);
    }

    public double getTotalCash() {
        return billMap.keySet().stream()
                .collect(Collectors.summingInt(bill -> bill * billMap.get(bill)));
    }

    public Map<Integer, Integer> getBills(double amount) throws InvalidAmountException, InsufficientCashException {
        if (!isMultipleOfHundred(amount))
            throw new InvalidAmountException(amount + " is not multiple of 100");

        if (getTotalCash() < amount)
            throw new InsufficientCashException();

        Map<Integer, Integer> bundle = new HashMap<>();
        var remaining = new Object() {
            int toCash = (int) amount;
        };

        billMap.forEach(
                (bill, count) -> {
                    int noOfBills = remaining.toCash / bill;
                    if (count < noOfBills)
                        noOfBills = count;
                    remaining.toCash = (remaining.toCash - bill * noOfBills);
                    bundle.put(bill, noOfBills);
                }
        );

        return bundle;
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientCashException {
        Map<Integer, Integer> bills = getBills(amount);
        removeBills(bills);
    }

    public void withdrawTwentyBills(double amount) throws InvalidAmountException, BillCountExceedsTwentyException, InsufficientCashException {
        Map<Integer, Integer> bundle = getBills(amount);
        long noOfBills = bundle.values()
                .stream().reduce(0, Integer::sum);
        if (noOfBills > 20)
            throw new BillCountExceedsTwentyException();
        removeBills(bundle);
    }

    public void removeBills(Map<Integer, Integer> bundle) {
        bundle.forEach((bill, count) -> billMap.put(bill, billMap.get(bill) - count));
    }

    private boolean isMultipleOfHundred(double number) {
        return number % 100 == 0;
    }
}
