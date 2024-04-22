package br.com.mike.docker.modelo;

import java.util.Map;

public class MemoryStats {
    private long usage;
    private long max_usage;
    private Map<String, Long> stats;
    private long limit;

    public long getUsage() {
        return usage;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }

    public long getMax_usage() {
        return max_usage;
    }

    public void setMax_usage(long max_usage) {
        this.max_usage = max_usage;
    }

    public Map<String, Long> getStats() {
        return stats;
    }

    public void setStats(Map<String, Long> stats) {
        this.stats = stats;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
