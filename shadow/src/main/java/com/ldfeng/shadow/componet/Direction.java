package com.ldfeng.shadow.componet;

/**
 * Created by ldf on 17/6/12.
 */

public class Direction {
    public int x;
    public int y;

    public Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void add(Direction direction) {
        this.x += direction.x;
        this.y += direction.y;
    }

    public void remove(Direction direction) {
        this.x -= direction.x;
        this.y -= direction.y;
    }

    @Override
    public boolean equals (Object object) {
        if(object instanceof Direction) {
            Direction direction = (Direction) object;
            if(direction.x == this.x && direction.y == this.y) {
                return true;
            }
        }
        return false;
    }

    public static Direction LEFT = new Direction(0 ,1);
    public static Direction TOP = new Direction(1 ,0);
    public static Direction RIGHT = new Direction(0 ,-1);
    public static Direction BOTTOM = new Direction(-1 ,0);

}
