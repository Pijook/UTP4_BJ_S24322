package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private T data;

    public Maybe(T data){
        this.data = data;
    }

    public static Maybe of(Object data){
        return new Maybe<>(data);
    }

    public void ifPresent(Consumer<T> consumer){
        if(data == null){
            return;
        }

        consumer.accept(data);
    }

    public Maybe<T> map(Function<T, T> function){
        if(data == null){
            return this;
        }
        else{
            return new Maybe<T>((T) function.apply(data));
        }
    }

    public T get(){
        if(data != null){
            return data;
        }
        else{
            throw new NoSuchElementException();
        }
    }

    public boolean isPresent(){
        return data != null;
    }

    public T orElse(T defVal){
        if(data == null){
            return defVal;
        }
        else{
            return data;
        }
    }

    public Maybe<T> filter(Predicate<T> predicate){
        if(predicate.test(data)){
            return this;
        }
        else{
            return new Maybe<T>(null);
        }
    }

    @Override
    public String toString() {
        if(data == null){
            return "Maybe is empty";
        }
        else{
            return "Maybe has value " + data;
        }
    }
}
