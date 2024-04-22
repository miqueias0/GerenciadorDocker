package br.com.mike.docker.modelo;

public class Cpu_stats {
    private Cpu_usage cpu_usage;
    private long system_cpu_usage;
    private int online_cpus;
    private ThrottlingData throttling_data;

    public Cpu_usage getCpu_usage() {
        return cpu_usage;
    }

    public void setCpu_usage(Cpu_usage cpu_usage) {
        this.cpu_usage = cpu_usage;
    }

    public long getSystem_cpu_usage() {
        return system_cpu_usage;
    }

    public void setSystem_cpu_usage(long system_cpu_usage) {
        this.system_cpu_usage = system_cpu_usage;
    }

    public int getOnline_cpus() {
        return online_cpus;
    }

    public void setOnline_cpus(int online_cpus) {
        this.online_cpus = online_cpus;
    }

    public ThrottlingData getThrottling_data() {
        return throttling_data;
    }

    public void setThrottling_data(ThrottlingData throttling_data) {
        this.throttling_data = throttling_data;
    }
}
