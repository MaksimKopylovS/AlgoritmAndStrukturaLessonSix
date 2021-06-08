package max_sk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeWorkApplilcation {
    public static void main(String[] args) {
        List<TreeMy<Integer>> myList = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());
        int random;
        int countTreeMy = 20;
        int countTry = 0;


        for (int i = 0; i < countTreeMy; i++) {
            myList.add(new TreeMy<>());
            for (int j = 0; j < countTreeMy; j++) {
                random = r.nextInt(50)-25;
                myList.get(i).add(random);
            }
            System.out.println(myList.get(i).isBalanced(myList.get(i).getRoot()));
            if(myList.get(i).isBalanced(myList.get(i).getRoot())){
                countTry++;
            }
        }

        double proc = (100/(countTreeMy / countTry));
        System.out.println(proc +"%");
    }
}
