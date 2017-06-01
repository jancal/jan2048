package com.jancal.game.new2048;

/**
 * Block
 *
 * @author Jancal(Zhang Hao) 2017/4/21
 */
class Block {
    int x;
    int y;
    int value;
    Block left;
    Block right;
    Block up;
    Block down;

    Block(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    boolean isZero() {
        return value == 0;
    }

    boolean moveIsAvailable(Direction direction) {
        Block pre = null;
        switch (direction) {
            case left:
                pre = this.left;
                break;
            case right:
                pre = this.right;
                break;
            case up:
                pre = this.up;
                break;
            case down:
                pre = this.down;
                break;
        }
        if (pre == null) return false;

        if (!isZero()) {
            if (pre.isZero() || pre.value == value) return true;
        }
        return pre.moveIsAvailable(direction);
    }

    @Override
    public String toString() {
        return "Block{" +
                "value=" + value +
                '}';
    }

    boolean hasPre(Direction direction) {
        return pre(direction) != null;
    }

    Block pre(Direction direction) {
        Block pre;
        if (direction.equals(Direction.left)) {
            pre = this.right;
        } else if (direction.equals(Direction.right)) {
            pre = this.left;
        } else if (direction.equals(Direction.up)) {
            pre = this.down;
        } else {
            pre = this.up;
        }
        return pre;
    }

    boolean hasNext(Direction direction) {
        return next(direction) != null;
    }

    Block next(Direction direction) {
        Block next;
        if (direction.equals(Direction.left)) {
            next = this.left;
        } else if (direction.equals(Direction.right)) {
            next = this.right;
        } else if (direction.equals(Direction.up)) {
            next = this.up;
        } else {
            next = this.down;
        }
        return next;
    }


}
