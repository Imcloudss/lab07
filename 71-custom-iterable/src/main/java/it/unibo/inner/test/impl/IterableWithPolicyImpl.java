package it.unibo.inner.test.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    
    private final T[] array;
    private Predicate<T> filter;

    /**
     * First costructor of class IterableWithPolicyImpl.
     * 'array' field is passed by user.
     * 'filter' field is setted to always return true.
     * 
     * @param array 
     *
     */
    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>() {

            public boolean test(T element) {
                return true;
            }
            
        });
    }

    /**
     * First costructor of class IterableWithPolicyImpl.
     * Both field are passed by user.
     * 
     * @param array
     * 
     * @param filter
     */
    public IterableWithPolicyImpl(final T[] array, final Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }

    /**
     * Creation of a new iterator.
     * 
     * @return a new InnerIterator's object
     */
    public InnerIterator iterator() {
        return new InnerIterator();
    }

    /**
     * Updating the filter with the new one passed by user.
     */
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }


    /**
     * This inner class implements an iterator in order to
     * filter the elements during the iteration according to
     * the policy in 'filter' field.
     */
    class InnerIterator implements Iterator<T> {

        private int currentIndex;

        /**
         * @return true 
         *          if the iteration has more elements.
         */
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

        /**
         * @return the next element in the iteration.
         */
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
