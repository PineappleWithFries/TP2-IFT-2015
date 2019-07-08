package primitives;

public class PharmacyDate implements Comparable<PharmacyDate> {


    //Attributs de la classe. On y retrouve egalement deux PharmacyDate que l'on utilisera pour s'assurer que les
    //dates entrees respecteront les limites (2000-01-01) et (2020-01-01)

    public static final PharmacyDate BEGIN_DATE= new PharmacyDate(2000,Month.January,1);
    public static final PharmacyDate FINAL_DATE= new PharmacyDate(2020,Month.January,1);
    private int day;
    private int year;
    private Month month;

    //Un enum pour les differents mois de l'annee, ainsi que des fonctions pour assurer que leurs nbres de jours sont
    //respectees

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
        public boolean leapMonth;

        Month(int numberOfDays, boolean leapMonth) {
            this.numberOfDays = numberOfDays;
            this.leapMonth = leapMonth;
        }

        public boolean isDateValid(int day, int year) {
            if(this.leapMonth && ((year % 4 == 0) || (year % 100 == 0 && !(year % 400 == 0)))) {
                return (day > 0) && (day <= numberOfDays + 1);
            }

            return (day > 0) && (day <= numberOfDays);
        }
    }

    //Constructeur de la classe. Nous allons passer en parametre des inputs deja "parser"

    public PharmacyDate(int year, Month month, int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }

    public PharmacyDate(String string) {
        String[] parsedString = string.split("-");
        year = Integer.parseInt(parsedString[0]);
        month = Month.values()[Integer.parseInt(parsedString[1]) - 1];
        day = Integer.parseInt(parsedString[2]);
    }

    //Fonction pour assurer que la PharmacyDate est de format valide
    public boolean isValid(){

        int beginComparison = this.compareTo(BEGIN_DATE);
        int finalComparison = this.compareTo(FINAL_DATE);

        return this.month.isDateValid(this.day,this.year) && beginComparison>=0 && finalComparison<=0;

    }

    //Fonction qui determine si deux dates sont egales

    @Override

    public boolean equals(Object o){
        return (o instanceof PharmacyDate) && (this.year==((PharmacyDate) o).year && this.day==((PharmacyDate) o).day
                && this.month==((PharmacyDate) o).month);
    }

    //Fonction qui permet de comparer si une PharmacyDate passe en entree est superieure, egale ou inferieure

    public int compareTo(PharmacyDate pharmacyDate) {

        int yearDifference = this.year-pharmacyDate.year;
        int monthDifference = this.month.compareTo(pharmacyDate.month);
        int dayDifference = this.day-pharmacyDate.day;

        if (yearDifference !=0){
            return yearDifference;
        } else if (monthDifference != 0) {
            return monthDifference;
        } else {
            return dayDifference;
        }

    }
}
