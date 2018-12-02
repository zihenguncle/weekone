package jx.com.day5_weaterfall.bean;

public class DataBean {

    public String name;
    public String randomnum;

    public String getName() {
        return name;
    }

    public String getRandomnum() {
        return randomnum;
    }

    public DataBean(String name, String randomnum) {
        this.name = name;
        this.randomnum = randomnum;
    }
}
