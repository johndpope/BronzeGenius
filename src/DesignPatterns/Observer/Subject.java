package DesignPatterns.Observer;

/**
 * Created by xuch on 2016/1/25.
 */
public interface Subject
{
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
