package com.techelevator.controller;

import com.techelevator.dao.PotholeDao;
import com.techelevator.model.Pothole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PotholeController {

    @Autowired
    private PotholeDao potholeDao;

    @RequestMapping(path = "/potholes", method = RequestMethod.GET )
    public List<Pothole> getOpenPotholes() {
        return potholeDao.getAllOpenPotholes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/potholes/add", method = RequestMethod.POST)
    public void addPothole(@RequestBody Pothole pothole) {
        potholeDao.addPothole(pothole);
    }

    @RequestMapping(path = "/potholes/delete/{id}", method = RequestMethod.DELETE)
    public void deletePothole(@PathVariable int id) {
        potholeDao.deletePothole(id);
    }

    @RequestMapping(path = "/potholes/employee/{employeeId}", method = RequestMethod.GET)
    public List<Pothole> getPotholesByEmployeeId(@PathVariable int employeeId) {
        return potholeDao.getPotholesByEmployeeId(employeeId);
    }

    @RequestMapping(path = "/potholes/status/update", method = RequestMethod.PUT)
    public void updatePothole(@RequestBody Pothole pothole) {
        potholeDao.updatePotholeStatus(pothole);
    }

    @RequestMapping(path = "/potholes/pending", method = RequestMethod.GET)
    public List<Pothole> getPendingPotholes() {
        return potholeDao.getPendingPotholes();
    }

    @RequestMapping(path= "/potholes/all", method = RequestMethod.GET)
    public List<Pothole> getAllPotholes() {
        return potholeDao.getAllPotholes();
    }

    @RequestMapping(path= "/potholes/repaired", method = RequestMethod.GET)
    public List<Pothole> getRepairedPotholes() {
        return potholeDao.getRepairedPotholes();
    }

    @RequestMapping(path="/potholes/scheduled", method = RequestMethod.GET)
    public List<Pothole> getScheduledPotholes() {
        return potholeDao.getScheduledPotholes();
    }

    @RequestMapping(path="/potholes/unscheduled", method = RequestMethod.GET)
    public List<Pothole> getUnscheduledPotholes() {
        return potholeDao.getUnscheduledPotholes();
    }

    @RequestMapping(path="/potholes/repair_status/update", method = RequestMethod.PUT)
    public void updateRepairStatus(@RequestBody Pothole pothole) {
        potholeDao.updatePotholeRepairStatus(pothole);
    }

    @RequestMapping(path="/potholes/complete", method = RequestMethod.PUT)
    public void completePothole(@RequestBody Pothole pothole) {
        potholeDao.repairPothole(pothole);
    }

    @RequestMapping(path = "/potholes/count/completed", method = RequestMethod.GET)
    public Integer countRepaired() {
        return potholeDao.countRepaired();
    }

    @RequestMapping(path = "/potholes/count/scheduled", method = RequestMethod.GET)
    public Integer countScheduled() {
        return potholeDao.countScheduled();
    }

    @RequestMapping(path = "/potholes/count/unscheduled", method = RequestMethod.GET)
    public Integer countUnscheduled() {
        return potholeDao.countUnscheduled();
    }

    @RequestMapping(path = "/potholes/verified", method = RequestMethod.GET)
    public List<Pothole> getVerifiedPotholes() {
        return potholeDao.getAllVerifiedPotholes();
    }
    @RequestMapping(path = "/potholes/showStatus", method = RequestMethod.PUT)
    public void updateShowDetails(@RequestBody Pothole pothole) {
        potholeDao.showDetails(pothole);
    }
    @RequestMapping(path = "/potholes/open/all", method = RequestMethod.GET)
    public List<Pothole> getAllReportedPotholes() {
       return potholeDao.getAllReportedPotholes();
    }
    @RequestMapping(path = "/potholes/update/repair_date", method = RequestMethod.PUT)
    public void updateRepairDate(@RequestBody Pothole pothole) {
        potholeDao.updatePotholeRepairDate(pothole);
    }
    @RequestMapping(path = "/potholes/scheduled/employee", method = RequestMethod.GET)
    public List<Pothole> getScheduledPotholesWithEmployeeInfo() {
        return potholeDao.getScheduledPotholesWithEmployeeInfo();
    }


}
