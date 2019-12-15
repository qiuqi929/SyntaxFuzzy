package pool;

import java.util.List;

public interface Pool<T> {

    void addElement(T value);

    List<T> getPoolList();

    T randomElement();
}
