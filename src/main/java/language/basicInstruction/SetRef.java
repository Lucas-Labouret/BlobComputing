package language.basicInstruction;

import language.Ref;

public class SetRef<T> implements BasicInstruction {
    private final Ref<T> in;
    private final Ref<T> out;

    public SetRef(Ref<T> in, Ref<T> out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public boolean exec() {
        out.set(in.copy().get());
        return true;
    }
}
