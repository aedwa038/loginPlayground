package org.playground.login.playground.service;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.repository.StuffRepository;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.repository.pojo.Stuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StuffService {

    @Autowired
    StuffRepository stuffRepository;

    public List<Stuff> getByUser(RegisteredUser registeredUser) throws ApplicatonError{
        return stuffRepository.getByUserId(registeredUser.getId());
    }

    public List<Stuff> getByUser(int userId) throws ApplicatonError{
        return stuffRepository.getByUserId(userId);
    }

    public void insert(RegisteredUser registeredUser, String data) throws ApplicatonError {
        Stuff stuff = new Stuff();
        stuff.setUser_id(registeredUser.getId());
        stuff.setData(data);
        try {
            stuffRepository.insert(stuff);
        } catch (Exception ex) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.BACKEND_ERROR, ex);
        }
    }

    public void update(int id, String data) throws ApplicatonError {
        Stuff stuff = new Stuff();
        stuff.setId(id);
        stuff.setData(data);
        try {
            stuffRepository.update(stuff);
        } catch (Exception ex) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.BACKEND_ERROR, ex);
        }
    }


}
