package br.com.vsi.infrastructure.storage;

public abstract class Storage {
    abstract void save();
    abstract void read(String path);
}
