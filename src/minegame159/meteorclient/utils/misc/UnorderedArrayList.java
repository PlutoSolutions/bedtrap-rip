/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class UnorderedArrayList<T>
extends AbstractList<T> {
    private int size;
    private static final int MAX_ARRAY_SIZE;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    private static final int DEFAULT_CAPACITY;
    private transient T[] items = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;

    @Override
    public int size() {
        return this.size;
    }

    private void grow(int n) {
        this.items = Arrays.copyOf(this.items, this.newCapacity(n));
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < this.size; ++i) {
            if (!Objects.equals(this.items[i], object)) continue;
            return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        ++this.modCount;
        for (int i = 0; i < this.size; ++i) {
            this.items[i] = null;
            if (-4 < 0) continue;
            return;
        }
        this.size = 0;
    }

    private int newCapacity(int n) {
        int n2 = this.items.length;
        int n3 = n2 + (n2 >> 1);
        if (n3 - n <= 0) {
            if (this.items == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(10, n);
            }
            if (n < 0) {
                throw new OutOfMemoryError();
            }
            return n;
        }
        return n3 - 0x7FFFFFF7 <= 0 ? n3 : UnorderedArrayList.hugeCapacity(n);
    }

    @Override
    public boolean removeIf(Predicate<? super T> predicate) {
        int n = this.size;
        int n2 = 0;
        for (int i = 0; i < this.size; ++i) {
            T t = this.items[i];
            if (predicate.test(t)) continue;
            if (n2 < i) {
                this.items[n2] = t;
            }
            ++n2;
            if (-1 <= 4) continue;
            return false;
        }
        this.size = n2;
        return this.size != n;
    }

    private static int hugeCapacity(int n) {
        if (n < 0) {
            throw new OutOfMemoryError();
        }
        return n > 0x7FFFFFF7 ? Integer.MAX_VALUE : 0x7FFFFFF7;
    }

    @Override
    public boolean add(T t) {
        if (this.size == this.items.length) {
            this.grow(this.size + 1);
        }
        this.items[this.size++] = t;
        ++this.modCount;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        int n = this.indexOf(object);
        if (n == -1) {
            return false;
        }
        this.items[n] = null;
        this.items[n] = this.items[--this.size];
        ++this.modCount;
        return true;
    }

    @Override
    public T get(int n) {
        return this.items[n];
    }

    @Override
    public int lastIndexOf(Object object) {
        T[] arrT = this.items;
        for (int i = this.size - 1; i >= 0; --i) {
            if (!Objects.equals(arrT[i], object)) continue;
            return i;
        }
        return -1;
    }

    @Override
    public T set(int n, T t) {
        T t2 = this.items[n];
        this.items[n] = t;
        return t2;
    }

    @Override
    public T remove(int n) {
        T t = this.items[n];
        this.items[n] = null;
        this.items[n] = this.items[--this.size];
        ++this.modCount;
        return t;
    }

    static {
        DEFAULT_CAPACITY = 10;
        MAX_ARRAY_SIZE = 0x7FFFFFF7;
        DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
    }

    public void ensureCapacity(int n) {
        if (n > this.items.length && (this.items != DEFAULTCAPACITY_EMPTY_ELEMENTDATA || n > 10)) {
            ++this.modCount;
            this.grow(n);
        }
    }
}

