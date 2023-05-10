package com.yc;

import java.util.Arrays;

public class PaymentId {

    public static final int MMB = 3;
    public static final int MB = 20;
    public static final int TB = 10;
    public static final int SB = 10;
    private long lastValue;
    private long sequence;
    private long mm;

    public PaymentId(long mm) {
        this.mm = mm;
    }

    public static void main(String[] args) {
        PaymentId paymentId = new PaymentId(2);
        for (int i = 0; i < 10; i++) {
            long id = paymentId.transactionId(560123, 729);
            System.out.println(id);
            System.out.println(getValueFromId(id, SB, 0));
//            System.out.println(paymentId.getMasterMerchantById(id));
//            System.out.println(paymentId.getMerchantById(id));
//            System.out.println(paymentId.getTerminalById(id));
//            System.out.println(paymentId.getSequence(id));
            System.out.println("================================================");
        }
    }

    public static long getValueFromId(long id, int bitGet, int... subBit) {
        long mask = (1L << bitGet) - 1;
        int totalBit = Arrays.stream(subBit).sum();
        return (id >> totalBit) & mask;
    }

    public long getMasterMerchantById(long id) {
        long mask = (1L << MMB) - 1;
        return (id >> (MB + TB + SB)) & mask;
    }

    public long getMerchantById(long id) {
        long mask = (1 << MB) - 1;
        return (id >> (TB + SB)) & mask;
    }

    public long getTerminalById(long id) {
        long mask = (1 << TB) - 1;
        return (id >> (SB)) & mask;
    }

    public long getSequence(long id) {
        long mask = (1 << SB) - 1;
        return (id) & mask;
    }

    public long transactionId(long m, long t) {
        long id = mm << (MB + TB + SB);
        id |= m << (TB + SB);
        id |= t << (SB);
        if (id == lastValue) {
            sequence++;
        } else {
            lastValue = id;
            sequence = 0;
        }
        id |= sequence;
        System.out.println("MM: " + mm + ", M: " + m + ", T: " + t + ", S: " + sequence);
        return id;
    }
}
