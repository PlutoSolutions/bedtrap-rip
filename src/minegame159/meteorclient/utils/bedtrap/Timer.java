/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.bedtrap;

public class Timer {
    private long time = -1L;

    public boolean passedDs(double d) {
        return this.passedMs((long)d * 100L);
    }

    public long convertToNS(long l) {
        return l * 1000000L;
    }

    public void setMs(long l) {
        this.time = System.nanoTime() - this.convertToNS(l);
    }

    public boolean passedMs(long l) {
        return this.passedNS(this.convertToNS(l));
    }

    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }

    public boolean passedNS(long l) {
        return System.nanoTime() - this.time >= l;
    }

    public Timer reset() {
        this.time = System.nanoTime();
        return this;
    }

    public boolean passedDms(double d) {
        return this.passedMs((long)d * 10L);
    }

    public long getMs(long l) {
        return l / 1000000L;
    }

    public boolean passedS(double d) {
        return this.passedMs((long)d * 1000L);
    }
}

