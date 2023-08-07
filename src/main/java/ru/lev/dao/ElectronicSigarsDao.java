package ru.lev.dao;

public interface ElectronicSigarsDao {

    void create(String nameSigars, int count);

    void updateElectronigSigars(String name, int count);

    int countTaste(String nameElectronicSigars);

    String buyHqd(String nameElectronicSigars, int howMany);
}
