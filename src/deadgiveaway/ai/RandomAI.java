package deadgiveaway.ai;

import java.util.Random;

public class RandomAI {
    private  Random rng = new Random();
    public int getIndex(int max) {
        return rng.nextInt(max);
    }
}
