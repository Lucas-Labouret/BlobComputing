package validation;

/**
 * A global validator validates global properties over the fields of a Blob program.
 * For example, whether a BoolV is false everywhere.
 * This is not a Blob object because global validation violates the locality principle of the model.
 * <p>
 * A blocking validator is a validator that, if it fails, indicates that Blob program execution should be stopped.
 */
public abstract class GlobalValidator {
    private static boolean block = false;
    /** Indicates whether a blocking validator has failed, then reset the flag. */
    public static boolean shouldBlock() {
        boolean shouldBlock = block;
        block = false;
        return shouldBlock;
    }

    public GlobalValidator(boolean isBlocking) {
        this.isBlocking = isBlocking;
    }

    /** Indicates whether the validator is blocking. */
    private boolean isBlocking = false;

    /** @return true if the validation passed, false otherwise. */
    protected abstract boolean _validate();

    /**
     * @return true if the validation passed, false otherwise.
     *         If the validator is blocking and the validation failed, set the block flag to true.
     */
    public boolean validate() {
        boolean result = _validate();
        // If the validator is blocking and the validation failed, set the block flag to true.
        // If the block flag is already true, it remains true regardless of the current validation result.
        block = block || (isBlocking && !result);
        return result;
    }
}