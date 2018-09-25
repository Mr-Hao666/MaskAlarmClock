package com.yhao.util;

import java.io.Serializable;

/**
 * 2p，可使用Pair减少代码量
 *
 * @param <F>
 * @param <S>
 * @author smartlv
 */
@SuppressWarnings("serial")
public final class Triple<F, S, T> implements Serializable {
    public F f;
    public S s;
    public T t;

    public Triple() {
    }

    public Triple(F f, S s, T t) {
        this.f = f;
        this.s = s;
        this.t = t;
    }

    /**
     * 通过值创建值对
     *
     * @param f 第一个值
     * @param s 第二个值
     * @return 值对
     */
    public static <FT, ST, TT> Triple<FT, ST, TT> makeTriple(FT f, ST s, TT t) {
        return new Triple<FT, ST, TT>(f, s, t);
    }

    private static <T> boolean eq(T o1, T o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        Triple<F, S, T> pr = (Triple<F, S, T>) o;
        if (pr == null)
            return false;
        return eq(f, pr.f) && eq(s, pr.s);
    }

    private static int h(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    public int hashCode() {
        int seed = h(f);
        seed ^= h(s) + 0x9e3779b9 + (seed << 6) + (seed >> 2);
        return seed;
    }

    public F getF() {
        return f;
    }

    public S getS() {
        return s;
    }

    public void setF(F f) {
        this.f = f;
    }

    public void setS(S s) {
        this.s = s;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(f).append(", ").append(s).append(", ").append(t).append("}");
        return sb.toString();
    }
}
