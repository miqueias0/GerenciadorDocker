package br.com.mike.docker.modelo;

import java.util.List;

public class BlkioStats {
    private List<Object> ioServiceBytesRecursive;
    private List<Object> ioServicedRecursive;
    private List<Object> ioQueueRecursive;
    private List<Object> ioServiceTimeRecursive;
    private List<Object> ioWaitTimeRecursive;
    private List<Object> ioMergedRecursive;
    private List<Object> ioTimeRecursive;
    private List<Object> sectorsRecursive;

    public List<Object> getIoServiceBytesRecursive() {
        return ioServiceBytesRecursive;
    }

    public void setIoServiceBytesRecursive(List<Object> ioServiceBytesRecursive) {
        this.ioServiceBytesRecursive = ioServiceBytesRecursive;
    }

    public List<Object> getIoServicedRecursive() {
        return ioServicedRecursive;
    }

    public void setIoServicedRecursive(List<Object> ioServicedRecursive) {
        this.ioServicedRecursive = ioServicedRecursive;
    }

    public List<Object> getIoQueueRecursive() {
        return ioQueueRecursive;
    }

    public void setIoQueueRecursive(List<Object> ioQueueRecursive) {
        this.ioQueueRecursive = ioQueueRecursive;
    }

    public List<Object> getIoServiceTimeRecursive() {
        return ioServiceTimeRecursive;
    }

    public void setIoServiceTimeRecursive(List<Object> ioServiceTimeRecursive) {
        this.ioServiceTimeRecursive = ioServiceTimeRecursive;
    }

    public List<Object> getIoWaitTimeRecursive() {
        return ioWaitTimeRecursive;
    }

    public void setIoWaitTimeRecursive(List<Object> ioWaitTimeRecursive) {
        this.ioWaitTimeRecursive = ioWaitTimeRecursive;
    }

    public List<Object> getIoMergedRecursive() {
        return ioMergedRecursive;
    }

    public void setIoMergedRecursive(List<Object> ioMergedRecursive) {
        this.ioMergedRecursive = ioMergedRecursive;
    }

    public List<Object> getIoTimeRecursive() {
        return ioTimeRecursive;
    }

    public void setIoTimeRecursive(List<Object> ioTimeRecursive) {
        this.ioTimeRecursive = ioTimeRecursive;
    }

    public List<Object> getSectorsRecursive() {
        return sectorsRecursive;
    }

    public void setSectorsRecursive(List<Object> sectorsRecursive) {
        this.sectorsRecursive = sectorsRecursive;
    }
}
