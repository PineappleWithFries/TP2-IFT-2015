package primitives;

public class PharmacyDate implements Comparable<PharmacyDate> {
    public enum Month {
        January(31, false),
        February(28, true),
        March(31, false),
        April(30, false),
        May(31, false),
        June(30, false),
        July(31, false),
        August(31, false),
        October(30, false),
        November(30, false),
        December(31, false);

        public int numberOfDays;
        public boolean leapDay;

        Month(int numberOfDays, boolean leapDay) {
            this.numberOfDays = numberOfDays;
            this.leapDay = leapDay;
        }

        public boolean isDateValid(int day, int year) {
            if(this.equals(Month.February) && ((year % 4 == 0) || (year % 100 == 0 && !(year % 400 == 0)))) {
                return (day > 0) && (day <= numberOfDays + 1);
            }

            return (day > 0) && (day <= numberOfDays);
        }
    }

    public int compareTo(PharmacyDate pharmacyDate) {
        return -1; //TODO
    }


}
