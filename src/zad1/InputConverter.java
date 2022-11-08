package zad1;

import java.util.function.Function;

public class InputConverter<T> {

    private final T inputData;

    public InputConverter(T inputData){
        this.inputData = inputData;
    }

    public <Any> Any convertBy(Function... functions){
        Any any = (Any) inputData;
        for(Function function : functions){
            any = (Any) function.apply(any);
        }
        return any;
    }
}
