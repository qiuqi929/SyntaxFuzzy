package pool;

import java.util.List;

public interface Pool<T> {

    void addElement(T object);

    List<T> getPoolList();

    T randomElement();

    // .. some others..

}
