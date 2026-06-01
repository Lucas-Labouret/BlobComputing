package language.cache;

import language.Obj;
import language.Ref;
import language.instruction.Instruction;
import language.instruction.Procedure;

import java.util.HashMap;
import java.util.HashSet;

public class Cache {
    public static class CacheEntry {
        public final long stepCount;
        private CacheEntry next;
        private final HashMap<Ref<?>, Obj> valueCache;
        private final HashMap<Procedure, Integer> instrPtrCache;

        private CacheEntry(
                long stepCount,
                CacheEntry next,
                HashMap<Ref<?>, Obj> valueCache,
                HashMap<Procedure, Integer> instrPtrCache
        ){
            this.stepCount = stepCount;
            this.next = next;
            this.valueCache = valueCache;
            this.instrPtrCache = instrPtrCache;
        }
    }

    private static final HashSet<Ref<?>> valueCache = new HashSet<>();
    private static final HashSet<Procedure> instrPtrCache = new HashSet<>();

    public static void register(Ref<?> ref) { valueCache.add(ref); }
    public static void register(Procedure procedure) { instrPtrCache.add(procedure); }

    private CacheEntry top = null;

    private static boolean caching = false;
    public static boolean isCaching() { return caching; }
    public CacheEntry push(long stepCount) {
        System.out.println("Before : " + Cache.valueCache.size());
        caching = true;

        HashMap<Ref<?>, Obj> valueCache = new HashMap<>();
        for (Ref<?> ref : Cache.valueCache) valueCache.put(ref, ref.get() == null ? null : ref.get().copy());

        HashMap<Procedure, Integer> instrPtrCache = new HashMap<>();
        for (Procedure procedure : Cache.instrPtrCache) instrPtrCache.put(procedure, procedure.getInstrPtr());

        caching = false;
        System.out.println("After : " + Cache.valueCache.size());

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

    private void applyCacheEntry(CacheEntry entry) {
        for (Ref ref : entry.valueCache.keySet()) {
            ref.set(entry.valueCache.get(ref));
        }

        for (Procedure procedure : entry.instrPtrCache.keySet()) {
            procedure.setInstrPtr(entry.instrPtrCache.get(procedure));
        }
    }

    public void retrieve(long stepCount, Instruction main) {
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
    }

    public long retrieve(CacheEntry entry) {
        applyCacheEntry(entry);
        return entry.stepCount;
    }
}
