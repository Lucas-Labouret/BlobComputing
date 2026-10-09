package language.cache;

import language.field.boolField.BoolField;
import language.instruction.Instruction;
import language.instruction.Procedure;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Stores the states of an execution at any given step count as CacheEntry objects,
 * and allows for retrieving and restoring those states later.
 * <p>
 * Entries are stored in a linked list, sorted by step count in descending order.
 */
public class Cache {
    /**
     * Represents a single cache entry,
     * storing the state of all registered fields and procedures at a given step count.
     */
    public static class CacheEntry {
        /** The step count at which this cache entry was created. */
        public final long stepCount;
        /** The next cache entry in the linked list, or null if this is the last entry. */
        private CacheEntry next;
        /** A mapping of registered fields to their cached values at this step count. */
        private final HashMap<BoolField<?>, BoolField<?>> valueCache;
        /** A mapping of registered procedures to their instruction pointers at this step count. */
        private final HashMap<Procedure, Integer> instrPtrCache;

        private CacheEntry(
                long stepCount,
                CacheEntry next,
                HashMap<BoolField<?>, BoolField<?>> valueCache,
                HashMap<Procedure, Integer> instrPtrCache
        ){
            this.stepCount = stepCount;
            this.next = next;
            this.valueCache = valueCache;
            this.instrPtrCache = instrPtrCache;
        }
    }

    /** A set of all registered fields to be cached. */
    private static final HashSet<BoolField<?>> fields = new HashSet<>();
    /** A set of all registered procedures to be cached. */
    private static final HashSet<Procedure> instrPtrs = new HashSet<>();

    /** @param field The field to register for caching. */
    public static void register(BoolField<?> field) {
        new Throwable("registration stack").printStackTrace();
        fields.add(field);
    }
    /** @param procedure The procedure to register for caching. */
    public static void register(Procedure procedure) { instrPtrs.add(procedure); }

    /** The top of the cache linked list, representing the most recent cache entry. */
    private CacheEntry top = null;

    /**
     * Creates a new cache entry and adds it to the cache, sorted by step count.
     *
     * @param stepCount The step count for the new cache entry.
     *                  If an entry with the same step count already exists, it will be overridden.
     * @return The new cache entry.
     */
    public CacheEntry push(long stepCount) {
        System.out.println(fields.size());
        HashMap<BoolField<?>, BoolField<?>> valueCache = new HashMap<>();
        for (BoolField<?> field : fields) valueCache.put(field, field.cache());

        HashMap<Procedure, Integer> instrPtrCache = new HashMap<>();
        for (Procedure procedure : instrPtrs) instrPtrCache.put(procedure, procedure.getInstrPtr());

        // Find the cache entries s.t. current.stepCount <= stepCount < previous.stepCount (if they exist)
        CacheEntry previous = null;
        CacheEntry current = top;
        while (current != null && current.stepCount > stepCount) {
            previous = current;
            current = current.next;
        }

        // There is already an entry for stepCount (we override) and it is at the top of the cache
        if (current != null && current.stepCount == stepCount && previous == null) {
            top = new CacheEntry(stepCount, current.next, valueCache, instrPtrCache);
            return top;
        }

        // There is already an entry for stepCount (we override) and it is in the middle of the cache
        if (current != null && current.stepCount == stepCount) {
            previous.next = new CacheEntry(stepCount, current.next, valueCache, instrPtrCache);
            return previous.next;
        }

        // New stepCount entry, we insert it in the right place in the cache
        CacheEntry newEntry = new CacheEntry(stepCount, current, valueCache, instrPtrCache);
        if (previous == null) top = newEntry;
        else previous.next = newEntry;

        return newEntry;
    }

    /**
     * Restores the state of all registered fields and procedures from the given cache entry.
     * @param entry The cache entry to restore from.
     */
    private void applyCacheEntry(CacheEntry entry) {
        for (BoolField field : entry.valueCache.keySet()) {
            field.set(entry.valueCache.get(field));
        }

        for (Procedure procedure : entry.instrPtrCache.keySet()) {
            procedure.setInstrPtr(entry.instrPtrCache.get(procedure));
        }
    }

    /** A flag to avoid concurrent retrievals, which could lead to inconsistent states. */
    private static boolean retrieving = false;
    /**
     * Restores the state of all registered fields and procedures to the state at the given step count.
     * <p>
     * To avoid storing every single step, this method retrieves the closest cached state,
     * then executes the main instruction until the desired step count is reached.
     */
    public void retrieve(long stepCount, Instruction main) {
        if (retrieving) { return; }
        retrieving = true;

        CacheEntry current = top;
        while (current != null && current.stepCount > stepCount) {
            current = current.next;
        }

        if  (current == null)
            throw new IllegalStateException("No cache entry found for step count: " + stepCount);

        applyCacheEntry(current);

        for (long i = 0; i < stepCount - current.stepCount; i++) {
            main.exec();
        }

        retrieving = false;
    }

    /**
     * Restores the state of all registered fields and procedures to the state at the given cache entry.
     * @param entry The cache entry to restore from.
     * @return The step count of the restored cache entry.
     */
    public long retrieve(CacheEntry entry) {
        applyCacheEntry(entry);
        return entry.stepCount;
    }
}
