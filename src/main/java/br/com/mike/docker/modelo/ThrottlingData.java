package br.com.mike.docker.modelo;

public class ThrottlingData {
    private int periods;
    private int throttled_periods;
    private long throttled_time;

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getThrottled_periods() {
        return throttled_periods;
    }

    public void setThrottled_periods(int throttled_periods) {
        this.throttled_periods = throttled_periods;
    }

    public long getThrottled_time() {
        return throttled_time;
    }

    public void setThrottled_time(long throttled_time) {
        this.throttled_time = throttled_time;
    }
}
