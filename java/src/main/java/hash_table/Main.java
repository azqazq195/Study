package hash_table;

public class Main {

    public static void main(String[] args) {
        linearProbingTable();
    }

    public static void chainingTable() {
        HashTable<String, String> chainingHashTable = new ChainingHashTable<>(10);
        chainingHashTable.put("1", "010-1111-1111");
        chainingHashTable.put("2", "010-2222-2222");
        chainingHashTable.put("3", "010-3333-3333");
        chainingHashTable.put("4", "010-4444-4444");
        chainingHashTable.put("5", "010-5555-5555");
        chainingHashTable.put("6", "010-6666-6666");
        chainingHashTable.put("7", "010-7777-7777");
        chainingHashTable.put("8", "010-8888-8888");
        chainingHashTable.put("9", "010-9999-9999");
        chainingHashTable.put("10", "010-0000-0000");
        chainingHashTable.put("11", "011-1111-1111");

        System.out.println(chainingHashTable);
        System.out.println();
    }

    public static void linearProbingTable() {
        HashTable<String, String> linearProbingHashTable = new LinearProbingHashTable<>(10);
        linearProbingHashTable.put("1", "010-1111-1111");
        linearProbingHashTable.put("2", "010-2222-2222");
        linearProbingHashTable.put("3", "010-3333-3333");
        linearProbingHashTable.put("4", "010-4444-4444");
        linearProbingHashTable.put("5", "010-5555-5555");
        linearProbingHashTable.put("6", "010-6666-6666");
        linearProbingHashTable.put("7", "010-7777-7777");
        linearProbingHashTable.put("8", "010-8888-8888");
        linearProbingHashTable.put("9", "010-9999-9999");
        linearProbingHashTable.put("10", "010-0000-0000");

        System.out.println(linearProbingHashTable);
        System.out.println();
    }
}
