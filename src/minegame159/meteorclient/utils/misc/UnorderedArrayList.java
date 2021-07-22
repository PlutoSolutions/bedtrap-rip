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
    private /* synthetic */ int size;
    private static final /* synthetic */ int MAX_ARRAY_SIZE;
    private static final /* synthetic */ Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    private static final /* synthetic */ int DEFAULT_CAPACITY;
    private transient /* synthetic */ T[] items;

    @Override
    public int size() {
        UnorderedArrayList lllllllllllllllllIIIlIIIIIlllIll;
        return lllllllllllllllllIIIlIIIIIlllIll.size;
    }

    private void grow(int lllllllllllllllllIIIlIIIIIlIllll) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIIllIIII;
        lllllllllllllllllIIIlIIIIIllIIII.items = Arrays.copyOf(lllllllllllllllllIIIlIIIIIllIIII.items, lllllllllllllllllIIIlIIIIIllIIII.newCapacity(lllllllllllllllllIIIlIIIIIlIllll));
    }

    @Override
    public int indexOf(Object lllllllllllllllllIIIlIIIIlllIIIl) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIlllIIII;
        for (int lllllllllllllllllIIIlIIIIlllIIll = 0; lllllllllllllllllIIIlIIIIlllIIll < lllllllllllllllllIIIlIIIIlllIIII.size; ++lllllllllllllllllIIIlIIIIlllIIll) {
            if (!Objects.equals(lllllllllllllllllIIIlIIIIlllIIII.items[lllllllllllllllllIIIlIIIIlllIIll], lllllllllllllllllIIIlIIIIlllIIIl)) continue;
            return lllllllllllllllllIIIlIIIIlllIIll;
        }
        return -1;
    }

    public UnorderedArrayList() {
        UnorderedArrayList lllllllllllllllllIIIlIIIlIIlIllI;
        lllllllllllllllllIIIlIIIlIIlIllI.items = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public void clear() {
        UnorderedArrayList lllllllllllllllllIIIlIIIIllllIII;
        ++lllllllllllllllllIIIlIIIIllllIII.modCount;
        for (int lllllllllllllllllIIIlIIIIllllIlI = 0; lllllllllllllllllIIIlIIIIllllIlI < lllllllllllllllllIIIlIIIIllllIII.size; ++lllllllllllllllllIIIlIIIIllllIlI) {
            lllllllllllllllllIIIlIIIIllllIII.items[lllllllllllllllllIIIlIIIIllllIlI] = null;
        }
        lllllllllllllllllIIIlIIIIllllIII.size = 0;
    }

    private int newCapacity(int lllllllllllllllllIIIlIIIIIlIlIIl) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIIlIIllI;
        int lllllllllllllllllIIIlIIIIIlIlIII = lllllllllllllllllIIIlIIIIIlIIllI.items.length;
        int lllllllllllllllllIIIlIIIIIlIIlll = lllllllllllllllllIIIlIIIIIlIlIII + (lllllllllllllllllIIIlIIIIIlIlIII >> 1);
        if (lllllllllllllllllIIIlIIIIIlIIlll - lllllllllllllllllIIIlIIIIIlIlIIl <= 0) {
            if (lllllllllllllllllIIIlIIIIIlIIllI.items == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(10, lllllllllllllllllIIIlIIIIIlIlIIl);
            }
            if (lllllllllllllllllIIIlIIIIIlIlIIl < 0) {
                throw new OutOfMemoryError();
            }
            return lllllllllllllllllIIIlIIIIIlIlIIl;
        }
        return lllllllllllllllllIIIlIIIIIlIIlll - 0x7FFFFFF7 <= 0 ? lllllllllllllllllIIIlIIIIIlIIlll : UnorderedArrayList.hugeCapacity(lllllllllllllllllIIIlIIIIIlIlIIl);
    }

    @Override
    public boolean removeIf(Predicate<? super T> lllllllllllllllllIIIlIIIIlIIIIlI) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIlIIIIll;
        int lllllllllllllllllIIIlIIIIlIIIlIl = lllllllllllllllllIIIlIIIIlIIIIll.size;
        int lllllllllllllllllIIIlIIIIlIIIlII = 0;
        for (int lllllllllllllllllIIIlIIIIlIIlIII = 0; lllllllllllllllllIIIlIIIIlIIlIII < lllllllllllllllllIIIlIIIIlIIIIll.size; ++lllllllllllllllllIIIlIIIIlIIlIII) {
            T lllllllllllllllllIIIlIIIIlIIlIIl = lllllllllllllllllIIIlIIIIlIIIIll.items[lllllllllllllllllIIIlIIIIlIIlIII];
            if (lllllllllllllllllIIIlIIIIlIIIIlI.test(lllllllllllllllllIIIlIIIIlIIlIIl)) continue;
            if (lllllllllllllllllIIIlIIIIlIIIlII < lllllllllllllllllIIIlIIIIlIIlIII) {
                lllllllllllllllllIIIlIIIIlIIIIll.items[lllllllllllllllllIIIlIIIIlIIIlII] = lllllllllllllllllIIIlIIIIlIIlIIl;
            }
            ++lllllllllllllllllIIIlIIIIlIIIlII;
        }
        lllllllllllllllllIIIlIIIIlIIIIll.size = lllllllllllllllllIIIlIIIIlIIIlII;
        return lllllllllllllllllIIIlIIIIlIIIIll.size != lllllllllllllllllIIIlIIIIlIIIlIl;
    }

    private static int hugeCapacity(int lllllllllllllllllIIIlIIIIIlIIIIl) {
        if (lllllllllllllllllIIIlIIIIIlIIIIl < 0) {
            throw new OutOfMemoryError();
        }
        return lllllllllllllllllIIIlIIIIIlIIIIl > 0x7FFFFFF7 ? Integer.MAX_VALUE : 0x7FFFFFF7;
    }

    @Override
    public boolean add(T lllllllllllllllllIIIlIIIlIIlIIIl) {
        UnorderedArrayList lllllllllllllllllIIIlIIIlIIlIIII;
        if (lllllllllllllllllIIIlIIIlIIlIIII.size == lllllllllllllllllIIIlIIIlIIlIIII.items.length) {
            lllllllllllllllllIIIlIIIlIIlIIII.grow(lllllllllllllllllIIIlIIIlIIlIIII.size + 1);
        }
        lllllllllllllllllIIIlIIIlIIlIIII.items[lllllllllllllllllIIIlIIIlIIlIIII.size++] = lllllllllllllllllIIIlIIIlIIlIIIl;
        ++lllllllllllllllllIIIlIIIlIIlIIII.modCount;
        return true;
    }

    @Override
    public boolean remove(Object lllllllllllllllllIIIlIIIIlIlllIl) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIlIllIll;
        int lllllllllllllllllIIIlIIIIlIlllII = lllllllllllllllllIIIlIIIIlIllIll.indexOf(lllllllllllllllllIIIlIIIIlIlllIl);
        if (lllllllllllllllllIIIlIIIIlIlllII == -1) {
            return false;
        }
        lllllllllllllllllIIIlIIIIlIllIll.items[lllllllllllllllllIIIlIIIIlIlllII] = null;
        lllllllllllllllllIIIlIIIIlIllIll.items[lllllllllllllllllIIIlIIIIlIlllII] = lllllllllllllllllIIIlIIIIlIllIll.items[--lllllllllllllllllIIIlIIIIlIllIll.size];
        ++lllllllllllllllllIIIlIIIIlIllIll.modCount;
        return true;
    }

    @Override
    public T get(int lllllllllllllllllIIIlIIIIlllllll) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIllllllI;
        return lllllllllllllllllIIIlIIIIllllllI.items[lllllllllllllllllIIIlIIIIlllllll];
    }

    @Override
    public int lastIndexOf(Object lllllllllllllllllIIIlIIIIllIIlll) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIllIlIII;
        T[] lllllllllllllllllIIIlIIIIllIIllI = lllllllllllllllllIIIlIIIIllIlIII.items;
        for (int lllllllllllllllllIIIlIIIIllIlIIl = lllllllllllllllllIIIlIIIIllIlIII.size - 1; lllllllllllllllllIIIlIIIIllIlIIl >= 0; --lllllllllllllllllIIIlIIIIllIlIIl) {
            if (!Objects.equals(lllllllllllllllllIIIlIIIIllIIllI[lllllllllllllllllIIIlIIIIllIlIIl], lllllllllllllllllIIIlIIIIllIIlll)) continue;
            return lllllllllllllllllIIIlIIIIllIlIIl;
        }
        return -1;
    }

    @Override
    public T set(int lllllllllllllllllIIIlIIIlIIIIlIl, T lllllllllllllllllIIIlIIIlIIIIlII) {
        UnorderedArrayList lllllllllllllllllIIIlIIIlIIIlIlI;
        T lllllllllllllllllIIIlIIIlIIIIlll = lllllllllllllllllIIIlIIIlIIIlIlI.items[lllllllllllllllllIIIlIIIlIIIIlIl];
        lllllllllllllllllIIIlIIIlIIIlIlI.items[lllllllllllllllllIIIlIIIlIIIIlIl] = lllllllllllllllllIIIlIIIlIIIIlII;
        return lllllllllllllllllIIIlIIIlIIIIlll;
    }

    @Override
    public T remove(int lllllllllllllllllIIIlIIIIlIlIIIl) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIlIlIlIl;
        T lllllllllllllllllIIIlIIIIlIlIIll = lllllllllllllllllIIIlIIIIlIlIlIl.items[lllllllllllllllllIIIlIIIIlIlIIIl];
        lllllllllllllllllIIIlIIIIlIlIlIl.items[lllllllllllllllllIIIlIIIIlIlIIIl] = null;
        lllllllllllllllllIIIlIIIIlIlIlIl.items[lllllllllllllllllIIIlIIIIlIlIIIl] = lllllllllllllllllIIIlIIIIlIlIlIl.items[--lllllllllllllllllIIIlIIIIlIlIlIl.size];
        ++lllllllllllllllllIIIlIIIIlIlIlIl.modCount;
        return lllllllllllllllllIIIlIIIIlIlIIll;
    }

    static {
        DEFAULT_CAPACITY = 10;
        MAX_ARRAY_SIZE = 0x7FFFFFF7;
        DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
    }

    public void ensureCapacity(int lllllllllllllllllIIIlIIIIIllIlIl) {
        UnorderedArrayList lllllllllllllllllIIIlIIIIIllIllI;
        if (lllllllllllllllllIIIlIIIIIllIlIl > lllllllllllllllllIIIlIIIIIllIllI.items.length && (lllllllllllllllllIIIlIIIIIllIllI.items != DEFAULTCAPACITY_EMPTY_ELEMENTDATA || lllllllllllllllllIIIlIIIIIllIlIl > 10)) {
            ++lllllllllllllllllIIIlIIIIIllIllI.modCount;
            lllllllllllllllllIIIlIIIIIllIllI.grow(lllllllllllllllllIIIlIIIIIllIlIl);
        }
    }
}

