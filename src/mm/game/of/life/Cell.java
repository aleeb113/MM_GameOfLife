package mm.game.of.life;

import java.io.Serializable;

class Cell implements Serializable {

    private boolean isAlive;
    private boolean willComeAlive;
    private boolean willDie;


    Cell() {
        this.isAlive = false;
        this.willComeAlive = false;
        this.willDie = false;
    }

    boolean isAlive() {
        return this.isAlive;
    }

    void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    boolean willComeAlive() {
        return willComeAlive;
    }

    void setWillComeAlive(boolean willComeAlive) {
        this.willComeAlive = willComeAlive;
    }

    boolean willDie() {
        return willDie;
    }

    void setWillDie(boolean willDie) {
        this.willDie = willDie;
    }
}
