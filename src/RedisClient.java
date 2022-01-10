import java.util.Objects;
import redis.clients.jedis.*;

public class RedisClient {
    private boolean isConnected = false;
    private int port;
    private Jedis jedis;
    private String host;

    public RedisClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean connect() {
        try {
            this.jedis = new Jedis(this.host, this.port);
            if (Objects.equals(jedis.ping(), "PONG")) {
                this.isConnected = true;
            }
        } catch (Exception $exception) {
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void addToQueue(String queueName, String value) {
        this.jedis.lpush(queueName, value);
    }

    public String popFromQueue(String queueName) {
        return this.jedis.rpop(queueName);
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
