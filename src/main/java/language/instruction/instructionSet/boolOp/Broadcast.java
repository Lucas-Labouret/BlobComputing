package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Broadcasts a BoolV into a BoolVe. */
class BroadcastVe implements BasicInstruction {
	private final BoolV orig;
	private final BoolVe dest;

	/** Creates a new BroadcastVe. */
	public BroadcastVe(BoolV orig, BoolVe dest) {
		this.orig = orig;
		this.dest = dest;
	}

	/** @return true. */
	@Override
	public boolean exec() {
		dest.broadcast(orig);
		return true;
	}
}

/** Broadcasts a BoolV into a BoolVf. */
class BroadcastVf implements BasicInstruction {
    private final BoolV orig;
    private final BoolVf dest;

    /** Creates a new BroadcastVf. */
    public BroadcastVf(BoolV orig, BoolVf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.broadcast(orig);
        return true;
    }
}

/** Broadcasts a BoolE into a BoolEv. */
class BroadcastEv implements BasicInstruction {
    private final BoolE orig;
    private final BoolEv dest;

    /** Creates a new BroadcastEv. */
    public BroadcastEv(BoolE orig, BoolEv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.broadcast(orig);
        return true;
    }
}

/** Broadcasts a BoolE into a BoolEf. */
class BroadcastEf implements BasicInstruction {
    private final BoolE orig;
    private final BoolEf dest;

    /** Creates a new BroadcastEf. */
    public BroadcastEf(BoolE orig, BoolEf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.broadcast(orig);
        return true;
    }
}

/** Broadcasts a BoolF into a BoolFv. */
class BroadcastFv implements BasicInstruction {
    private final BoolF orig;
    private final BoolFv dest;

    /** Creates a new BroadcastFv. */
    public BroadcastFv(BoolF orig, BoolFv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.broadcast(orig);
        return true;
    }
}

/** Broadcasts a BoolF into a BoolFe. */
class BroadcastFe implements BasicInstruction {
    private final BoolF orig;
    private final BoolFe dest;

    /** Creates a new BroadcastFe. */
    public BroadcastFe(BoolF orig, BoolFe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.broadcast(orig);
        return true;
    }
}
