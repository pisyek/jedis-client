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

    /**
     * Create connection to redis server
     *
     * @return boolean
     */
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

    /**
     * Add to a queue, create queue if not exists
     *
     * @param queueName The queue name i.e batch-<batch id>
     * @param value     The chunk data need to be processed
     */
    public void addToQueue(String queueName, String value) {
        this.jedis.lpush(queueName, value);
    }

    /**
     * FIFO from the batch queue
     *
     * @param queueName The queue name i.e batch-<batch id>
     * @return boolean
     */
    public String popFromQueue(String queueName) {
        return this.jedis.rpop(queueName);
    }

    public void addKeyValue(String key, String value) {
        this.jedis.set(key, value);
    }

    public String getKeyValue(String key) {
        return this.jedis.get(key);
    }

    public long deleteKeyValue(String key) {
        return this.jedis.del(key);
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
