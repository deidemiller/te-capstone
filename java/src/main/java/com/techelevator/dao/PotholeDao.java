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
    void updatePotholeRepairStatus(Pothole pothole);
    void repairPothole(Pothole pothole);
    Integer countRepaired();
    Integer countUnscheduled();
    Integer countScheduled();
    List<Pothole> getAllVerifiedPotholes();
    void showDetails(Pothole pothole);
    List<Pothole> getAllReportedPotholes();
    void updatePotholeRepairDate(Pothole pothole);
    List<Pothole> getPotholesByEmployeeId(int employeeId);
    List<Pothole> getScheduledPotholesWithEmployeeInfo();
}
