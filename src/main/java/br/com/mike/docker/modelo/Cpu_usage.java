package br.com.mike.docker.modelo;

public class Cpu_usage {
    private long total_usage;
    private long[] percpu_usage;
    private long usage_in_kernelmode;
    private long usage_in_usermode;

    public long getTotal_usage() {
        return total_usage;
    }

    public void setTotal_usage(long total_usage) {
        this.total_usage = total_usage;
    }

    public long[] getPercpu_usage() {
        return percpu_usage;
    }

    public void setPercpu_usage(long[] percpu_usage) {
        this.percpu_usage = percpu_usage;
    }

    public long getUsage_in_kernelmode() {
        return usage_in_kernelmode;
    }

    public void setUsage_in_kernelmode(long usage_in_kernelmode) {
        this.usage_in_kernelmode = usage_in_kernelmode;
    }

    public long getUsage_in_usermode() {
        return usage_in_usermode;
    }

    public void setUsage_in_usermode(long usage_in_usermode) {
        this.usage_in_usermode = usage_in_usermode;
    }
}
