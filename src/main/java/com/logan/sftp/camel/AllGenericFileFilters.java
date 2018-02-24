package com.logan.sftp.camel;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("allGenericFileFilters")
public class AllGenericFileFilters<T> implements GenericFileFilter<T> {
	
	@Autowired
	private List<GenericFileFilter<T>> list;
	
	@PostConstruct
	private void excludeSelf() {
		list.removeIf(x -> this.equals(x));
	}

	@Override
	public boolean accept(GenericFile<T> file) {
		return list.stream().allMatch(f -> f.accept(file));
	}
}
