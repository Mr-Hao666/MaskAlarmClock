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
public final class Pair<F, S> implements Serializable {
    public F f;
    public S s;

    public Pair() {
    }

    public Pair(F f, S s) {
        this.f = f;
        this.s = s;
    }

    /**
     * 通过值创建值对
     *
     * @param f 第一个值
     * @param s 第二个值
     * @return 值对
     */
    public static <FT, ST> Pair<FT, ST> makePair(FT f, ST s) {
        return new Pair<FT, ST>(f, s);
    }

    private static <T> boolean eq(T o1, T o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        Pair<F, S> pr = (Pair<F, S>) o;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(f).append(", ").append(s).append("}");
        return sb.toString();
    }
}
