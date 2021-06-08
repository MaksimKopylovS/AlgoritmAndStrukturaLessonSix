package max_sk;

public interface Tree <E extends Comparable<? super E>> {


    boolean add(E value);

    boolean contains(E value);

    boolean isEmpty();

    void display();

}

