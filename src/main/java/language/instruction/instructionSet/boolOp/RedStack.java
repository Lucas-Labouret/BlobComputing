package language.instruction.instructionSet.boolOp;

import language.instruction.instructionSet.BasicInstruction;
import language.obj.field.boolField.BoolE;
import language.obj.field.boolField.BoolF;
import language.obj.field.boolField.BoolV;
import language.ref.field.boolField.*;

/** Performs a stack reduction from a BoolVe to a BoolV with neutral element 0. */
class RedStackVe0 implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolVRef[] dest;

    /** Creates a new RedStackVe0. */
    public RedStackVe0(BoolVeRef orig, BoolVRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = BoolV.redStackV0(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolVe to a BoolV with neutral element 1. */
class RedStackVe1 implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolVRef[] dest;

    /** Creates a new RedStackVe0. */
    public RedStackVe1(BoolVeRef orig, BoolVRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = BoolV.redStackV1(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolVf to a BoolV with neutral element 0. */
class RedStackVf0 implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolVRef[] dest;

    /** Creates a new RedStackVf0. */
    public RedStackVf0(BoolVfRef orig, BoolVRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = BoolV.redStackV0(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolVf to a BoolV with neutral element 1. */
class RedStackVf1 implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolVRef[] dest;

    /** Creates a new RedStackVf0. */
    public RedStackVf1(BoolVfRef orig, BoolVRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolV[] stack = BoolV.redStackV1(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEv to a BoolE with neutral element 0. */
class RedStackEv0 implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolERef[] dest;

    /** Creates a new RedStackVf0. */
    public RedStackEv0(BoolEvRef orig, BoolERef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = BoolE.redStackE0(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEv to a BoolE with neutral element 1. */
class RedStackEv1 implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolERef[] dest;

    /** Creates a new RedStackEv1. */
    public RedStackEv1(BoolEvRef orig, BoolERef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = BoolE.redStackE1(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEf to a BoolE with neutral element 0. */
class RedStackEf0 implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolERef[] dest;

    /** Creates a new RedStackEf0. */
    public RedStackEf0(BoolEfRef orig, BoolERef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = BoolE.redStackE0(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolEf to a BoolE with neutral element 1. */
class RedStackEf1 implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolERef[] dest;

    /** Creates a new RedStackEf1. */
    public RedStackEf1(BoolEfRef orig, BoolERef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolE[] stack = BoolE.redStackE1(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFv to a BoolF with neutral element 0. */
class RedStackFv0 implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolFRef[] dest;

    /** Creates a new RedStackFv0. */
    public RedStackFv0(BoolFvRef orig, BoolFRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = BoolF.redStackF0(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFv to a BoolF with neutral element 1. */
class RedStackFv1 implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolFRef[] dest;

    /** Creates a new RedStackFv1. */
    public RedStackFv1(BoolFvRef orig, BoolFRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = BoolF.redStackF1(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFe to a BoolF with neutral element 0. */
class RedStackFe0 implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolFRef[] dest;

    /** Creates a new RedStackFe0. */
    public RedStackFe0(BoolFeRef orig, BoolFRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = BoolF.redStackF0(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

/** Performs a stack reduction from a BoolFe to a BoolF with neutral element 1. */
class RedStackFe1 implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolFRef[] dest;

    /** Creates a new RedStackFe1. */
    public RedStackFe1(BoolFeRef orig, BoolFRef[] dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        BoolF[] stack = BoolF.redStackF1(orig.get());
        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid stack reduction: expected " + dest.length + " levels, but got " + stack.length
        );
        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}

