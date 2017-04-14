package DesignPattern.Visitor;

/**
 * @author xuch.
 */
public interface Element<T> {
    void accept(T t);
}
