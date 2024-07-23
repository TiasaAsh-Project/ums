package com.indusnet.ums.repository;
import com.indusnet.ums.model.UserModel;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface IUserServiceRepository extends MongoRepository<UserModel, String> {
	 
	 Optional <UserModel> findByEmailAndPassword(String email, String password);

	Optional<UserModel> findById(ObjectId objectId);

    void deleteById(ObjectId objectId);

	Optional<UserModel> getByName(String name);

	Optional<UserModel> getByEmail(String email);

	Object getByPassword(String password);
}


