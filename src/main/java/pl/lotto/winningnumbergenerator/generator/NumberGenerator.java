package pl.lotto.winningnumbergenerator.generator;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NumberGenerator {
    public List<Integer> generateNumbers(boolean validDate){
        Set<Integer> numbersGenerated = new HashSet<>();
        Random random = new Random();
        if(validDate){
            while(numbersGenerated.size()<6){
                numbersGenerated.add(random.nextInt(1,99));
            }
        }
        return numbersGenerated.stream().toList();
    }


}
