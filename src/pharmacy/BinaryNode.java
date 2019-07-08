package pharmacy;

import primitives.PharmacyDate;
import primitives.PharmacyItem;

public class BinaryNode {
    public final static String PRINT_BUFFER = "|";
    private BinaryNode left, right;
    private PharmacyItem value;

    public BinaryNode(PharmacyItem value, BinaryNode left, BinaryNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public PharmacyItem getValue() {
        return value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }



    public String toString() {
        return "" + value + "\n" + (left != null ? left.toString() : "") + (right != null ? right.toString() : "");
        //return "" + value + "\n" + (left != null ? left.toString(1) : "") + (right != null ? right.toString(1) : "");
    }

    public String toString(int depth) {
        String buffer = "";

        for(int i = 0; i < depth; i++) {
            buffer += PRINT_BUFFER;
        }

        if(depth > 100)
            return "Probable recursive definition in tree\n";

        return "" + buffer + value + "\n" +
                (left != null ? left.toString(depth + 1) : "") +
                (right != null ? right.toString(depth + 1) : "");
    }
}
