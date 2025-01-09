package br.com.vsi.infrastructure.storage;

public class AwsS3StorageImpl extends Storage {
    @Override
    void save() {
        System.out.println("Save file to AWS S3");
    }

    @Override
    void read(String path) {
    System.out.println("Read file from AWS S3");
    }
}
