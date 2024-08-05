package project.vm.codegen;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private Map<Integer,Object> heap;
    private int nextFreeAddress;

    public  Memory(){
        this.heap = new HashMap<>();
        this.nextFreeAddress = 0;
    }

    public int allocate(int size){
        int address = nextFreeAddress;
        nextFreeAddress+=size;
        return address;
    }
    public void store(int address, Object value) {
        heap.put(address, value);
    }

    public Object load(int address) {
        return heap.get(address);
    }

    public void printHeap() {
        System.out.println("Heap:");
        heap.forEach((address, value) -> System.out.println("Address " + address + ": " + value));
    }

}
