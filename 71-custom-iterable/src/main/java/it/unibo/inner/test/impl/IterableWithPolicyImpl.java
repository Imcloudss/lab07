package it.unibo.inner.test.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    
    private final T[] array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>() {

            public boolean test(T element) {
                return true;
            }
            
        });
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }

    public InnerIterator iterator() {
        return new InnerIterator();
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }


    class InnerIterator implements Iterator<T> {

        private int currentIndex;

        public boolean hasNext() {
            while(currentIndex < array.length) {
                T elementOfArray = array[currentIndex];
                if(filter.test(elementOfArray)) {
                    return true;
                }
                this.currentIndex++;
            }
            return false;
        }

        public T next() {
            if(hasNext()) {
                T elementOfArray = array[currentIndex];
                currentIndex++;
                return elementOfArray;
            }
            return null;
        }

    }
}
