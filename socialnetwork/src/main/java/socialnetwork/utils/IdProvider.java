package socialnetwork.utils;

public class IdProvider {
    private long nextId;

    public IdProvider(long currentId) {
        this.nextId = currentId + 1;
    }

    public long getUniqueId() {
        if (nextId < 0) {
            throw new IllegalStateException("Out of IDs!");
        }
        long uniqueId = nextId;
        nextId++;
        return uniqueId;
    }

    public long getNextId(){
        return nextId;
    }
}
