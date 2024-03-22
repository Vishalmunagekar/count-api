package com.myapp.countapi.service;

import com.myapp.countapi.entities.CountEntity;
import com.myapp.countapi.models.CountResponse;
import com.myapp.countapi.repository.CountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;

/**
 * The CounterService class provides functionality for managing counter operations.
 * It allows generating new keys for new users, resetting keys and counts for existing users,
 * and increasing counts associated with specific keys.
 *
 * <p>This class interacts with the CountRepository to persist count entities.</p>
 *
 * <p>Author: Vishal Munagekar</p>
 * <p>Version: 1.0</p>
 */
@Service
public class CounterService {

    @Autowired
    private CountRepository countRepository;

    /**
     * Generates a new key for a new user and initializes their count to zero.
     *
     * @param username The username of the new user.
     * @return A {@link CountResponse} object containing the generated key and initial count.
     * @throws EntityNotFoundException If the count entity is not found for the provided username.
     */
    public CountResponse generateKeyForNewUser(String username){
        CountEntity countEntity = new CountEntity(username, UUID.randomUUID().toString(), BigInteger.ZERO);
        CountEntity savedCountEntity = countRepository.save(countEntity);
        return new CountResponse(savedCountEntity.getApiKey(), savedCountEntity.getCount(), null, null);
    }

    /**
     * Resets the key and count for an existing user.
     *
     * @param username The username of the existing user.
     * @return A {@link CountResponse} object containing the updated key and count.
     * @throws EntityNotFoundException If the count entity is not found for the provided username.
     */
    public CountResponse forgotKeyForExistingUser(String username){
        CountEntity countEntity = countRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("count not found with username " + username));
        countEntity.setApiKey(UUID.randomUUID().toString());
        CountEntity savedCountEntity = countRepository.save(countEntity);
        return new CountResponse(savedCountEntity.getApiKey(), savedCountEntity.getCount(), null, null);
    }

    /**
     * Increases the count associated with the given key by the specified value.
     *
     * @param key   The key associated with the user's count.
     * @param value The value by which to increase the count.
     * @return A {@link CountResponse} object containing the updated key and count.
     * @throws EntityNotFoundException If the count entity is not found for the provided key.
     */
    public CountResponse increaseCount(String key, BigInteger value) {
        CountEntity countEntity = countRepository.findByApiKey(key)
                .orElseThrow(() -> new EntityNotFoundException("count not found with key " + key));

        countEntity.setCount(countEntity.getCount().add(value));
        CountEntity savedCountEntity = countRepository.save(countEntity);

        return new CountResponse(savedCountEntity.getApiKey(), savedCountEntity.getCount(), null, null);
    }

    /**
     * Resets the count associated with the given key to zero.
     *
     * @param key The key associated with the user's count.
     * @return A {@link CountResponse} object containing the updated key and count.
     * @throws EntityNotFoundException If the count entity is not found for the provided key.
     */
    public CountResponse resetCount(String key) {
        CountEntity countEntity = countRepository.findByApiKey(key)
                .orElseThrow(() -> new EntityNotFoundException("count not found with key " + key));

        countEntity.setCount(BigInteger.ZERO);
        CountEntity savedCountEntity = countRepository.save(countEntity);

        return new CountResponse(savedCountEntity.getApiKey(), BigInteger.ZERO, null, null);
    }
}
