package pharmacy;

import primitives.Condition;
import primitives.PharmacyDate;
import primitives.PharmacyItem;

public class TreePharmacy {
    private BinaryNode head;
    private PharmacyDate currentDate;

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

            if (difference < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new BinaryNode(pharmacyItem, null, null));
                    currentNode = null;
                }
            } else if (difference > 0) {
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

    public boolean remove(PharmacyItem pharmacyItem) {
        BinaryNode previousNode = null;
        BinaryNode currentNode = head;

        while(currentNode != null) {
            int difference = pharmacyItem.compareTo(currentNode.getValue());

            if(difference < 0) {
                /*
                 * Si la difference avec la node presentement selectionnee et l'item est inferieure à 0,
                 * alors il faut se diriger vers la node de gauche et changer previousNode.
                 */

                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            } else if(difference > 0) {
                /*
                 * Si la difference avec la node présentement selectionnee et l'item est inferieure à 0,
                 * alors il faut se diriger vers la node de droite et changer previousNode.
                 */

                previousNode = currentNode;
                currentNode = currentNode.getRight();
            } else {
                /*
                 * Si la difference est nulle, alors c'est la node actuelle qu'il faut supprimer.
                 */

                if(currentNode.getLeft() == null && currentNode.getRight() == null) {
                    //Si la node n'as pas d'enfant, on peut seulement l'enlever.

                    if(previousNode == null) {
                        head = null;
                        return true;
                    } else if(currentNode == previousNode.getLeft()) {
                        previousNode.setLeft(null);
                        return true;
                    } else if(currentNode == previousNode.getRight()) {
                        previousNode.setRight(null);
                        return true;
                    }
                } else if(currentNode.getLeft() == null) {
                    //Si la node a un seul enfant, à droite.

                    if(previousNode == null) {
                        head = currentNode.getRight();
                        return true;
                    } else if(currentNode == previousNode.getLeft()) {
                        previousNode.setLeft(currentNode.getRight());
                        return true;
                    } else if(currentNode == previousNode.getRight()) {
                        previousNode.setRight(currentNode.getRight());
                        return true;
                    }
                } else if(currentNode.getRight() == null) {
                    //Si la node a un seul enfant, à gauche.

                    if(previousNode == null) {
                        head = currentNode.getLeft();
                        return true;
                    } else if(currentNode == previousNode.getLeft()) {
                        previousNode.setLeft(currentNode.getLeft());
                        return true;
                    } else if(currentNode == previousNode.getRight()) {
                        previousNode.setRight(currentNode.getLeft());
                        return true;
                    }
                } else {
                    //Si la node a deux enfants, on pleure :(
                    BinaryNode leftCurrentChild = currentNode.getLeft();

                    BinaryNode previousRightMost = null;
                    BinaryNode leftRightMost = leftCurrentChild;
                    while(leftRightMost.getRight() != null) {
                        previousRightMost = leftRightMost;
                        leftRightMost = leftRightMost.getRight();
                    }

                    if(previousRightMost != null) {
                        previousRightMost.setRight(leftRightMost.getLeft());

                        leftRightMost.setLeft(currentNode.getLeft());
                        leftRightMost.setRight(currentNode.getRight());

                        if(previousNode == null) {
                            head = leftRightMost;
                            return true;
                        } else if(currentNode == previousNode.getRight()) {
                            previousNode.setRight(leftRightMost);
                            return true;
                        } else if(currentNode == previousNode.getLeft()) {
                            previousNode.setLeft(leftRightMost);
                            return true;
                        }
                    } else {
                        leftRightMost.setRight(currentNode.getRight());

                        if(previousNode == null) {
                            head = leftRightMost;
                            return true;
                        } else if(currentNode == previousNode.getRight()) {
                            previousNode.setRight(leftRightMost);
                            return true;
                        } else if(currentNode == previousNode.getLeft()) {
                            previousNode.setLeft(leftRightMost);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public PharmacyItem removeCondition(Condition<PharmacyItem> condition) {
        BinaryNode previousNode = null;
        BinaryNode currentNode = head;

        while(currentNode != null) {
            int difference = condition.respectsCondition(currentNode.getValue());

            if(difference < 0) {
                /*
                 * Si la difference avec la node presentement selectionnee et l'item est inferieure à 0,
                 * alors il faut se diriger vers la node de gauche et changer previousNode.
                 */

                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            } else if(difference > 0) {
                /*
                 * Si la difference avec la node présentement selectionnee et l'item est inferieure à 0,
                 * alors il faut se diriger vers la node de droite et changer previousNode.
                 */

                previousNode = currentNode;
                currentNode = currentNode.getRight();
            } else {
                /*
                 * Si la difference est nulle, alors c'est la node actuelle qu'il faut supprimer.
                 */

                if(currentNode.getLeft() == null && currentNode.getRight() == null) {
                    //Si la node n'as pas d'enfant, on peut seulement l'enlever.

                    if(previousNode == null) {
                        head = null;
                        return currentNode.getValue();
                    } else if(currentNode == previousNode.getLeft()) {
                        previousNode.setLeft(null);
                        return currentNode.getValue();
                    } else if(currentNode == previousNode.getRight()) {
                        previousNode.setRight(null);
                        return currentNode.getValue();
                    }
                } else if(currentNode.getLeft() == null) {
                    //Si la node a un seul enfant, à droite.

                    if(previousNode == null) {
                        head = currentNode.getRight();
                        return currentNode.getValue();
                    } else if(currentNode == previousNode.getLeft()) {
                        previousNode.setLeft(currentNode.getRight());
                        return currentNode.getValue();
                    } else if(currentNode == previousNode.getRight()) {
                        previousNode.setRight(currentNode.getRight());
                        return currentNode.getValue();
                    }
                } else if(currentNode.getRight() == null) {
                    //Si la node a un seul enfant, à gauche.

                    if(previousNode == null) {
                        head = currentNode.getLeft();
                        return currentNode.getValue();
                    } else if(currentNode == previousNode.getLeft()) {
                        previousNode.setLeft(currentNode.getLeft());
                        return currentNode.getValue();
                    } else if(currentNode == previousNode.getRight()) {
                        previousNode.setRight(currentNode.getLeft());
                        return currentNode.getValue();
                    }
                } else {
                    //Si la node a deux enfants, on pleure :(
                    BinaryNode leftCurrentChild = currentNode.getLeft();

                    BinaryNode previousRightMost = null;
                    BinaryNode leftRightMost = leftCurrentChild;
                    while(leftRightMost.getRight() != null) {
                        previousRightMost = leftRightMost;
                        leftRightMost = leftRightMost.getRight();
                    }

                    if(previousRightMost != null) {
                        previousRightMost.setRight(leftRightMost.getLeft());

                        leftRightMost.setLeft(currentNode.getLeft());
                        leftRightMost.setRight(currentNode.getRight());

                        if(previousNode == null) {
                            head = leftRightMost;
                            return currentNode.getValue();
                        } else if(currentNode == previousNode.getRight()) {
                            previousNode.setRight(leftRightMost);
                            return currentNode.getValue();
                        } else if(currentNode == previousNode.getLeft()) {
                            previousNode.setLeft(leftRightMost);
                            return currentNode.getValue();
                        }
                    } else {
                        leftRightMost.setRight(currentNode.getRight());

                        if(previousNode == null) {
                            head = leftRightMost;
                            return currentNode.getValue();
                        } else if(currentNode == previousNode.getRight()) {
                            previousNode.setRight(leftRightMost);
                            return currentNode.getValue();
                        } else if(currentNode == previousNode.getLeft()) {
                            previousNode.setLeft(leftRightMost);
                            return currentNode.getValue();
                        }
                    }
                }
            }
        }

        return null;
    }

    public int retrieve(final int quantity, final String name, final PharmacyDate maxDate) {
        int currentQuantity = 0;

        Condition<PharmacyItem> condition = new Condition<PharmacyItem>() {
            @Override
            public int respectsCondition(PharmacyItem type) {
                int differenceTemps = maxDate.compareTo(type.getDate());

                if (differenceTemps > 0) {
                    return differenceTemps;
                } else {
                    return -name.compareTo(type.getMedication());
                }
            }
        };

        PharmacyItem nextItem = null;
        while(currentQuantity < quantity) {
            nextItem = this.removeCondition(condition);

            if(nextItem == null) {
                break;
            } else {
                currentQuantity += nextItem.getQuantity();
            }
        }

        if(nextItem != null && currentQuantity > quantity) {
            System.out.println(new PharmacyItem(nextItem.getDate(), nextItem.getMedication(), currentQuantity - quantity));
            this.insert(new PharmacyItem(nextItem.getDate(), nextItem.getMedication(), currentQuantity - quantity));
        }

        return currentQuantity - quantity;
    }

    public int removeAllCondition(Condition<PharmacyItem> condition) {
        int removed = 0;

        while(removeCondition(condition) != null) {
            removed++;
        }

        return removed;
    }

    public PharmacyDate getDate() {
        return currentDate;
    }

    public void setDate(PharmacyDate pharmacyDate) {
        //TODO Supprimer les médécines avec temps d'expiration
        this.currentDate = pharmacyDate;
    }

    public String toString() {
        return head.toString();
    }
}
