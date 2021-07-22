/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import java.lang.reflect.Method;

abstract class VarArgsChecker {
    static VarArgsChecker create() {
        try {
            Method lIlllllIlllIll = Method.class.getMethod("isVarArgs", new Class[0]);
            if (lIlllllIlllIll != null) {
                return new RealVarArgsChecker();
            }
            return new NoVarArgsChecker();
        }
        catch (NoSuchMethodException lIlllllIlllIlI) {
            return new NoVarArgsChecker();
        }
        catch (SecurityException lIlllllIlllIIl) {
            return new NoVarArgsChecker();
        }
    }

    abstract int fixedArgs(Method var1);

    private VarArgsChecker() {
        VarArgsChecker lIlllllIlllllI;
    }

    abstract boolean isVarArgs(Method var1);

    private static final class RealVarArgsChecker
    extends VarArgsChecker {
        private RealVarArgsChecker() {
            RealVarArgsChecker llllllllllllllllIlllIlIllllIIIlI;
        }

        @Override
        boolean isVarArgs(Method llllllllllllllllIlllIlIlllIlllIl) {
            return llllllllllllllllIlllIlIlllIlllIl.isVarArgs();
        }

        @Override
        int fixedArgs(Method llllllllllllllllIlllIlIlllIllIIl) {
            return llllllllllllllllIlllIlIlllIllIIl.isVarArgs() ? llllllllllllllllIlllIlIlllIllIIl.getParameterTypes().length - 1 : 0;
        }
    }

    private static final class NoVarArgsChecker
    extends VarArgsChecker {
        @Override
        int fixedArgs(Method lllllllllllllllllllllIlIIllllIll) {
            return 0;
        }

        private NoVarArgsChecker() {
            NoVarArgsChecker lllllllllllllllllllllIlIlIIIIIII;
        }

        @Override
        boolean isVarArgs(Method lllllllllllllllllllllIlIIlllllIl) {
            return false;
        }
    }
}

