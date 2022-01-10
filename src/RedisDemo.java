public class RedisDemo {
    public static void main(String[] args) {
        System.out.println("Hello");

        RedisClient redisClient = new RedisClient("localhost", 6379);
        System.out.println(redisClient.isConnected());
copy
        boolean connect = redisClient.connect();

        System.out.println("add task 1");
        redisClient.addToQueue("task","eeee");
        String a = redisClient.popFromQueue("task");
        System.out.println(a);
    }
}
