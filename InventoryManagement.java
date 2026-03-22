import java.util.*;
public class InventoryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many products do you want to add? ");
        int n = sc.nextInt();
        sc.nextLine();

        List<String> arrayListInventory = new ArrayList<>();
        LinkedList<String> linkedListInventory = new LinkedList<>();
        Set<String> hashSetInventory = new HashSet<>();
        Set<String> treeSetInventory = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter product: ");
            String product = sc.nextLine();
            arrayListInventory.add(product);
            linkedListInventory.add(product);

            boolean addedHash = hashSetInventory.add(product);
            if (!addedHash) {
                System.out.println("Duplicate '" + product + "' ignored in HashSet.");
            }

            boolean addedTree = treeSetInventory.add(product);
            if (!addedTree) {
                System.out.println("Duplicate '" + product + "' ignored in TreeSet.");
            }
        }

        System.out.println("\n--- ArrayList Inventory ---");
        System.out.println("Initial: " + arrayListInventory);
        if (!arrayListInventory.isEmpty()) {
            System.out.println("Removing first: " + arrayListInventory.get(0));
            arrayListInventory.remove(0);
        }
        System.out.println("After removal: " + arrayListInventory);
        System.out.println("Iterating:");
        for (String p : arrayListInventory) System.out.println(p);

        System.out.println("\n--- LinkedList Inventory ---");
        System.out.println("Initial: " + linkedListInventory);
        if (!linkedListInventory.isEmpty()) {
            System.out.println("Removing last: " + linkedListInventory.getLast());
            linkedListInventory.removeLast();
        }
        System.out.println("After removal: " + linkedListInventory);
        System.out.println("Iterating:");
        for (String p : linkedListInventory) System.out.println(p);

        System.out.println("\n--- HashSet Inventory (no duplicates) ---");
        System.out.println("Initial: " + hashSetInventory);
        if (!hashSetInventory.isEmpty()) {
            String first = hashSetInventory.iterator().next();
            System.out.println("Removing: " + first);
            hashSetInventory.remove(first);
        }
        System.out.println("After removal: " + hashSetInventory);
        System.out.println("Iterating:");
        for (String p : hashSetInventory) System.out.println(p);

        System.out.println("\n--- TreeSet Inventory (sorted, no duplicates) ---");
        System.out.println("Initial: " + treeSetInventory);
        if (!treeSetInventory.isEmpty()) {
            String first = treeSetInventory.iterator().next();
            System.out.println("Removing: " + first);
            treeSetInventory.remove(first);
        }
        System.out.println("After removal: " + treeSetInventory);
        System.out.println("Iterating:");
        for (String p : treeSetInventory) System.out.println(p);

        sc.close();
    }
}