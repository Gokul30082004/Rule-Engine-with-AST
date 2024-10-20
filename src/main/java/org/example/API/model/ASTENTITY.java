package org.example.API.model;

import jakarta.persistence.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aST")
public class ASTENTITY {
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use appropriate strategy for your NoSQL DB
    @Id
    private ObjectId id; // or ObjectId for MongoDB

    private String type;
    private String key;
    private String value;

    private ASTENTITY left;

    private ASTENTITY right;

    // Constructors
    public ASTENTITY() {}

    public ASTENTITY(String type, String key, String value, ASTENTITY left, ASTENTITY right) {
        this.type = type;
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }
    public ASTENTITY(ASTENTITY left, ASTENTITY right, String type){

        this.type = type;
        this.left = left;
        this.right = right;
    }
    public ASTENTITY(String value, String key, String type){
        this.type = type;
        this.key = key;
        this.value = value;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ASTENTITY getLeft() {
        return left;
    }

    public void setLeft(ASTENTITY left) {
        this.left = left;
    }

    public ASTENTITY getRight() {
        return right;
    }

    public void setRight(ASTENTITY right) {
        this.right = right;
    }
}
