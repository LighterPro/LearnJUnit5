package com.healthycoderapp;

public class ActivityCalculator {
    private static final int WORKOUT_DURATION_MIN = 45;

    public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkouts) {
        if (weeklyCardioMin < 0 || weeklyWorkouts < 0) {
            throw new RuntimeException("Input below 0");
        }

        int totalMinutes = weeklyCardioMin + weeklyWorkouts * WORKOUT_DURATION_MIN;
        double averageDailyActivityMins = totalMinutes / 7.0;

        if (averageDailyActivityMins < 20) {
            return "bad";
        } else if (averageDailyActivityMins >= 20 & averageDailyActivityMins < 40) {
            return "average";
        } else {
            return "good";
        }
    }
}
