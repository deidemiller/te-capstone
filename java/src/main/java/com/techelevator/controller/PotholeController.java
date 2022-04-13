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
    public List<Pothole> getPotholes() {
        return potholeDao.getAllPotholes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/potholes/add", method = RequestMethod.POST)
    public void addPothole(@RequestBody Pothole pothole) {
        potholeDao.addPothole(pothole);
    }

    @RequestMapping(path = "/potholes/delete", method = RequestMethod.DELETE)
    public void deletePothole(@RequestBody Pothole pothole) {
        potholeDao.deletePothole(pothole.getPotholeId());
    }

    @RequestMapping(path = "potholes/status/update", method = RequestMethod.PUT)
    public void updatePothole(@RequestBody Pothole pothole) {
        potholeDao.updatePotholeStatus(pothole);
    }

    @RequestMapping(path = "/potholes/pending", method = RequestMethod.GET)
    public List<Pothole> getPendingPotholes() {
        return potholeDao.getPendingPotholes();
    }

}
