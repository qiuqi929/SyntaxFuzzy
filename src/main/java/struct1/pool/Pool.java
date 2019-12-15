package pool;

import java.util.List;

public interface Pool<T> {

    List<T> getPoolList();

    void addElement(T value);

    T randomElement();
}
