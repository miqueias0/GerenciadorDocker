package br.com.mike.docker.modelo;

public class NetworkStats {

    private long rx_bytes;
    private long rx_packets;
    private int rx_errors;
    private int rx_dropped;
    private long tx_bytes;
    private long tx_packets;
    private int tx_errors;
    private int tx_dropped;

    public long getRx_bytes() {
        return rx_bytes;
    }

    public void setRx_bytes(long rx_bytes) {
        this.rx_bytes = rx_bytes;
    }

    public long getRx_packets() {
        return rx_packets;
    }

    public void setRx_packets(long rx_packets) {
        this.rx_packets = rx_packets;
    }

    public int getRx_errors() {
        return rx_errors;
    }

    public void setRx_errors(int rx_errors) {
        this.rx_errors = rx_errors;
    }

    public int getRx_dropped() {
        return rx_dropped;
    }

    public void setRx_dropped(int rx_dropped) {
        this.rx_dropped = rx_dropped;
    }

    public long getTx_bytes() {
        return tx_bytes;
    }

    public void setTx_bytes(long tx_bytes) {
        this.tx_bytes = tx_bytes;
    }

    public long getTx_packets() {
        return tx_packets;
    }

    public void setTx_packets(long tx_packets) {
        this.tx_packets = tx_packets;
    }

    public int getTx_errors() {
        return tx_errors;
    }

    public void setTx_errors(int tx_errors) {
        this.tx_errors = tx_errors;
    }

    public int getTx_dropped() {
        return tx_dropped;
    }

    public void setTx_dropped(int tx_dropped) {
        this.tx_dropped = tx_dropped;
    }
}
