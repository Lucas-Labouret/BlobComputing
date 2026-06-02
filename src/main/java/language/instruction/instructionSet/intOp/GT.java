package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.obj.field.boolField.*;
import language.obj.field.intField.*;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

class GTV extends Procedure {
    public GTV(IntVRef a, IntVRef b, BoolVRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVRef[] aBits = new BoolVRef[a.get().n + 1];
        BoolVRef[] bBits = new BoolVRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolVRef());
            bBits[i] = tmp(new BoolVRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolVRef bothPos = tmp(new BoolVRef());
        BoolVRef bothNeg = tmp(new BoolVRef());
        BoolVRef sameSign = tmp(new BoolVRef());
        BoolVRef diffSign = tmp(new BoolVRef());

        IntVRef notA = IntVRef.of(new IntV(a.get().n));
        not(a, notA);
        IntVRef notB = IntVRef.of(new IntV(b.get().n));
        not(b, notB);

        BoolVRef[] notABits = new BoolVRef[a.get().n + 1];
        BoolVRef[] notBBits = new BoolVRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            notABits[i] = tmp(new BoolVRef());
            notBBits[i] = tmp(new BoolVRef());
        }
        split(notA, notABits);
        split(notB, notBBits);

        and(notABits[0], notBBits[0], bothPos);
        and(aBits[0], bBits[0], bothNeg);
        or(bothPos, bothNeg, sameSign);
        xor(aBits[0], bBits[0], diffSign);

        BoolVRef[] stitchA = new BoolVRef[a.get().n + 1];
        BoolVRef[] stitchB = new BoolVRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            stitchA[i] = tmp(new BoolVRef());
            stitchB[i] = tmp(new BoolVRef());
            set(BoolVRef.of(BoolV.zeroes()), stitchA[i]);
            set(BoolVRef.of(BoolV.zeroes()), stitchB[i]);

            fif(bothPos, aBits[i], stitchA[i], stitchA[i]);
            fif(bothNeg, notABits[i], stitchA[i], stitchA[i]);

            fif(bothPos, bBits[i], stitchB[i], stitchB[i]);
            fif(bothNeg, notBBits[i], stitchB[i], stitchB[i]);
        }

        set(BoolVRef.of(BoolV.zeroes()), res);

        BoolVRef diffBits = tmp(new BoolVRef());
        BoolVRef ssdb = tmp(new BoolVRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(stitchA[i], stitchB[i], diffBits);
            and(sameSign, diffBits, ssdb);
            fif(ssdb, stitchA[i], stitchB[i], res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTVe extends Procedure {
    public GTVe(IntVeRef a, IntVeRef b, BoolVeRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVeRef[] aBits = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bBits = new BoolVeRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolVeRef());
            bBits[i] = tmp(new BoolVeRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolVeRef bothPos = tmp(new BoolVeRef());
        BoolVeRef bothNeg = tmp(new BoolVeRef());
        BoolVeRef sameSign = tmp(new BoolVeRef());
        BoolVeRef diffSign = tmp(new BoolVeRef());

        IntVeRef notA = IntVeRef.of(new IntVe(a.get().n));
        not(a, notA);
        IntVeRef notB = IntVeRef.of(new IntVe(b.get().n));
        not(b, notB);

        BoolVeRef[] notABits = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] notBBits = new BoolVeRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            notABits[i] = tmp(new BoolVeRef());
            notBBits[i] = tmp(new BoolVeRef());
        }
        split(notA, notABits);
        split(notB, notBBits);

        and(notABits[0], notBBits[0], bothPos);
        and(aBits[0], bBits[0], bothNeg);
        or(bothPos, bothNeg, sameSign);
        xor(aBits[0], bBits[0], diffSign);

        BoolVeRef[] stitchA = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] stitchB = new BoolVeRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            stitchA[i] = tmp(new BoolVeRef());
            stitchB[i] = tmp(new BoolVeRef());
            set(BoolVeRef.of(BoolVe.zeroes()), stitchA[i]);
            set(BoolVeRef.of(BoolVe.zeroes()), stitchB[i]);

            fif(bothPos, aBits[i], stitchA[i], stitchA[i]);
            fif(bothNeg, notABits[i], stitchA[i], stitchA[i]);

            fif(bothPos, bBits[i], stitchB[i], stitchB[i]);
            fif(bothNeg, notBBits[i], stitchB[i], stitchB[i]);
        }

        set(BoolVeRef.of(BoolVe.zeroes()), res);

        BoolVeRef diffBits = tmp(new BoolVeRef());
        BoolVeRef ssdb = tmp(new BoolVeRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(stitchA[i], stitchB[i], diffBits);
            and(sameSign, diffBits, ssdb);
            fif(ssdb, stitchA[i], stitchB[i], res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTVf extends Procedure {
    public GTVf(IntVfRef a, IntVfRef b, BoolVfRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        IntVfRef diff = new IntVfRef();
        sub(a, b, diff);

        BoolVfRef[] bits = new BoolVfRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVfRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTE extends Procedure {
    public GTE(IntERef a, IntERef b, BoolERef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntERef diff = new IntERef();
        sub(a, b, diff);

        BoolERef[] bits = new BoolERef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolERef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTEv extends Procedure {
    public GTEv(IntEvRef a, IntEvRef b, BoolEvRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntEvRef diff = new IntEvRef();
        sub(a, b, diff);

        BoolEvRef[] bits = new BoolEvRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolEvRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTEf extends Procedure {
    public GTEf(IntEfRef a, IntEfRef b, BoolEfRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntEfRef diff = new IntEfRef();
        sub(a, b, diff);

        BoolEfRef[] bits = new BoolEfRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolEfRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTF extends Procedure {
    public GTF(IntFRef a, IntFRef b, BoolFRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntFs of different sizes.");

        IntFRef diff = new IntFRef();
        sub(a, b, diff);

        BoolFRef[] bits = new BoolFRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTFv extends Procedure {
    public GTFv(IntFvRef a, IntFvRef b, BoolFvRef res) {
        IntFvRef diff = new IntFvRef();
        sub(a, b, diff);

        BoolFvRef[] bits = new BoolFvRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFvRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTFe extends Procedure {
    public GTFe(IntFeRef a, IntFeRef b, BoolFeRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntFeRef diff = new IntFeRef();
        sub(a, b, diff);

        BoolFeRef[] bits = new BoolFeRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFeRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}