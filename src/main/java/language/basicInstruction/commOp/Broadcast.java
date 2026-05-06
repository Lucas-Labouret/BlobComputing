package language.basicInstruction.commOp;

import field.boolField.fieldS.*;
import language.basicInstruction.BasicInstruction;
import language.fieldRef.*;

/** Broadcasts a BoolV into a BoolVe. */
class BroadcastVe implements BasicInstruction {
	private final BoolVRef orig;
	private final BoolVeRef res;

	/** Creates a new BroadcastVe. */
	public BroadcastVe(BoolVRef orig, BoolVeRef res) {
		this.orig = orig;
		this.res = res;
	}

	/** @return true. */
	@Override
	public boolean exec() {
		res.set(BoolV.broadcastVe(orig.get()));
		return true;
	}
}

/** Broadcasts a BoolV into a BoolVf. */
class BroadcastVf implements BasicInstruction {
    private final BoolVRef orig;
    private final BoolVfRef res;

    /** Creates a new BroadcastVf. */
    public BroadcastVf(BoolVRef orig, BoolVfRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolV.broadcastVf(orig.get()));
        return true;
    }
}

/** Broadcasts a BoolE into a BoolEv. */
class BroadcastEv implements BasicInstruction {
    private final BoolERef orig;
    private final BoolEvRef res;

    /** Creates a new BroadcastEv. */
    public BroadcastEv(BoolERef orig, BoolEvRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolE.broadcastEv(orig.get()));
        return true;
    }
}

/** Broadcasts a BoolE into a BoolEf. */
class BroadcastEf implements BasicInstruction {
    private final BoolERef orig;
    private final BoolEfRef res;

    /** Creates a new BroadcastEf. */
    public BroadcastEf(BoolERef orig, BoolEfRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolE.broadcastEf(orig.get()));
        return true;
    }
}

/** Broadcasts a BoolF into a BoolFv. */
class BroadcastFv implements BasicInstruction {
    private final BoolFRef orig;
    private final BoolFvRef res;

    /** Creates a new BroadcastFv. */
    public BroadcastFv(BoolFRef orig, BoolFvRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolF.broadcastFv(orig.get()));
        return true;
    }
}

/** Broadcasts a BoolF into a BoolFe. */
class BroadcastFe implements BasicInstruction {
    private final BoolFRef orig;
    private final BoolFeRef res;

    /** Creates a new BroadcastFe. */
    public BroadcastFe(BoolFRef orig, BoolFeRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolF.broadcastFe(orig.get()));
        return true;
    }
}
