package com.example.socketreact.repository;

import com.example.socketreact.entity.SocketEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocketRepository extends CrudRepository<SocketEntity, Long> {
    @Override
    List<SocketEntity> findAll();

    @Query(value = "select * from socket_entity where room like :room", nativeQuery = true)
    List<SocketEntity> findRoomAll(@Param("room") String room);
}
