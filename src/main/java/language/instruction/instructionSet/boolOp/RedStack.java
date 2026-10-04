package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs a stack reduction from a BoolVe to a BoolV with neutral element 0. */
class RedStackVe0 implements BasicInstruction {
    private final BoolVe orig;
    private final BoolV[] dest;

    /** Creates a new RedStackVe0. */
    public RedStackVe0(BoolVe orig, BoolV[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = orig.redStack0();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolVe to a BoolV with neutral element 1. */
class RedStackVe1 implements BasicInstruction {
    private final BoolVe orig;
    private final BoolV[] dest;

    /** Creates a new RedStackVe0. */
    public RedStackVe1(BoolVe orig, BoolV[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = orig.redStack1();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolVf to a BoolV with neutral element 0. */
class RedStackVf0 implements BasicInstruction {
    private final BoolVf orig;
    private final BoolV[] dest;

    /** Creates a new RedStackVf0. */
    public RedStackVf0(BoolVf orig, BoolV[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = orig.redStack0();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolVf to a BoolV with neutral element 1. */
class RedStackVf1 implements BasicInstruction {
    private final BoolVf orig;
    private final BoolV[] dest;

    /** Creates a new RedStackVf0. */
    public RedStackVf1(BoolVf orig, BoolV[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = orig.redStack1();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEv to a BoolE with neutral element 0. */
class RedStackEv0 implements BasicInstruction {
    private final BoolEv orig;
    private final BoolE[] dest;

    /** Creates a new RedStackVf0. */
    public RedStackEv0(BoolEv orig, BoolE[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = orig.redStack0();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEv to a BoolE with neutral element 1. */
class RedStackEv1 implements BasicInstruction {
    private final BoolEv orig;
    private final BoolE[] dest;

    /** Creates a new RedStackEv1. */
    public RedStackEv1(BoolEv orig, BoolE[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = orig.redStack1();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEf to a BoolE with neutral element 0. */
class RedStackEf0 implements BasicInstruction {
    private final BoolEf orig;
    private final BoolE[] dest;

    /** Creates a new RedStackEf0. */
    public RedStackEf0(BoolEf orig, BoolE[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = orig.redStack0();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEf to a BoolE with neutral element 1. */
class RedStackEf1 implements BasicInstruction {
    private final BoolEf orig;
    private final BoolE[] dest;

    /** Creates a new RedStackEf1. */
    public RedStackEf1(BoolEf orig, BoolE[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = orig.redStack1();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFv to a BoolF with neutral element 0. */
class RedStackFv0 implements BasicInstruction {
    private final BoolFv orig;
    private final BoolF[] dest;

    /** Creates a new RedStackFv0. */
    public RedStackFv0(BoolFv orig, BoolF[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = orig.redStack0();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFv to a BoolF with neutral element 1. */
class RedStackFv1 implements BasicInstruction {
    private final BoolFv orig;
    private final BoolF[] dest;

    /** Creates a new RedStackFv1. */
    public RedStackFv1(BoolFv orig, BoolF[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = orig.redStack1();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFe to a BoolF with neutral element 0. */
class RedStackFe0 implements BasicInstruction {
    private final BoolFe orig;
    private final BoolF[] dest;

    /** Creates a new RedStackFe0. */
    public RedStackFe0(BoolFe orig, BoolF[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = orig.redStack0();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFe to a BoolF with neutral element 1. */
class RedStackFe1 implements BasicInstruction {
    private final BoolFe orig;
    private final BoolF[] dest;

    /** Creates a new RedStackFe1. */
    public RedStackFe1(BoolFe orig, BoolF[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = orig.redStack1();
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

