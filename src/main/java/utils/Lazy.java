package utils;

import java.util.function.Supplier;

/**
 * A class that represents a lazily evaluated value. The value is computed only when it is first accessed.
 * @param <T> the type of the value
 */
public class Lazy<T> {
    private final Supplier<T> supplier;
    private T value;

    public Lazy(Supplier<T> supplier) { this.supplier = supplier; }

    public T get() {
        if (value == null) value = supplier.get();
        return value;
    }
}
