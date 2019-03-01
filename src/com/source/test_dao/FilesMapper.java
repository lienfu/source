package com.source.test_dao;

import org.springframework.stereotype.Repository;

import com.source.test_model.Files;

@Repository
public interface FilesMapper {

	public int addFile(Files File);
}
