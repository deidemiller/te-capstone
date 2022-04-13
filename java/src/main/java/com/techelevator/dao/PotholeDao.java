package com.techelevator.dao;

import com.techelevator.model.Pothole;

import java.util.List;

public interface PotholeDao {
    List<Pothole> getAllOpenPotholes();
    void addPothole(Pothole pothole);
    void deletePothole(int potholeId);
    void updatePotholeStatus(Pothole pothole);
    List<Pothole> getPendingPotholes();
    List<Pothole> getAllPotholes();
    List<Pothole> getRepairedPotholes();
    List<Pothole> getScheduledPotholes();
    List<Pothole> getUnscheduledPotholes();
}
