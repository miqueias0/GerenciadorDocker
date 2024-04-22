package br.com.mike.docker.modelo;

import java.util.Map;

public class DockerContainerStats {
    private String read;
    private String preread;
    private PidsStats pids_stats;
    private BlkioStats blkio_stats;
    private int num_procs;
    private Map<String, Object> storage_stats;
    private Cpu_stats cpu_stats;
    private Cpu_stats precpu_stats;
    private MemoryStats memory_stats;
    private String name;
    private String id;
    private Map<String, NetworkStats> networks;

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getPreread() {
        return preread;
    }

    public void setPreread(String preread) {
        this.preread = preread;
    }

    public PidsStats getPids_stats() {
        return pids_stats;
    }

    public void setPids_stats(PidsStats pids_stats) {
        this.pids_stats = pids_stats;
    }

    public BlkioStats getBlkio_stats() {
        return blkio_stats;
    }

    public void setBlkio_stats(BlkioStats blkio_stats) {
        this.blkio_stats = blkio_stats;
    }

    public int getNum_procs() {
        return num_procs;
    }

    public void setNum_procs(int num_procs) {
        this.num_procs = num_procs;
    }

    public Map<String, Object> getStorage_stats() {
        return storage_stats;
    }

    public void setStorage_stats(Map<String, Object> storage_stats) {
        this.storage_stats = storage_stats;
    }

    public Cpu_stats getCpu_stats() {
        return cpu_stats;
    }

    public void setCpu_stats(Cpu_stats cpu_stats) {
        this.cpu_stats = cpu_stats;
    }

    public Cpu_stats getPrecpu_stats() {
        return precpu_stats;
    }

    public void setPrecpu_stats(Cpu_stats precpu_stats) {
        this.precpu_stats = precpu_stats;
    }

    public MemoryStats getMemory_stats() {
        return memory_stats;
    }

    public void setMemory_stats(MemoryStats memory_stats) {
        this.memory_stats = memory_stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, NetworkStats> getNetworks() {
        return networks;
    }

    public void setNetworks(Map<String, NetworkStats> networks) {
        this.networks = networks;
    }
}
