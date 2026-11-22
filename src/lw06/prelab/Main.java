package lw06.prelab;
import java.io.File;
import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

public class Main {

    static Node insert(Node root, int data) {
        if (root == null) return new Node(data);
        if (data < root.data) root.left = insert(root.left, data);
        else if (data > root.data) root.right = insert(root.right, data);
        return root;
    }

    static boolean search(Node root, int target) {
        if (root == null) return false;
        if (target == root.data) return true;
        if (target < root.data) return search(root.left, target);
        return search(root.right, target);
    }

    static void preorder(Node root) {
        if (root == null) return;
        System.out.print(" " + root.data);
        preorder(root.left);
        preorder(root.right);
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(" " + root.data);
        inorder(root.right);
    }

    static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(" " + root.data);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("src/lw06/prelab/treenumbers.txt"));
        String[] values = sc.nextLine().trim().split(" ");
        int target = Integer.parseInt(sc.nextLine().trim());

        Node root = null;
        for (String v : values) {
            root = insert(root, Integer.parseInt(v));
        }

        System.out.print("Preorder :");
        preorder(root);
        System.out.println();

        System.out.print("Inorder :");
        inorder(root);
        System.out.println();

        System.out.print("Postorder :");
        postorder(root);
        System.out.println();

        System.out.println("Search " + target + " : " + search(root, target));
    }
}