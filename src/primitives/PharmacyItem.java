package primitives;

public class PharmacyItem implements Comparable<PharmacyItem> {

    private PharmacyDate date;
    private String medication;
    private int quantity;

    //Constructeur pour notre classe. Les proprietes sont inserees directement a la creation

    public PharmacyItem( PharmacyDate date, String medication, int quantity){

        this.date=date;
        this.medication=medication;
        this.quantity=quantity;
    }

    //On va organiser les nodes de l'arbre selon deux trajets. En premier par la date et en cas d'une egalite de
    //date, on va trier par l'ordre lexicographique des noms des medicaments

    public int compareTo(PharmacyItem pharmacyItem) {
        int differenceTemps = this.date.compareTo(pharmacyItem.date);

        if (differenceTemps!= 0) {
            return differenceTemps;
        } else {
            return this.medication.compareTo(pharmacyItem.medication);
        }
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof PharmacyItem) &&
                ((PharmacyItem) o).date.equals(this.date) &&
                ((PharmacyItem) o).medication.equals(this.medication);
    }

    public String getMedication() {
        return this.medication;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity){
        this.quantity -= quantity;
    }

    public PharmacyDate getDate() {
        return this.date;
    }

    public boolean isValid() {

        return quantity >0 ? true : false;
    }

    public String toString() {
        return medication + " " + quantity + " " + date;
    }
}
