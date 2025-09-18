package cis3334.java_heartrate_start;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Heartrate {
    public Integer pulse;       // actual rate in beats per minute
    public Integer age;         // age when heart rate measurement was taken
    public Double maxHeartRate; // calculated maximum rate based on age
    public Double percent;      // percent of maximum rate
    public Integer range;       // which range this heart rate is in, used as index into arrays below

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @Ignore
    final String rangeNames[] = {"Resting", "Moderate", "Endurance", "Aerobic", "Anaerobic", "Red zone"};
    @Ignore
    final String rangeDescriptions[] = {
            "In active or resting",
            "Weight maintenance and warm up",
            "Fitness and fat burning",
            "Cardio training and endurance",
            "Hardcore interval training",
            "Maximum Effort"
    };
    @Ignore
    final Double rangeBounds[] = {.50, .60, .70, .80, .90, 1.00};

    public Heartrate(Integer pulse, Integer age) {
        this.pulse = pulse;
        this.age = age;
        calcHeartRange();
    }

    public int getPulse() {
        return pulse != null ? pulse : 0;
    }

    public int getAge() {
        return age != null ? age : 0;
    }

    public int getId() {
        return id != null ? id : 0;
    }

    /**
     * Calculate the maximum heartrate and percent of max using CDC guidelines
     * @return range index (0-5)
     */
    public Integer calcHeartRange() {
        if (pulse == null || age == null) {
            range = 0; // default to resting
            maxHeartRate = 220.0;
            percent = 0.0;
            return range;
        }

        maxHeartRate = 220.0 - age;
        percent = pulse / maxHeartRate;

        for (int i = 0; i < rangeBounds.length; i++) {
            if (percent < rangeBounds[i]) {
                range = i;
                return range;
            }
        }
        // assign last range if none matched
        range = rangeNames.length - 1;
        return range;
    }

    public String getRangeName() {
        if (range == null) {
            calcHeartRange();
        }
        return rangeNames[range];
    }

    public String getRangeDescription() {
        if (range == null) {
            calcHeartRange();
        }
        return rangeDescriptions[range];
    }

    @Override
    public String toString() {
        return "Pulse of " + getPulse() + " - Cardio level: " + getRangeName();
    }
}
