package com.yc;

public class MasterId {

    /*
     *   3 bit cho vùng
     *   7 bit cho tỉnh/thành phố
     *   10 bit cho quận/huyện
     *   14 bit cho phường xã
     * */

    public static final int REGIONS_BIT = 3;
    public static final int PROVINCES_BIT = 7;
    public static final int DISTRICTS_BIT = 10;
    public static final int WARDS_BIT = 15;

    public static void main(String[] args) {
        long id = addressId(6, 64, 635, 24016);
        System.out.println(id);
        System.out.println(getRegionsById(id));
        System.out.println(getProvinceId(id));
        System.out.println(getDistrictId(id));
        System.out.println(getWardId(id));
    }

    public static long getRegionsById(long id) {
        long mask = (1L << REGIONS_BIT) - 1;
        return (id >> (PROVINCES_BIT + DISTRICTS_BIT + WARDS_BIT)) & mask;
    }

    public static long getProvinceId(long id) {
        long mask = (1 << PROVINCES_BIT) - 1;
        return (id >> (DISTRICTS_BIT + WARDS_BIT)) & mask;
    }

    public static long getDistrictId(long id) {
        long mask = (1 << DISTRICTS_BIT) - 1;
        return (id >> (WARDS_BIT)) & mask;
    }

    public static long getWardId(long id) {
        long mask = (1 << WARDS_BIT) - 1;
        long value = (id) & mask;
        return value;
    }

    public static long addressId(long region, long province, long district, long ward) {
        long id = region << (PROVINCES_BIT + DISTRICTS_BIT + WARDS_BIT);
        id |= province << (DISTRICTS_BIT + WARDS_BIT);
        id |= district << (WARDS_BIT);
        id |= ward;
        System.out.println("region: " + region + ", province: " + province + ", district: " + district + ", ward: " + ward);
        return id;
    }
}
