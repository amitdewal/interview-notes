/**
 * 
 */
package com.amit.kafkaspring.model;

/**
 * 
 */
//A Java record is a short way to write a class with fields, a constructor, and getters.)
public record Order(String orderId, String customer, String item, double price) {
}
