package pharmacy;

import primitives.PharmacyDate;
import primitives.PharmacyItem;

public class TreePharmacy {
    BinaryNode head;

    public TreePharmacy() {

    }

    public void insert(PharmacyItem pharmacyItem) {
        if(head == null) {
            head = new BinaryNode(pharmacyItem, null, null);
            return;
        }

        BinaryNode currentNode = head;

        while(currentNode != null) {
            int difference = pharmacyItem.compareTo(currentNode.getValue());

            if (difference > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new BinaryNode(pharmacyItem, null, null));
                    currentNode = null;
                }
            } else if (difference < 0) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new BinaryNode(pharmacyItem, null, null));
                    currentNode = null;
                }
            } else {
                currentNode.getValue().addQuantity(pharmacyItem.getQuantity());
                currentNode = null;
            }
        }
    }

    public void remove(PharmacyItem pharmacyItem) {
        
    }

    public void removeAll(String medication) {
        //TODO Pas nécessaire pour
    }

    public void removeAll(PharmacyDate pharmacyDate) {
        //TODO Enlever tous les éléments ayant cette data
    }

    public void removeInferior(PharmacyDate pharmacyDate) {
        //TODO Enlever tous les éléments ayant une data inférieure à ça
    }

    public String toString() {
        return head.toString();
    }
}
