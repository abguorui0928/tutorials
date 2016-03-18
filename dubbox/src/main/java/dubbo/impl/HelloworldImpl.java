package dubbo.impl;

import dubbo.Helloworld;

/**
 * Created by guor on 2016/3/18.
 */
public class HelloworldImpl implements Helloworld {
    @Override
    public String hello() {
        return "hello " + System.currentTimeMillis();
    }
}
