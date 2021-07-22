/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.bedtrap;

public class Timer {
    private /* synthetic */ long time;

    public Timer() {
        Timer llllllllllllllllllIIlIlIIIlllIII;
        llllllllllllllllllIIlIlIIIlllIII.time = -1L;
    }

    public boolean passedDs(double llllllllllllllllllIIlIlIIIlIIlll) {
        Timer llllllllllllllllllIIlIlIIIlIIllI;
        return llllllllllllllllllIIlIlIIIlIIllI.passedMs((long)llllllllllllllllllIIlIlIIIlIIlll * 100L);
    }

    public long convertToNS(long llllllllllllllllllIIlIlIIIIIIlIl) {
        return llllllllllllllllllIIlIlIIIIIIlIl * 1000000L;
    }

    public void setMs(long llllllllllllllllllIIlIlIIIIllIll) {
        Timer llllllllllllllllllIIlIlIIIIlllII;
        llllllllllllllllllIIlIlIIIIlllII.time = System.nanoTime() - llllllllllllllllllIIlIlIIIIlllII.convertToNS(llllllllllllllllllIIlIlIIIIllIll);
    }

    public boolean passedMs(long llllllllllllllllllIIlIlIIIIlllll) {
        Timer llllllllllllllllllIIlIlIIIlIIIlI;
        return llllllllllllllllllIIlIlIIIlIIIlI.passedNS(llllllllllllllllllIIlIlIIIlIIIlI.convertToNS(llllllllllllllllllIIlIlIIIIlllll));
    }

    public long getPassedTimeMs() {
        Timer llllllllllllllllllIIlIlIIIIlIIIl;
        return llllllllllllllllllIIlIlIIIIlIIIl.getMs(System.nanoTime() - llllllllllllllllllIIlIlIIIIlIIIl.time);
    }

    public boolean passedNS(long llllllllllllllllllIIlIlIIIIlIIll) {
        Timer llllllllllllllllllIIlIlIIIIlIlII;
        return System.nanoTime() - llllllllllllllllllIIlIlIIIIlIlII.time >= llllllllllllllllllIIlIlIIIIlIIll;
    }

    public Timer reset() {
        Timer llllllllllllllllllIIlIlIIIIIlllI;
        llllllllllllllllllIIlIlIIIIIlllI.time = System.nanoTime();
        return llllllllllllllllllIIlIlIIIIIlllI;
    }

    public boolean passedDms(double llllllllllllllllllIIlIlIIIlIllIl) {
        Timer llllllllllllllllllIIlIlIIIlIlllI;
        return llllllllllllllllllIIlIlIIIlIlllI.passedMs((long)llllllllllllllllllIIlIlIIIlIllIl * 10L);
    }

    public long getMs(long llllllllllllllllllIIlIlIIIIIlIIl) {
        return llllllllllllllllllIIlIlIIIIIlIIl / 1000000L;
    }

    public boolean passedS(double llllllllllllllllllIIlIlIIIllIIIl) {
        Timer llllllllllllllllllIIlIlIIIllIIlI;
        return llllllllllllllllllIIlIlIIIllIIlI.passedMs((long)llllllllllllllllllIIlIlIIIllIIIl * 1000L);
    }
}

