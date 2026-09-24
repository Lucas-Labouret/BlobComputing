package blobProgram.agent.homogeneize;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Expand extends Force {
    private final QuasiParticle seeds;
    private final IntVeRef gradient;

    public Expand (QuasiParticle seeds, IntVeRef gradient) {
        this.seeds = seeds;
        this.gradient = gradient;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef yes, BoolVRef no) {
            QuasiParticle one = tmp(new QuasiParticle());
            QuasiParticle two = tmp(new QuasiParticle());
            call(seeds.oneParticle(one));
            call(seeds.twoParticle(two));

            BoolVeRef posGrad = tmp(new BoolVeRef());
            gt(gradient, new IntVeRef(IntVe.of(1, gradient.get().n)), posGrad);

            call(computeOne(one, posGrad, yes));
            show("Expand Yes", yes);
            set(new BoolVRef(BoolV.zeroes()), no);
        }
    }

    private class ComputeOne extends Procedure {
        public ComputeOne(QuasiParticle one, BoolVeRef posGrad, BoolVRef res) {
            int n = gradient.get().n;

            BoolVeRef isMaxGrad = tmp(new BoolVeRef());
            call(isMax(gradient, isMaxGrad));
            and(isMaxGrad, posGrad, isMaxGrad);
            show("IsMaxGrad", isMaxGrad);

            IntVeRef adjustedPrio = tmp(new IntVeRef(new IntVe(prioRandBits)));
            broadcast(prioRand, adjustedPrio);
            call(BlobV.send(adjustedPrio, adjustedPrio));
            fif(isMaxGrad, adjustedPrio, new IntVeRef(IntVe.minValue(prioRandBits)), adjustedPrio);
            BoolVeRef isMaxAdjustedPrio = tmp(new BoolVeRef());
            call(isMax(adjustedPrio, isMaxAdjustedPrio));

            and(isMaxGrad, isMaxAdjustedPrio, isMaxGrad);



            IntVRef nbMaxGrad = tmp(new IntVRef(new IntV(n)));
            redAdd(isMaxGrad, nbMaxGrad);
            BoolVRef oneMaxGrad = tmp(new BoolVRef());
            eq(nbMaxGrad, new IntVRef(IntV.of(1, n)), oneMaxGrad);
            and(one, oneMaxGrad, oneMaxGrad);
            BoolVeRef oneMaxGradVe = tmp(new BoolVeRef());
            broadcast(oneMaxGrad, oneMaxGradVe);
            and(isMaxGrad, oneMaxGradVe, isMaxGrad);
            call(BlobV.send(isMaxGrad, isMaxGrad));
            redOr(isMaxGrad, res);
        }
    }
    private Procedure computeOne(QuasiParticle one, BoolVeRef posGrad, BoolVRef res) {
        return new ComputeOne(one, posGrad, res);
    }

    private class ComputeTwo extends Procedure {
        public ComputeTwo(QuasiParticle two, QuasiParticle res) {
            int n = gradient.get().n;

        }
    }

    private class IsMax extends Procedure {
        public IsMax(IntVeRef source, BoolVeRef res) {
            int n = source.get().n;
            IntVRef max = tmp(new IntVRef(new IntV(n)));
            redMax(source, max);
            IntVeRef maxGradVe = tmp(new IntVeRef(new IntVe(n)));
            broadcast(max, maxGradVe);
            eq(source, maxGradVe, res);
        }
    }
    private Procedure isMax(IntVeRef source, BoolVeRef res) {
        return new IsMax(source, res);
    }

    @Override
    protected Procedure _compute(BoolVRef yes, BoolVRef no) {
        return new _Compute(yes, no);
    }
}
