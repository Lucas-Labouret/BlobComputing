package language.obj;

import language.instruction.Procedure;
import language.obj.field.boolField.fieldT.BoolVe;
import language.obj.field.intField.IntV;
import language.obj.field.intField.IntVe;
import language.ref.field.boolField.fieldT.BoolVeRef;
import language.ref.field.intField.IntVRef;
import language.ref.field.intField.IntVeRef;

public class Test {
    private class RedAddTest extends Procedure {
        public RedAddTest() {
            int n = 3;
            BoolVeRef[] bits = new BoolVeRef[n+1];
            bits[0] = BoolVeRef.of(BoolVe.zeroes());
            for (int i = 1; i <= n; i++) {
                bits[i] = BoolVeRef.of(BoolVe.ones());
            }
            IntVeRef ve = IntVeRef.of(new IntVe(n));
            join(bits, ve);

            IntVRef v = IntVRef.of(new IntV(n+1));
            redAdd(ve, v);
        }
    }
}
