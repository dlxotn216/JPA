package me.strongwhisky.app.day26.service;

/**
 * Created by taesu on 2018-05-26.
 */
public interface TestService {
    void readByDefault();

    void readByScalarType();

    void readByReadOnlyQueryHints();

    void readByReadOnlyTransaction();

    void readByNotSupportransaction();

    void readByReadOnlyTransactionAndRepository();

    void readByNotSupportedTransactionAndRepository();
}
