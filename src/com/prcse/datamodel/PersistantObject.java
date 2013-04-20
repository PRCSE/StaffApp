package com.prcse.datamodel;

import java.io.Serializable;

public abstract class PersistantObject implements Serializable {
	protected long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PersistantObject [id=" + id + "]";
	}
}
