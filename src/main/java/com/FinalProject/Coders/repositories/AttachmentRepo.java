package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttachmentRepo extends JpaRepository<Attachment , Long> {
    @Query("UPDATE Attachment a SET a.photo = :photo WHERE a.Id = :id")
    void updateAttachmentById(@Param("id") Long id , @Param("photo") byte[] photo);
}
