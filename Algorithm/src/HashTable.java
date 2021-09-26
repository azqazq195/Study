import java.util.Objects;

public class HashTable {
    public static void main(String[] args) {
        String name = "DaveLee";
        System.out.println(name.charAt(0));
        System.out.println((int) name.charAt(0));
        System.out.println((int) name.charAt(0) % 20);
        System.out.println();

        MyHash mainObject = new MyHash(20);
        mainObject.saveData("DaveLee", "01088883333");
        mainObject.saveData("fun-coding", "01099998888");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(mainObject.getData("David"));

    }
}

class MyHash {
    public Slot[] hashTable;

    public MyHash(Integer size) {
        this.hashTable = new Slot[size];
    }

    public static class Slot {
        String key;
        String value;

        Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public Integer hashFunc(String key) {
        return (int) (key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (Objects.equals(this.hashTable[address].key, key)) {
                this.hashTable[address].value = value;
            } else {
                int currentAddress = address + 1;
                while (this.hashTable[currentAddress] != null) {
                    if (Objects.equals(this.hashTable[currentAddress].key, key)) {
                        this.hashTable[currentAddress].value = value;
                        return true;
                    } else {
                        currentAddress++;
                        if (currentAddress >= this.hashTable.length) {
                            return false;
                        }
                    }
                }
                this.hashTable[currentAddress] = new Slot(key, value);
                return true;
            }
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if(this.hashTable[address].key == key) {
                return this.hashTable[address].value;
            } else {
                int currentAddress = address + 1;
                while(this.hashTable[currentAddress] != null) {
                    if(this.hashTable[currentAddress].key == key) {
                        return this.hashTable[currentAddress].value;
                    } else {
                        currentAddress++;
                        if(currentAddress >= this.hashTable.length) {
                            return null;
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }
}
