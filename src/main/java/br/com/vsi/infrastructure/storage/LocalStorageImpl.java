package br.com.vsi.infrastructure.storage;

public class LocalStorageImpl extends Storage {
    @Override
    void save() {
        System.out.println("Save file on local storage");
    }

    @Override
    void read(String path) {
        System.out.println("Read file from local storage");
    }
}
