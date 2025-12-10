package com.mycompany.mavenproject1;

public abstract class BaseModel {
    protected int id;
    
    public BaseModel() {
    }
    
    public BaseModel(int id) {
        this.id = id;
    }

    public abstract String getDisplayInfo();
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return getDisplayInfo();
    }
}
